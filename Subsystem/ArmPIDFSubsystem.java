public class ArmPIDFSubsystem extends SubsystemBase {

  public static double kP = 0.01, kI = 0, kD = 0;
  public static double kF = 0;
  private final PIDFController pidf;
  private int target = 0; 

  //tolerance
  private final int TOLERANCE = 15;

  private final DcMotorEx viper;
  public double power;

  private final Servo joint;
  public double jointPos;
  
  public static final double ticks_per_rev = 537.7;
  
  public static final double cm_per_rev = 3.82 * Math.PI; // perímetro em cm (dSpool*pi) 
  public static final double cm_per_tick = cm_per_rev / ticks_per_rev; 

  //Posições do viper em cm
  public static final double highChamber = 66.0;
  public static final double lowChamber = 33.0;
  public static final double highBasket = 69.6;
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
    
    viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    pidf = new PIDFController(kP, kI, kD, kF); 
  }

  public void pidfUpdate() {

    //limitador de target
    target = Math.max(cmToTicks(MIN_HEIGHT), Math.min(cmToTicks(MAX_HEIGHT), target));

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
    }

   //verifica se chegou ao target
    public boolean atTarget() {
      return Math.abs(viper.getCurrentPosition() - target) < TOLERANCE && Math.abs(viper.getVelocity()) < 15;
      }

  public void resetViper() {
    pidf.reset();
    viper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    viper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    viper.setPower(0);
  }

  //====================================================
  //===============VIPER STATE MACHINE==================
  //====================================================

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

  //====================================================
  //===============JOINT ENUM MODE==================
  //====================================================

  private enum JMode {
    OFF,
    DOWN,
    TO_BASKET,
    TO_CHAMBER
  }

  private JMode jointMode = JMode.OFF;

  public void jointOff() { jointMode = JMode.OFF; }
  public void jointDown() { jointMode = JMode.DOWN; }
  public void jointToBasket() { jointMode = JMode.TO_BASKET; }
  public void jointToChamber() { jointMode = JMode.TO_CHAMBER; }

  public boolean jointAtTarget() {
    boolean targetPos;
    if (joint.getPosition() == jointPos) {
      targetPos = true;
    }
    return targetPos
  }

  @Override
  public void periodic() {

  //====================================================
  //===============VIPER STATE MACHINE==================
  //====================================================
      
    switch (mode) {

      case OFF: 
        viper.setPower(0);
        target = cmToTicks(startPosition);
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

  //====================================================
  //===============VIPER STATE MACHINE==================
  //====================================================

    switch (jointMode) {

      case OFF:
        jointPos = 0.0;
        joint.setPosition(jointPos);
        break;

      case DOWN:
        jointPos = 0.0;
        joint.setPosition(jointPos);
        break;

      case TO_BASKET:
        jointPos = 0.75;
        joint.setPosition(jointPos);
        break;

      case TO_CHAMBER:
        jointPos = 0.34;
        joint.setPosition(jointPos);
        break;
    }
    
 }

  //status

  public int getTarget() { return target; }
  public int getViperPosition() { return viper.getCurrentPosition(); }
  public double getError() { return target - viper.getCurrentPosition(); }
  public double getViperPower() { return power; }
  public double getViperVelocity() { return viper.getVelocity(); }

  public double getJointPosition() { return joint.getPosition(); }

  public String getStatus() {
    return String.format(
      "Mode=%s ViperPos=%d Target=%d Error=%d Power=%.2f Velocity=%.1f JPos=%d",
      mode,
      getViperPosition(),
      getTarget(),
      getError(),
      getViperPower(),
      getViperVelocity(),
      getJointPosition()
      );
 }
  

}
