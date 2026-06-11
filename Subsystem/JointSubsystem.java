package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class SJointSubsystem extends SubsystemBase {

    private Servo joint;
    public double pos;

    //construtor
    public JointSubsystem (HardwareMap hwMap) {
        joint = hwMap.get(Servo.class, "joint");
    }

    //====================================================
    //===============JOINT ENUM MODE==================
    //====================================================

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

    public boolean jointAtTarget() {
        boolean targetPos = true;
        if (joint.getPosition() == pos) {
            targetPos = true;
        }
        return targetPos;
    }

    @Override
    public void periodic() {
        //state machine
        switch (jointMode) {

            case OFF:
                pos = 0.0;
                joint.setPosition(pos);
                break;

            case DOWN:
                pos = 0.0;
                joint.setPosition(pos);
                break;

            case TO_BASKET:
                pos = 0.85;
                joint.setPosition(pos);
                break;

            case TO_CHAMBER:
                pos = 0.5;
                joint.setPosition(pos);
                break;
        }

    }

}
