public class ArmPIDFSubsystem extends SubsystemBase {

  public static double kP = 0.01, kI = 0, kD = 0;
  public static double kF = 0;
  public static Controller pidf;
  public static int target = 0; 

  //tolerance
  private final int TOLERANCE = 15;

  private final DcMotorEx viper;
  public double power;

  public final double ticks_per_rev = 537.7;

  public final double degrees_per_tick = 360.0 / 537.7; //grau por tick
  
  public final double cm_per_rev = 3.82 * Math.PI; // perímetro em cm (dSpool*pi) 
  public final double cm_per_tick = cm_per_rev / ticks_per_rev; 

  //Posições do viper em cm
  public static final double highChamber = 66.0;
  public static final double lowChamber = 33.0;
  public static final double highBasket = 109.2;
  public static final double lowBasket = 65.4;
  public static final double startPosition = 0;
  public static final double MAX_HEIGHT = 69.6;
  public static final double MIN_HEIGHT = 0;

  //conversão cm => ticks
  public int cmToTicks(double cm) {
    return (int) (cm / cm_per_tick);
  } 

  //Construtor
  public ArmPIDFSubsystem(HardwareMap hwMap) {
    viper = hwMap.get(DcMotorEx.class, "viper");
    
    viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    pidf = new Controller(kP, kI, kD, kF); 
  }

  public void pidfUpdate() {

    //limitador de target
    target = Math.max(cmToTicks(MIN_HEEIGHT), Math.min(cmToTicks(MAX_HEIGHT), target));

    //atualizar os valores de pidf
    pidf.setPIDF(kP, kI, kD, kF);

    int posViper = viper.getCurrentPosition();

    double pid = pidf.calculate(posViper, target);
    double ff = kF;

    //calcular o power
    double output = pid + ff;
    output = Math.max(-1.0, Math.min(1.0, output));
    double speed = 0.8;
    power = (speed * output);
    viper.setPower(power);

    //verifica se chegou
    public boolean atTarget() {
      return Math.abs(posViper - target) < TOLERANCE && Math.abs(viper.getVelocity()) < 15;
      
    }
    }

  public void resetViper() {
    pidf.reset();
    viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    viper.setPower(0);
  }

  private enum Mode {
    OFF,
    DOWN,
    TO_HIGH_CHAMBER,
    TO_LOW_CHAMBER,
    TO_HIGH_BASKET,
    TO_LOW_BASKET
  }

  private Mode mode = Mode.OFF;

  public void viperOff() { mode = Mode.OFF;}
  public void viperDown() { mode = Mode.DOWN; }
  public void viperToHighChamber() { mode = Mode.TO_HIGH_CHAMBER; }
  public void viperToLowChamber() { mode = Mode.TO_LOW_CHAMBER; }
  public void viperToHighBasket() { mode = Mode.TO_HIGH_BASKET; }
  public void viperToLowBasket() { mode = Mode.TO_LOW_BASKET; }

  @Override
  public void periodic() {

  //state machine
    switch (mode) {

      case OFF: 
        viper.setPower(0);
        target = startPosition;
        break;

      case DOWN: 
        target = cmToTicks(startPosition);
        break;

      case TO_HIGH_CHAMBER:
        target = cmToTicks(highChamber);
        break;
      
      case TO_LOW_CHAMBER:
        target = cmToTicks(lowChamber);
        break;

      case TO_HIGH_BASKET:
        target = cmToTicks(highBasket);
        break; 

      case TO_LOW_BASKET:
        target = cmToTicks(lowBasket);
        break; 
        }

    if (mode != Mode.OFF) {
      pidfUpdate();
    }

  }




}
