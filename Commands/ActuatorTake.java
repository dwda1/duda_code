public class ActuatorTake extends CommandBase {
  
  private final ActuatorPIDFSubsystem actuator;

  public ActuatorOff(ActuatorPIDFSubsystem subsystem) {
    actuator = subsystem;
    addRequirements(actuator);
  }

  @Override
  public void initialized() {
    actuator.actuatorTake();
  }

  @Override
  public boolean isFinished() {
    return actuator.atTarget();
  }
}
