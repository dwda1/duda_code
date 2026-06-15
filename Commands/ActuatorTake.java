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
