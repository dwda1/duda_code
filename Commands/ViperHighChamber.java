public class GoToHighChamber extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public GoToHighChamber(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequeriments(viper);
  }

  @Override
  public void initialize() {
    viper.viperToHighChamber();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
