package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class DriverSubsystem extends SubsystemBase {

    private DcMotorEx rightFront, rightBack, leftFront, leftBack;
    private GoBildaPinpointDriver pinpoint;

    public DriverSubsystem (HardwareMap hwMap) {
        rightFront = hwMap.get(DcMotorEx.class, "RMF");
        rightBack = hwMap.get(DcMotorEx.class, "RMB");
        leftFront = hwMap.get(DcMotorEx.class, "LMF");
        leftBack = hwMap.get(DcMotorEx.class, "LMB");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void periodic() {

    }
}

//link da documentação: https://www.gobilda.com/content/user_manuals/3110-0002-0001%20User%20Guide.pdf
