package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.claw;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ClawSubsystem;

public class ClawClose extends CommandBase {

    private final ClawSubsystem claw;
    private ElapsedTime timer;

    public ClawClose (ClawSubsystem subsystem) {
        claw = subsystem;
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.claw_Close();
        timer = new ElapsedTime();
    }

    @Override
    public boolean isFinished() {
        return timer.milliseconds() > 500;
    }
}
