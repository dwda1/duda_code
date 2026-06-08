public class JointToBasket extends CommandBase {

  public final ArmPIDFSubsystem joint;

  public JointToBasket (ArmPIDFSubsystem subsystem) {
    joint = subsystem;
    addRequirements(joint);
  }

  @Override
  public void initialize() {
    joint.jointToBasket();
  }

  @Override
  public boolean isFinished() {
    joint.jointAtTarget();
  }
}
