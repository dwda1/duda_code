public class ViperOff extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public ViperOff(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequirements(viper);
  }

  @Override
  public void initialize() {
    viper.viperOff();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
