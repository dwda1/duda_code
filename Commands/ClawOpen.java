public class ClawOpen extends CommandBase {
  
  private final ClawSubsystem claw;

  public ClawOpen (ClawSubsystem subsystem) {
    claw = subsystem;
    addRequirements(claw);
  }

  @Override
  public void initialize() {
    claw.claw_Open();
  }

  @Override
  public boolean isFinished() {
    return claw.clawAtTarget();
  }
}
