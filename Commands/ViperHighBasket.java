public class GoToHighBasket extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public GoToHighBasket(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequirements(viper);
  }

  @Override
  public void initialize() {
    viper.viperToHighBasket();
  }

  @Override
  public boolean isFinished() {
    return viper.atTarget();
  }
}
  
