package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ArmPIDFSubsystem;

public class GoToLowBasket extends CommandBase {
    private final ArmPIDFSubsystem viper;

    public GoToLowBasket(ArmPIDFSubsystem subsystem) {
        viper = subsystem;
        addRequirements(viper);
    }

    @Override
    public void initialize() {
        viper.viperToLowBasket();
    }

    @Override
    public boolean isFinished() {
        return viper.atTarget();
    }
}
