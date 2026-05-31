public class GoToLowBasket extends CommandBase {

  private final ArmPIDFSubsystem viper;

  public GoToLowBasket(ArmPIDFSubsystem subsystem) {
    viper = subsystem;
    addRequirements(viper);
  }

  @Override
  public void initialize() {
    viper.viperToLowBasket();
  }

  @Override
  public boolean isFinished() {
    return viper.atTarget();
  }
}
  
