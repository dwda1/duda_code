public class ViperOff extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public ViperOff(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequeriments(viper);
  }

  @Override
  public void initialized() {
    viper.viperOff();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
