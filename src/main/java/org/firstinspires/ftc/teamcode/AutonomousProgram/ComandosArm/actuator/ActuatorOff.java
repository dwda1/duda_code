package org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ActuatorPIDFSubsystem;

public class ActuatorOff extends CommandBase {

    private final ActuatorPIDFSubsystem actuator;

    public ActuatorOff(ActuatorPIDFSubsystem subsystem) {
        actuator = subsystem;
        addRequirements(actuator);
    }

    @Override
    public void initialize() {
        actuator.actuatorOff();
    }

    @Override
    public boolean isFinished() {
        return actuator.atTarget();
    }
}
