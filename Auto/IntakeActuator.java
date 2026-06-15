public class IntakeAuto extends LinearOpMode {
  private ActuatorPIDFSubsystem actuator;
  private JointSubsystem joint;
  private ClawSubsystem claw;

  @Override 
  public vois runOpMode() throws InterruptedException {
    actuator = new ActuatorPIDFSubsystem(hardwareMap);
    actuator.resetActuator();

    joint = new JointSubsystem(hardwareMap);
    claw = new ClawSubsystem(hardwareMap);

    waitForStart();

    CommandScheduler.getInstance().schedule(
      new SequentialCommandGroup(
        new ActuatorTake(actuator),
        new ClawOpen(claw),
        new ClawClose(claw),
        new ActuatorTransfer(actuator),
        new JointToChamber(joint),
        new ClawOpen(claw),
        new JointDown(joint),
        new ActuatorBack(actuator)
        )
    );

    while(opModeIsActive()) {
      CommandScheduler.getInstance().run();

      telemetry.addData("Actuator Status:", actuator.getActuatorSTATUS());
      telemetry.addData("Joint Status: ", joint.getJointStatus());
      telemetry.addData("Claw Status: ", claw.getClawStatus());
      telemetry.update();
    }

      CommandScheduler.getInstance().reset();
  }
}
