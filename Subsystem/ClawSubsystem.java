package org.firstinspires.ftc.teamcode.Subsystems;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
    private Servo claw;
    public double pos;

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
                pos = 0.85;
                claw.setPosition(pos);
                break;

            case CLOSE:
                pos = 0.0;
                claw.setPosition(pos);
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
