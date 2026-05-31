public class ViperDown extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public ViperDown(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequirements(viper);
  }

   @Override
  public void initialize() {
    viper.viperDown();
  }

  @Override
  public boolean isFinished() {
    return viper.atTarget();
  }
}
