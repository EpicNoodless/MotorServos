package org.firstinspires.ftc.teamcode.src.main.java.org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;
@Autonomous()
public class C12_State extends OpMode {
    enum State {
        STAGEONE, // 0.25 speed
        STAGETWO, // 0.5 speed
        STAGETHREE, // 0.75 speed
        FULLSPEED, // 1 speed
        STOPED // 0 speed
    }
    State state = State.STAGEONE;
    double lastTime;
    C12_Multiple board = new C12_Multiple();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    public void start() {
        State state = State.STAGEONE;
        resetRuntime();
        lastTime = getRuntime();
    }

    @Override
    public void loop() {
        double motorSpeed = board.motorSpeed;
        board.setMotorSpeed(motorSpeed);
        String x = "?";
        telemetry.addData("Mission Completed ", false);
        telemetry.addData("Motor speed ", motorSpeed);
        telemetry.addData("touchSensor statue ", x);
        telemetry.addData("State ", state);
        telemetry.addData("Run Time ", getRuntime());
        telemetry.addData("Time in State ", getRuntime() - lastTime);
        switch (state) {
            case STAGEONE:
                if (getRuntime() >= 1) {
                    telemetry.addData("State in stage 1: ", state);
                    lastTime = getRuntime();
                    motorSpeed = 0.25;
                    board.setMotorSpeed(motorSpeed);
                    telemetry.addData("Stage 1 Runtime: ", getRuntime());
                    if (getRuntime() >= 3) {
                        state = State.STAGETWO;
                    }
                }
                break;
            case STAGETWO:
                telemetry.addData("Stage 2 Runtime: ", getRuntime());
                    lastTime = getRuntime();
                    motorSpeed = 0.5;
                    board.setMotorSpeed(motorSpeed);
                if  (getRuntime() >= 5)
                    state = State.STAGETHREE;
                break;
            case STAGETHREE:
                lastTime = getRuntime();
                motorSpeed = 0.75;
                board.setMotorSpeed(motorSpeed);
                if (getRuntime() >= 7) {
                    state = State.FULLSPEED;
                }
                break;
            case FULLSPEED:
                lastTime = getRuntime();
                motorSpeed = 1;
                board.setMotorSpeed(motorSpeed);
                if (board.isTouchSensorPressed()) {
                    state = State.STOPED;
                }
                break;
            case STOPED:
                telemetry.addData("Mission Completed ", true);
        }
    }
}
        class C12_Multiple {
            private DcMotor motor;
            private DigitalChannel touchSensor;

            double motorSpeed;

            public void init(HardwareMap hwMap) {
                motor = hwMap.get(DcMotor.class, "motor");
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                touchSensor = hwMap.get(DigitalChannel.class, "touchSensor");
                touchSensor.setMode(DigitalChannel.Mode.INPUT);
            }

            public void setMotorSpeed(double motorSpeed) {
                motor.setPower(motorSpeed);
            }

            public boolean isTouchSensorPressed() {
                return !touchSensor.getState();
            }

            public boolean isTouchSensorReleased() {
                return touchSensor.getState();
            }
        }
