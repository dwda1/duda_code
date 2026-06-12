public class JointToChamber extends CommandBase {

  public final ArmPIDFSubsystem joint;
  private ElapsedTime timer;

  public JointToChamber (ArmPIDFSubsystem subsystem) {
    joint = subsystem;
    addRequirements(joint);
  }

  @Override
  public void initialize() {
    joint.jointToChamber();
    timer = new ElapsedTime();
  }

  @Override
  public boolean isFinished() {
    return timer.milliseconds() > 700;
  }
}
