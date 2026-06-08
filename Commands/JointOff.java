public class JointOff extends CommandBase {

public final ArmPIDFSubsystem joint;

public JointOff(ArmPIDFSubsystem = subsystem) {
  joint = subsystem;
  addRequirements(joint);
}

  @Override
  public void initialize() {
    joint.jointOff();
}

  @Override
  public boolean isFinished() {
    return joint.jointAtTarget();
  }
}
