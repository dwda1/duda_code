public class AutoViper extends LinearOpMode {

  private ArmPIDFSubsystem viper;

  @Override
  public void runOpMode() throws InterruptedException {
    viper = new ArmPIDFSubsystem(hwMap);
    viper.resetViper();
    
  ViperOff v_off = new ViperOff(viper);
  ViperDown v_down = new ViperDown(viper);
  GoToHighChamber v_high_chamber = new GoToHighChamber(viper);
  GoToLowChamber v_low_chamber = new GoToLowChamber(viper);
  GoToHighBasket v_high_basket = new GoToHighBasket(viper);
  GoToLowBasket v_low_basket = new GoToLowBasket(viper);

    waitForStart();

    CommandScheduler.getInstance().schedule(
      new SequentialCommandGroup(
        v_low_chamber,
        v_down,

        v_high_chamber,
        v_down,

        v_low_basket,
        v_down,

        v_high_basket,
        v_down,
        v_off
        )
      );

    while (opModeIsActive()) {

      CommandScheduler.getInstance().run();

      telemetry.addData("Viper status: ", viper.getStatus());
      telemetry.update();
    }

    CommandScheduler.getInstance().reset();
  }
}
      
  
  
