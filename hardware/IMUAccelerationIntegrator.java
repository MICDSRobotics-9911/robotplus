/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package org.firstinspires.ftc.teamcode.robotplus.hardware;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import static org.firstinspires.ftc.robotcore.external.navigation.NavUtil.meanIntegrate;
import static org.firstinspires.ftc.robotcore.external.navigation.NavUtil.minus;
import static org.firstinspires.ftc.robotcore.external.navigation.NavUtil.plus;
import static org.firstinspires.ftc.robotcore.external.navigation.NavUtil.scale;

/**
 * Created by BAbel on 11/6/2017.
 */

public class IMUAccelerationIntegrator implements BNO055IMU.AccelerationIntegrator {

    BNO055IMU.Parameters parameters;
    Position position;
    Velocity velocity;
    Acceleration acceleration;

    public Position getPosition() { return this.position; }
    public Velocity getVelocity() { return this.velocity; }
    public Acceleration getAcceleration() { return this.acceleration; }

    //------------------------------------------------------------------------------------------
    // Construction
    //------------------------------------------------------------------------------------------

    IMUAccelerationIntegrator() {
        this.parameters = null;
        this.position = new Position();
        this.velocity = new Velocity();
        this.acceleration = null;
    }

    //------------------------------------------------------------------------------------------
    // Operations
    //------------------------------------------------------------------------------------------

    @Override public void initialize(@NonNull BNO055IMU.Parameters parameters, @Nullable Position initialPosition, @Nullable Velocity initialVelocity)
    {
        this.parameters = parameters;
        this.position = initialPosition != null ? initialPosition : this.position;
        this.velocity = initialVelocity != null ? initialVelocity : this.velocity;
        this.acceleration = null;
    }

    @Override public void update(Acceleration linearAcceleration)
    {
        // We should always be given a timestamp here
        if (linearAcceleration.acquisitionTime != 0)
        {
            // We can only integrate if we have a previous acceleration to baseline from
            if (acceleration != null)
            {
                Acceleration accelPrev    = acceleration;
                Velocity     velocityPrev = velocity;
                acceleration = linearAcceleration;

                if (accelPrev.acquisitionTime != 0)
                {
                    //epsilon can be anything: the lower the number, the greater the accuracy, but also the worse performance.
                    Velocity deltaVelocity = tightRecursiveSimpson(acceleration, accelPrev, 1e-12);
                    velocity = plus(velocity, deltaVelocity);
                }

                if (velocityPrev.acquisitionTime != 0)
                {
                    //epsilon can be anything: the lower the number, the greater the accuracy, but also the worse performance.
                    Position deltaPosition = tightRecursiveSimpson(velocity, velocityPrev, 1e-12);
                    position = plus(position, deltaPosition);
                }

                if (parameters != null && parameters.loggingEnabled)
                {
                    RobotLog.vv(parameters.loggingTag, "dt=%.3fs accel=%s vel=%s pos=%s", (acceleration.acquisitionTime - accelPrev.acquisitionTime)*1e-9, acceleration, velocity, position);
                }
            }
            else
                acceleration = linearAcceleration;
        }
    }

    /* FTC's Mean Integrate simply takes the current value of the acceleration and multiplies it
     * with the time passed. This basically creates a big Riemann sum, but it isn't the best with
     * the noisy data that an accelerometer will produce. We can do better. So we FLEX ON THAT CALC
     * and use other methods of numerical integration that follow. We will see which work the best.
     */

    /**
     * Changes the type from acceleration to velocity, keeping values for x, y, and z. Used for
     * returning the correct type after integrating in other functions, *NOT* for actually
     * converting acceleration to velocity.
     * @param a the Acceleration to convert
     * @return the converted Velocity
     */
    public Velocity fakeConvert(Acceleration a){
        return new Velocity(a.unit,
                a.xAccel,
                a.yAccel,
                a.zAccel,
                a.acquisitionTime);
    }

    /**
     * Changes the type from velocity to position, keeping values for x, y, and z. Used for
     * returning the correct type after integrating in other functions, *NOT* for actually
     * converting acceleration to velocity.
     * @param v the Velocity to convert
     * @return the converted Position
     */
    public Position fakeConvert(Velocity v){
        return new Position(v.unit,
                v.xVeloc,
                v.yVeloc,
                v.zVeloc,
                v.acquisitionTime);
    }

    /* SIMPSON'S RULE: Creates a better approximation of the integral through creating quadratic
     * functions to integrate instead of rectangles. Best for smoother functions, but very solid
     * overall (especially only given endpoints like we are). The recursive algorithm performs
     * the approximation repeatedly on smaller intervals intil the error is below a certain point.
     */

