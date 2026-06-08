public class JointDown extends CommandBase {

public final ArmPIDFSubsystem joint;

public JointDown(ArmPIDFSubsystem = subsystem) {
  joint = subsystem;
  addRequirements(joint);
}

  @Override
  public void initialize() {
    joint.jointDown();
}

  @Override
  public boolean isFinished() {
    return joint.jointAtTarget();
  }
}
