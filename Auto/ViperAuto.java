public class AutoViper extends LinearOpMode {

  private ArmPIDFSubsystem viper;

  @Override
  public void runOpMode() throws InterruptedException {
    viper = new ArmPIDFSubsystem(hwMap);
    viper.resetViper();

    waitForStart();

    CommandScheduler.getInstance().schedule(
      new SequentialCommandGroup(
        new GoToLowChamber(viper),
        new ViperDown(viper),

        new GoToHighChamber(viper),
        new ViperDown(viper),

        new GoToLowBasket(viper),
        new ViperDown(viper),

        new GoToHighBasket(viper),
        new ViperDown(viper),
        new ViperOff(viper)
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
      
  
  
