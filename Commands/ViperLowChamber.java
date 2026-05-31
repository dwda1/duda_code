public class GoToLowChamber extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public GoToLowChamber(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequirements(viper);
  }

  @Override
  public void initialize() {
    viper.viperToLowChamber();
  }

  @Override
  public boolean isFinished() {
    return viper.atTarget();
  }
}
