package org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
    private final Servo claw;

    //posições
    public static double open = 0.85;
    public static double close = 0.0;

    public ClawSubsystem (HardwareMap hwMap) {
        claw = hwMap.get(Servo.class, "claw");
    }

    public enum Mode {
        OPEN,
        CLOSE
    }
    private Mode mode = Mode.OPEN;

    public void claw_Open() { mode = Mode.OPEN; }
    public void claw_Close() { mode = Mode.CLOSE; }

    @Override
    public void periodic() {
        //state machine
        switch (mode) {

            case OPEN:
                claw.setPosition(open);
                break;

            case CLOSE:
                claw.setPosition(close);
                break;
        }
    }

    public String getClawStatus() {
        return String.format(
                "Mode=%s ClawPos=%.2f",
                mode,
                claw.getPosition()
        );
    }
}