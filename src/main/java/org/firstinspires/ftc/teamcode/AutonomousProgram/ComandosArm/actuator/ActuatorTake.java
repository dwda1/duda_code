package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ActuatorPIDFSubsystem;

public class ActuatorTake extends CommandBase {

    private final ActuatorPIDFSubsystem actuator;

    public ActuatorTake(ActuatorPIDFSubsystem subsystem) {
        actuator = subsystem;
        addRequirements(actuator);
    }

    @Override
    public void initialize() {
        actuator.actuatorTake();
    }

    @Override
    public boolean isFinished() {
        return actuator.atTarget();
    }
}
