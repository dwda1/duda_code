package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ArmPIDFSubsystem;

public class GoToHighChamber extends CommandBase {
    private final ArmPIDFSubsystem viper;

    public GoToHighChamber(ArmPIDFSubsystem subsystem) {
        viper = subsystem;
        addRequirements(viper);
    }

    @Override
    public void initialize() {
        viper.viperToHighChamber();
    }

    @Override
    public boolean isFinished() {
        return viper.atTarget();
    }
}
