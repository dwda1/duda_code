public class ActuatorTransfer extends CommandBase {
  
  private final ActuatorPIDFSubsystem actuator;

  public ActuatorTransfer(ActuatorPIDFSubsystem subsystem) {
    actuator = subsystem;
    addRequirements(actuator);
  }

  @Override
  public void initialize() {
    actuator.actuatorTransfer();
  }

  @Override
  public boolean isFinished() {
    return actuator.atTarget();
  }
}
