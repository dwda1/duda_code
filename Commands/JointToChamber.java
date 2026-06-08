public class JointToChamber extends CommandBase {

  public final ArmPIDFSubsystem joint;

  public JointToChamber (ArmPIDFSubsystem subsystem) {
    joint = subsystem;
    addRequirements(joint);
  }

  @Override
  public void initialize() {
    joint.jointToChamber();
  }

  @Override
  public boolean isFinished() {
    joint.jointAtTarget();
  }
}
