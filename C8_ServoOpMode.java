package org.firstinspires.ftc.teamcode.src.main.java.org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

@TeleOp()
public class C8_ServoOpMode extends OpMode {
    PB_Servo board = new PB_Servo();
    @Override
    public void init() {
         board.init(hardwareMap);
    }

    @Override
    public void loop() {
        double servoSpeed = gamepad1.left_stick_x;
        board.setServoSpeed(servoSpeed);

        telemetry.addData("Servo speed", servoSpeed);
    }
}

 class PB_Servo {
    private Servo servo;

    public void init(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "servo");
    }
    public void setServoSpeed(double speed){
        servo.setPosition(speed);
    }
    
 }

