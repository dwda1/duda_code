public class AutoArm extends LinearOpMode {

  private ArmPIDFSubsystem viper;
  private JointSubsystem joint;

  @Override
  public void runOpMode() throws InterruptedException {
    viper = new ArmPIDFSubsystem(hardwareMap);
    viper.resetViper();

    joint = new JointSubsystem(hardwareMap);

    waitForStart();

    CommandScheduler.getInstance().schedule(
      new SequentialCommandGroup(
        new GoToLowChamber(viper),
        new JointToChamber(joint),
        new JointDown(joint),
        new ViperDown(viper),

        new GoToHighChamber(viper),
        new JointToChamber(joint),
        new JointDown(joint),
        new ViperDown(viper),

        new GoToLowBasket(viper),
        new JointToBasket(joint),
        new JointDonw(joint),
        new ViperDown(viper),

        new GoToHighBasket(viper),
        new JointToBasket(joint),
        new JointDown(joint),
        new ViperDown(viper),
        new ViperOff(viper)
        )
      );

    while (opModeIsActive()) {

      CommandScheduler.getInstance().run();

      telemetry.addData("status: ", viper.getStatus());
      telemetry.addData("Joint status: ", joint.getJointStatus());
      telemetry.update();
    }

    CommandScheduler.getInstance().reset();
  }
}
      
  
  
