package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;


public class Arm extends SubsystemBase {
    private DcMotorEx armMotor;
    private Servo articulationArm;

    public static double kP;
    public static double kI;
    public static double kD;
    public static double kF;

    private final PIDFController pidf = new PIDFController(kP, kI, kD, kF);

    public static double TICKS_PER_REV = 537.7;
    double motorPos = armMotor.getCurrentPosition();
    double revolutions = motorPos/TICKS_PER_REV;
    public static double targetRPM;
    double angle = revolutions * 360;
    double angleNormalized = angle % 360;

    int armHigh = 1000; //ticks value
    int armLow = 0;

    boolean up = true;
    double power = 0;


    public Arm(HardwareMap hwMap) {
        armMotor = hwMap.get(DcMotorEx.class, "armMotor");
        articulationArm = hwMap.get(Servo.class, "jointArm");

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double armPos(int pos) {
        articulationArm.setPosition(pos);
        return pos;
    }
    public void armUP (){
        up = true;
    }
    public void armDown (){
        up = false;
    }
    public void OFF (){
        up = false;
        power = 0;
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTargetRPM(double rpm) {
        targetRPM = rpm;
    }
    public double getTargetRPM() {
        return targetRPM;
    }

    public double getCurrentRPM() {
        return (armMotor.getVelocity() / TICKS_PER_REV) * 60.0;
    }

    @Override
    public void periodic() {

        pidf.setPIDF(kP, kI, kD, kF);

        double currentTPS = armMotor.getVelocity();
        double targetTPS = (currentTPS / 60.0) * TICKS_PER_REV;

        if(up) {
            double ff = targetTPS * kF;
        }
    }

    }
