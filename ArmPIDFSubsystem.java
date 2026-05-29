public class ArmPIDFSubsystem extends SubsystemBase {

  public static double kP = 0.01, kI = 0, kD = 0;
  public static double kF = 0;
  public static Controller pidf;
  public static target = 0; 

  private final DcMotorEx viper;
  public double power;

  public final double degrees_per_tick = 360.0 / 537.7; //grau por tick

  //Posições do viper
  public static final highChamber;
  public static final lowChamber;
  public static final highBasket;
  public static final lowBasket;
  public static final startPosition = 0;
  public static final MAX_HEIGHT;
  public static final MIN_HEIGHT;

  //Construtor
  public ArmPIDFSubsystem(HardwareMap hwMap) {
    viper = hwMap.get(DcMotorEx.class, "viper");
    
    viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    pidf = new Controller(kP, kI, kD, kF); 
  }

  public void pidfUpdate() {

    //atualizar os valores de pidf
    pidf.setPIDF(kP, kI, kD, kF);

    int posViper = viper.getCurrentPosition();

    double pid = pidf.calculate(posViper, target);
    double ff = Math.cos(Math.toRadians(target * degrees_per_tick)) * kF;

    //calcular o power
    double output = pid + ff;
    output = Math.max(-1.0, Math.min(1.0, output));
    double speed = 0.8;
    power = (speed * output);
    }

 //Métodos
 public 
 





}
