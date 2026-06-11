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

    public void Claw_Open() { mode = Mode.OPEN; }
    public void Claw_Close() { mode = Mode.CLOSE; }

    // verifica se já chegou na posição
    public boolean clawAtTarget() { return claw.getPOsition() == pos; }
    
    @Override
    public void periodic() {
        //state machine
        switch (mode) {

            case OPEN:
                pos = 0.35;
                claw.setPosition(pos);

            case CLOSE:
                pos = 0.0;
                claw.setPosition(pos);
        }
    }
    
    public String getClawStatus() {
        return String.format(
                "Mode=%s ClawPos=%d",
                mode,
                claw.getPosition()
        );
    }
}
