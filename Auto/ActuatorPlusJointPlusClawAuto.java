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
  }
