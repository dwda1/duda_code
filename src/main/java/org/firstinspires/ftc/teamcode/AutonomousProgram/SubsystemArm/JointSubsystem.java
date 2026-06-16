package org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class JointSubsystem extends SubsystemBase {

    private final Servo joint;

    //posições
    public static double startPosition = 0;
    public static double toBasket = 1.0;
    public static double toChamber = 0.5;

    //construtor
    public JointSubsystem (HardwareMap hwMap) {
        joint = hwMap.get(Servo.class, "joint");
    }

    private enum JMode {
        OFF,
        DOWN,
        TO_BASKET,
        TO_CHAMBER
    }

    private JMode jointMode = JMode.OFF;

    public void jointOff() { jointMode = JMode.OFF; }
    public void jointDown() { jointMode = JMode.DOWN; }
    public void jointToBasket() { jointMode = JMode.TO_BASKET; }
    public void jointToChamber() { jointMode = JMode.TO_CHAMBER; }

    @Override
    public void periodic() {
        //state machine
        switch (jointMode) {

            case OFF:
                joint.setPosition(startPosition);
                break;

            case DOWN:
                joint.setPosition(startPosition);
                break;

            case TO_BASKET:
                joint.setPosition(toBasket);
                break;

            case TO_CHAMBER:
                joint.setPosition(toChamber);
                break;
        }
    }

    public String getJointStatus() {
        return String.format(
                "Mode=%s JointPos=%.2f",
                jointMode,
                joint.getPosition()
        );
    }
}
