package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ArmPIDFSubsystem;

public class GoToHighBasket extends CommandBase {
    private final ArmPIDFSubsystem viper;

    public GoToHighBasket(ArmPIDFSubsystem subsystem) {
        viper = subsystem;
        addRequirements(viper);
    }

    @Override
    public void initialize() {
        viper.viperToHighBasket();
    }

    @Override
    public boolean isFinished() {
        return viper.atTarget();
    }
}
