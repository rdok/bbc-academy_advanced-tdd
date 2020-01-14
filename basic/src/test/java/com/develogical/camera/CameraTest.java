package com.develogical.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest
{

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Sensor sensor = context.mock(Sensor.class);
    MemoryCard memoryCard = context.mock(MemoryCard.class);
    Camera camera = new Camera(sensor, memoryCard);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor()
    {
        context.checking(new Expectations()
        {{
            exactly(1).of(sensor).powerUp();
        }});

        camera.powerOn();
    }
    @Test
    public void switchingTheCameraOffPowersDownTheSensor()
    {
        context.checking(new Expectations()
        {{
            exactly(1).of(sensor).powerDown();
        }});

        camera.powerOff();
    }
    @Test
    public void pressingTheCameraShutterWhenThePowerIsOffDoesNothing()
    {
        context.checking(new Expectations()
        {{
            exactly(1).of(sensor).powerDown();
            never(sensor).readData();
        }});

        camera.powerOff();
        camera.pressShutter();
    }

    @Test
    public void pressingTheCameraShutterWhenThePowerIsOnCopiesDataFromTheSensorToMemoryCard()
    {
        context.checking(new Expectations()
        {{
            byte[] data = "cyberpunk2077".getBytes();

            exactly(1).of(sensor).powerUp();
            exactly(1).of(sensor).readData(); will(returnValue(data));
            exactly(1).of(memoryCard).write(data);
        }});

        camera.powerOn();
        camera.pressShutter();
    }
    @Test
    public void ifdataIsCurrentlyWrittenSwitchingThecameraOffDoesNotPowerDownTheSensor()
    {
        context.checking(new Expectations()
        {{
            byte[] data = "cyberpunk2077".getBytes();

            exactly(1).of(sensor).powerUp();
            exactly(1).of(sensor).readData(); will(returnValue(data));
            exactly(1).of(memoryCard).write(data);
            never(sensor).powerDown();

        }});

        camera.powerOn();
        camera.pressShutter();
        camera.powerOff();
    }

    @Test
    public void onceWritingThedataHasCompletedThenCameraPowersDownTheSensor()
    {
        context.checking(new Expectations()
        {{
            exactly(1).of(sensor).powerUp();
            exactly(1).of(sensor).powerDown();
        }});

        camera.powerOn();
        camera.writeComplete();
    }
}