    public Velocity simpsonApproximation(Acceleration cur, Acceleration prev){
        double intervalHooHa = (cur.acquisitionTime - prev.acquisitionTime) / 6 * 1e-9;
        Acceleration middleValue = scale(plus(cur, prev), 0.5);
        Acceleration scaledMiddle = scale(middleValue, 4);

        Acceleration numericalApproximation = scale(plus(plus(prev, scaledMiddle), cur), intervalHooHa);

        return fakeConvert(numericalApproximation);
    }

    public Position simpsonApproximation(Velocity cur, Velocity prev){
        double middle = (cur.acquisitionTime - prev.acquisitionTime) / 6 * 1e-9;
        Velocity middleValue = scale(plus(cur, prev), 0.5);
        Velocity scaledMiddle = scale(middleValue, 4);

        Velocity numericalApproximation = scale(plus(plus(prev, scaledMiddle), cur), middle);

        return fakeConvert(numericalApproximation);
    }

    public Velocity recursiveSimpson(Acceleration cur, Acceleration prev, double epsilon){
        //double middle = (cur.acquisitionTime - prev.acquisitionTime) / 6 * 1e-9;
        Acceleration middleValue = scale(plus(cur, prev), 0.5);

        Velocity left = simpsonApproximation(prev, middleValue);
        Velocity right = simpsonApproximation(middleValue, cur);
        Velocity whole = simpsonApproximation(prev,cur);

        if (Math.abs(left.xVeloc + right.xVeloc - whole.xVeloc) <= 15 * epsilon ||
                Math.abs(left.yVeloc + right.yVeloc - whole.yVeloc) <= 15 * epsilon ||
                Math.abs(left.zVeloc + right.zVeloc - whole.zVeloc) <= 15 * epsilon) {
            return plus(left, plus(right, scale(plus(left, minus(right, whole)), 1 / 15)));
        }
        return plus(recursiveSimpson(prev, middleValue, epsilon / 2), recursiveSimpson(middleValue, cur, epsilon / 2));
    }

    public Position recursiveSimpson(Velocity cur, Velocity prev, double epsilon){
        //double middle = (cur.acquisitionTime - prev.acquisitionTime) / 6 * 1e-9;
        Velocity middleValue = scale(plus(cur, prev), 0.5);

        Position left = simpsonApproximation(prev, middleValue);
        Position right = simpsonApproximation(middleValue, cur);
        Position whole = simpsonApproximation(prev,cur);

        if (Math.abs(left.x + right.x - whole.x) <= 15 * epsilon ||
                Math.abs(left.y + right.y - whole.y) <= 15 * epsilon ||
                Math.abs(left.z + right.z - whole.z) <= 15 * epsilon) {
            return plus(left, plus(right, scale(plus(left, minus(right, whole)), 1 / 15)));
        }
        return plus(recursiveSimpson(prev, middleValue, epsilon / 2), recursiveSimpson(middleValue, cur, epsilon / 2));
    }

    public Velocity tightRecursiveSimpson(Acceleration cur, Acceleration prev, double epsilon){
        //double middle = (cur.acquisitionTime - prev.acquisitionTime) / 6 * 1e-9;
        Acceleration middleValue = scale(plus(cur, prev), 0.5);

        Velocity left = simpsonApproximation(prev, middleValue);
        Velocity right = simpsonApproximation(middleValue, cur);
        Velocity whole = simpsonApproximation(prev,cur);

        if (Math.abs(left.xVeloc + right.xVeloc - whole.xVeloc) <= 15 * epsilon &&
                Math.abs(left.yVeloc + right.yVeloc - whole.yVeloc) <= 15 * epsilon &&
                Math.abs(left.zVeloc + right.zVeloc - whole.zVeloc) <= 15 * epsilon) {
            return plus(left, plus(right, scale(plus(left, minus(right, whole)), 1 / 15)));
        }
        return plus(recursiveSimpson(prev, middleValue, epsilon / 2), recursiveSimpson(middleValue, cur, epsilon / 2));
    }

    public Position tightRecursiveSimpson(Velocity cur, Velocity prev, double epsilon){
        //double middle = (cur.acquisitionTime - prev.acquisitionTime) / 6 * 1e-9;
        Velocity middleValue = scale(plus(cur, prev), 0.5);

        Position left = simpsonApproximation(prev, middleValue);
        Position right = simpsonApproximation(middleValue, cur);
        Position whole = simpsonApproximation(prev,cur);

        if (Math.abs(left.x + right.x - whole.x) <= 15 * epsilon &&
                Math.abs(left.y + right.y - whole.y) <= 15 * epsilon &&
                Math.abs(left.z + right.z - whole.z) <= 15 * epsilon) {
            return plus(left, plus(right, scale(plus(left, minus(right, whole)), 1 / 15)));
        }
        return plus(recursiveSimpson(prev, middleValue, epsilon / 2), recursiveSimpson(middleValue, cur, epsilon / 2));
    }

}
