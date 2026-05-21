package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;


public class Arm extends SubsystemBase {
    private final DcMotorEx armMotor;
    private final Servo articulationArm;

    public static double kP;
    public static double kI;
    public static double kD;
    public static double kF;

    private final PIDFController pidf = new PIDFController(kP, kI, kD, kF);

    public static double TICKS_PER_REV = 537.7;
    double motorPos = armMotor.getCurrentPosition();
    double revolutions = motorPos/TICKS_PER_REV;
    double targetRPM;
    double angle = revolutions * 360;
    double angleNormalized = angle % 360;

    public Arm(HardwareMap hwMap) {
        armMotor = hwMap.get(DcMotorEx.class, "armMotor");
        articulationArm = hwMap.get(Servo.class, "jointArm");

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    
}
