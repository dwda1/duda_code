package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ArmPIDFSubsystem;

public class ViperDown extends CommandBase {
    private final ArmPIDFSubsystem viper;

    public ViperDown(ArmPIDFSubsystem subsystem) {
        viper = subsystem;
        addRequirements(viper);
    }

    @Override
    public void initialize() {
        viper.viperDown();
    }

    @Override
    public boolean isFinished() {
        return viper.atTarget();
    }
}
