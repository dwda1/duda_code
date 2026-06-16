package org.firstinspires.ftc.teamcode.AutonomousProgram.LinearOpModeAUTO.actuator;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator.ActuatorBack;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator.ActuatorOff;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator.ActuatorTake;
import org.firstinspires.ftc.teamcode.AutonomousProgram.ComandosArm.actuator.ActuatorTransfer;
import org.firstinspires.ftc.teamcode.AutonomousProgram.SubsystemArm.ActuatorPIDFSubsystem;

@Autonomous
public class ActuatorAuto extends LinearOpMode {
    private ActuatorPIDFSubsystem actuator;

    @Override
    public void runOpMode() throws InterruptedException {
        actuator = new ActuatorPIDFSubsystem(hardwareMap);
        actuator.resetActuator();

        waitForStart();

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(

                        new ActuatorTake(actuator),

                        new ActuatorBack(actuator),

                        new ActuatorTransfer(actuator),

                        new ActuatorBack(actuator),

                        new ActuatorOff(actuator)
                )
        );

        while(opModeIsActive()) {
            CommandScheduler.getInstance().run();

            telemetry.addData("Actuator Status:", actuator.getActuatorSTATUS());
            telemetry.update();
        }

        CommandScheduler.getInstance().reset();
    }
}
