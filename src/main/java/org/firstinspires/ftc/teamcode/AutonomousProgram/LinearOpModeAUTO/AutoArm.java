package org.firstinspires.ftc.teamcode.AutonomousProgram.LinearOpModeAUTO;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.GoToHighBasket;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.GoToHighChamber;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.GoToLowBasket;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.GoToLowChamber;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.ViperDown;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.ViperOff;
import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ArmPIDFSubsystem;

public class AutoArm extends LinearOpMode {
    private ArmPIDFSubsystem viper;

    @Override
    public void runOpMode() throws InterruptedException {
        viper = new ArmPIDFSubsystem(hardwareMap);
        viper.resetViper();

        waitForStart();

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new GoToLowChamber(viper),
                        new ViperDown(viper),

                        new GoToHighChamber(viper),
                        new ViperDown(viper),

                        new GoToLowBasket(viper),
                        new ViperDown(viper),

                        new GoToHighBasket(viper),
                        new ViperDown(viper),
                        new ViperOff(viper)
                )
        );

        while (opModeIsActive()) {

            CommandScheduler.getInstance().run();

            telemetry.addData("Viper status: ", viper.getStatus());
            telemetry.update();
        }

        CommandScheduler.getInstance().reset();

    }
}
