package org.firstinspires.ftc.teamcode.robotplus.autonomous;

import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;

public class TranslateData {
    private MecanumDrive mecanumDrive;
    private MecanumDrive.Direction direction;
    private double velocity;
    private double rotation;

    public TranslateData(MecanumDrive mecanumDrive, MecanumDrive.Direction direction, double velocity, double rotation) {
        this.mecanumDrive = mecanumDrive;
        this.direction = direction;
        this.velocity = velocity;
        this.rotation = rotation;
    }

    public TranslateData(MecanumDrive mecanumDrive, MecanumDrive.Direction direction) {
        this.mecanumDrive = mecanumDrive;
        this.direction = direction;
    }

    public MecanumDrive getMecanumDrive() {
        return mecanumDrive;
    }

    public MecanumDrive.Direction getDirection() {
        return direction;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getRotation() {
        return rotation;
    }
}
