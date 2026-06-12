public class ClawOpen extends CommandBase {
  
  private final ClawSubsystem claw;
  private ElapsedTime timer;

  public ClawOpen (ClawSubsystem subsystem) {
    claw = subsystem;
    addRequirements(claw);
  }

  @Override
  public void initialize() {
    claw.claw_Open();
    timer = new ElapsedTime();
  }

  @Override
  public boolean isFinished() {
    return timer.milliseconds() > 500;
  }
}
