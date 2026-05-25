package org.firstinspires.ftc.teamcode.src.main.java.org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp()
public class C7_MotorOpMode extends OpMode {
    PB_Motor board = new PB_Motor();
    @Override
    public void init() {
         board.init(hardwareMap);
    }

    @Override
    public void loop() {
        double motorSpeed = gamepad1.left_stick_y;
        board.setMotorSpeed(motorSpeed);

        telemetry.addData("Motor speed", motorSpeed);
    }
}

 class PB_Motor {
    private DcMotor motor;

    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setMotorSpeed(double speed){
        motor.setPower(speed);

    }
}
