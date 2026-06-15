public class ActuatorLowChamber extends CommandBase {
  
  private final ActuatorPIDFSubsystem actuator;

  public ActuatorOff(ActuatorPIDFSubsystem subsystem) {
    actuator = subsystem;
    addRequirements(actuator);
  }

  @Override
  public void initialized() {
    actuator.actuatorLowChamber();
  }

  @Override
  public boolean isFinished() {
    return actuator.atTarget();
  }
}
