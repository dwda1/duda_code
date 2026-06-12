public class JointDown extends CommandBase {

public final ArmPIDFSubsystem joint;
private ElapsedTime timer;

public JointDown(ArmPIDFSubsystem = subsystem) {
  joint = subsystem;
  addRequirements(joint);
}

  @Override
  public void initialize() {
    joint.jointDown();
    timer = new ElapsedTime();
}

  @Override
  public boolean isFinished() {
    return timer.milliseconds() > 500;
  }
}
