package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ActuatorPIDFSubsystem;

public class ActuatorBack extends CommandBase {

    private final ActuatorPIDFSubsystem actuator;

    public ActuatorBack(ActuatorPIDFSubsystem subsystem) {
        actuator = subsystem;
        addRequirements(actuator);
    }

    @Override
    public void initialize() {
        actuator.actuatorBack();
    }

    @Override
    public boolean isFinished() {
        return actuator.atTarget();
    }
}
