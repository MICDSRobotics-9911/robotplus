package org.firstinspires.ftc.teamcode.robotplus.autonomoushelper;

/**
 * An object that makes a goal for a specific step in an autonomous program
 * @author Alex Migala, Blake Abel, Nick Clifford
 * @since 9/1/17
 */
public class AutonomousGoal {

    /**
     * The actual thing that this step does
     */
    private String goalName;

    /**
     * The step at which the event fires
     */
    private int triggerStep;

    /**
     * Constructor for just a goal, triggerStep is 0
     * @param goal the goal of the step
     */
    public AutonomousGoal(String goal) {
        this.goalName = goal;
    }

    /**
     * Full constructor
     * @param goal the goal of the step
     * @param step the step at which it will be triggered
     */
    public AutonomousGoal(String goal, int step) {
        this.goalName = goal;
        this.triggerStep = step;
    }

    /**
     * Gets the trigger step
     * @return the trigger step
     */
    public int getTriggerStep() {
        return this.triggerStep;
    }

    /**
     * Gets the goal
     * @return the goal
     */
    public String getGoal() {
        return this.goalName;
    }
}
