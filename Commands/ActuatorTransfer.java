public class ActuatorTransfer extends CommandBase {
  
  private final ActuatorPIDFSubsystem actuator;

  public ActuatorOff(ActuatorPIDFSubsystem subsystem) {
    actuator = subsystem;
    addRequirements(actuator);
  }

  @Override
  public void initialized() {
    actuator.actuatorTransfer();
  }

  @Override
  public boolean isFinished() {
    return actuator.atTarget();
  }
}
