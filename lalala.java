public class ArmSubsystem extends SubsystemBase{

private final DcMotorEx motor;
private final Servo servo;

public double TICKS_PER_REV = 537.7;
public double motorPos = motor.getCurrentPosition();
public double spoolCirc = 38.2 * Math.PI; //perímetro (d*pi);
  
public static double kP = 0.01;
public static double kI = 0;
public static double kD = 0;
public static double kF = 0.1; 

public ArmSubsystem (HardwareMap hwMap) {
  motor = hwMap.get(DcMotorEx.class, "viper");
  servo = hwMap.get(Servo.class, "joint");

  motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
  motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
  }

//======================Conversão de ticks para cm====================

  public double ticksToCm (int ticks) {
    return (ticks / TICKS_PER_REV) * spoolCirc; //ticks/tpr = número de voltas
}
  // a cada volta completa da polia GT2 de 60 dentes, o kit se move 120mm
  // então, para calcular o número de voltas que o motor dará para alcançar uma determinada distância, deve-se dividir a distância (em mm) por 120mm 
  // em 5,8 voltas o motor gira 3.118,66 ticks ~= 3.118 ticks (5,8 * TICKS_PER_REV), 5,8 é o n de voltas necessárias para que o Viper se extenda ao máximo 696mm ou 69,6 cm
  // ou seja, o valor em ticks que eu devo inserir para obter a extensão máxima do Viper é 3.118 ticks
  //, porém a altura máxima será definida pela altura dos elementos de jogo, então, vou ter que refazer


public static double returnKitPIDF (double target, double posArm) {
  
  private final PIDFController pidf = new PIDFController(kP, kI, kD, kF); 
  pidf.setPIDF(kP, kI, kD, kF);
 

  
  double pid = pidf.calculate(posArm, target);




  
}
