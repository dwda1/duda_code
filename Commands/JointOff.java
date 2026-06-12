public class JointOff extends CommandBase {

public final ArmPIDFSubsystem joint;
private ElapsedTime timer;

public JointOff(ArmPIDFSubsystem = subsystem) {
  joint = subsystem;
  addRequirements(joint);
}

  @Override
  public void initialize() {
    joint.jointOff();
    timer = new ElapsedTime();
}

  @Override
  public boolean isFinished() {
    return timer.milliseconds() > 500;
  }
}
