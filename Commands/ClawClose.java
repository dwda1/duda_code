public class ClawClose extends CommandBase {
  
  private final ClawSubsystem claw;
  private ElapsedTime timer;

  public ClawClose (ClawSubsystem subsystem) {
    claw = subsystem;
    addRequirements(claw);
  }

  @Override
  public void initialize() {
    claw.claw_Close();
    timer = new ElapsedTime();
  }

  @Override
  public boolean isFinished() {
    return timer.milliseconds() > 500;
  }
}
