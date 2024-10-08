package org.firstinspires.ftc.teamcode.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Config;

public class Forebar extends SubSystem {
    private DcMotor forebar;

    public boolean forebarInside;

    public Forebar(Config config, boolean isOneController) {
        super(config, isOneController);
    }

    public Forebar(Config config) {
        super(config);
    }

    @Override
    public void init() {
        forebar = config.hardwareMap.get(DcMotor.class, "forebar");

        forebar.setDirection(DcMotorSimple.Direction.FORWARD);

        forebar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        forebar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        forebarInside = true;
    }

    @Override
    public void update() {
        config.telemetry.addData("forebar", forebar.getCurrentPosition());

        if (config.gamePad1.left_bumper) {
            if (forebarInside) {
                forebar.setTargetPosition(750);

                forebar.setPower(0.8);

                forebar.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                forebarInside = false;
            } else {
                forebar.setTargetPosition(0);

                forebar.setPower(0.8);

                forebar.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                forebarInside = true;
            }
        }
    }
}
