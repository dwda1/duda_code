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
//https://www.studocu.com/en-us/document/central-ill-rural-region/ap-physics-c-electricity-magnetism/3110-0002-0001-pinpoint-odometry-computer-user-guide/160519804?sid=71774275-0e01-4b51-a68a-4547cd6313801783367217
