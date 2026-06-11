public class ClawClose extends CommandBase {
  
  private final ClawSubsystem claw;

  public ClawClose (ClawSubsystem subsystem) {
    claw = subsystem;
    addRequirements(claw);
  }

  @Override
  public void initialize() {
    claw.claw_Close();
  }

  @Override
  public void isFinished() {
    claw.clawAtTarget();
  }
}
