public class ActuatorPIDFSubsystem extends SubsystemBase {
 
  private DcMotorEx actuator;
  public double power;
  public static final double speed = 0.8;
  public static int target = 0;
  public static final int TOLERANCE = 15;
  
  private PIDFController pidf;
  
  public static double kP = 0, kI = 0, kD = 0;
  public static double kF = 0;

  private static final double ticks_per_rev = 537.7;

  //distância percorrida por volta
  private static final double cm_per_rev = 4.96 * Math.PI;
  
  //distancia por tick
  private static final double cm_per_tick = cm_per_rev / ticks_per_rev;

  //posições do atuator
  double MAX_HEIGHT = 
  double MIN_HEIGHT =
  double highChamber =
  double lowChamber =
  double highBasket =
  double lowBasket =
  double initialPosition =

  //convertendo cm para ticks
  public int cmToTicks(double cm) {
    return (int) Math.round(cm / cm_per_tick);
  }

  public ActuatorPIDFSubsystem (HardwareMap hwMap) {
    actuator = hwMap.get(DcMotorEx.clas, "atuator");

    actuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    pidf = new PIDFController(kP, kI, kD, kF);
  }

  public void pidfUpdate() {
   //limitador 
   target = Math.max(cmToTicks(MIN_HEIGHT), Math.min(cmToTicks(MAX_HEIGHT), target));

   pidf.setPIDF(kP, kI, kD, kF);

   //calcula o pid
   int posActuator = actuator.getCurrentPosition();
   double pid = pidf.calculate(posActuator, target);
   double ff = kF;
   
   //aplica o pid na potencia do motor
   double output = pid + ff;
   output = Math.max(-1.0, Math.min(1.0, output));
   power = (output * speed);
   actuator.setPower(power);
  }

  public boolean atTarget() {
    return Math.abs(actuator.getCurrentPosition() - target) < TOLERANCE && Math.abs(actuator.getVelocity()) < 15;
  }

  public void resetActuator() {
    pidf.reset();
    actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    actuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    actuator.setPower(0);
  }

  //modos

  private enum Mode {
    OFF,
    DOWN,
    LOW_CHAMBER,
    HIGH_CHAMBER,
    LOW_BASKET,
    HIGH_BASKET
  }

  private Mode mode = Mode.OFF;

  public void actuatorOff() { mode = Mode.OFF; }
  public void actuatorDown() { mode = Mode.DOWN; }
  public void actuatorLowChamber() { mode = Mode.LOW_CHAMBER; }
  public void actuatorHighChamber() { mode = Mode.HIGHT_CHAMBER; }
  public void actuatorLowBasket() { mode = Mode.LOW_BASKET; }
  public void actuatorHighBasket() { mode = Mode.HIGHT_BASKET; }

  @Override 
  public void periodic() {

   //state machine
   switch (mode) {

    case OFF:
      actuator.setPower(0);
      break;

    case DOWN:
      target = cmToTicks(initialPosition);
      break;

    case LOW_CHAMBER: 
      target = cmToTicks(lowChamber);
      break;

    case HIGH_CHAMBER:
      target = cmToTicks(highChamber);
      break;

    case LOW_BASKET:
      target = cmToTicks(lowBasket);
      break;

    case HIGH_BASKET:
      target = cmToTicks(highBasket);
      break;
   }
   
    if (mode != Mode.OFF) {
      pidfUpdate();
    }
  }

  //status
  public int getTarget() { return target; }
  public int getActuatorPosition() { return actuator.getCurrentPosition(); }
  public double getError() { return target - actuator.getCurrentPosition(); }
  public double getActuatorPower() { return power; }
  public double getActuatorVelocity() { return actuator.getVelocity(); }

  public String getActuatorSTATUS() {
    return String.format(
      "Mode=%s Target=%d ActuatorPos=%d Error=%.2f Power=%.2f Velocity=%.2f",
     mode,
     getTarget(),
     getActuatorPosition(),
     getError(),
     getActuatorPower(),
     getActuatorVelocity()
     );
  }
}
 
  //a distância percorrida pelo actuator = ao perímetro da engrenagem acoplada ao motor
  // o diâmetro da engrenagem = 4,96 cm
  // p = d.r => 4.96 * Math.PI
  // ticks per rev = 145.6 => 1150 RPM
  // ticks per rev = 537.7 => 312 RPM
  // para saber quantos ticks tem um motor, pega a resolução do encoder (28 contagens per rev => 28 PPR) e multiplica pela redução
