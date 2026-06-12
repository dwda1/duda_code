public class JointToBasket extends CommandBase {

  public final ArmPIDFSubsystem joint;
  private ElapsedTime timer;

  public JointToBasket (ArmPIDFSubsystem subsystem) {
    joint = subsystem;
    addRequirements(joint);
  }

  @Override
  public void initialize() {
    joint.jointToBasket();
    timer = new ElapsedTime();
  }

  @Override
  public boolean isFinished() {
    return timer.milliseconds() > 1000;
  }
}
