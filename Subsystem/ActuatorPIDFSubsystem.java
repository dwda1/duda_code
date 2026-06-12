public class ActuatorPIDFSubsystem extends SubsystemBase {
 
  private DcMotorEX actuator;
  public double power;
  public static target = 0;
  
  private PIDFController pidf;
  
  public static double kP = 0, kI = 0, kD = 0;
  public static double kF = 0;

  //distância percorrida por volta
  double cm_per_rev = 4.96 * Math.PI;
  double ticks_per_rev = 537.7;
  
  //nessa distância ele roda esse número de ticks
  double cm_per_tick = cm_per_rev / ticks_per_rev;

  //convertendo cm para ticks
  public int cmToTicks(int cm) {
    return (int) (cm / cm_per_tick);
  }

  public AtuatorPIDFSubsystem (HardwareMap hwMap) {
    atuator = hwMap.get(DcMotor.clas, "atuator");

    atuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    atuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    pidf = new PIDFController(kP, kI, kD, kF);
  }

  publi

  //a distância percorrida pelo actuator = ao perímetro da engrenagem acoplada ao motor
  // o diâmetro da engrenagem = 4,96 cm
  // p = d.r => 4.96 * Math.PI
  // ticks per rev = 145.6 => 1150 RPM
  // ticks per rev = 537.7 => 312 RPM
  // para saber quantos ticks tem um motor, pega a resolução do encoder (28 contagens per rev => 28 PPR) e multiplica pela redução
  
