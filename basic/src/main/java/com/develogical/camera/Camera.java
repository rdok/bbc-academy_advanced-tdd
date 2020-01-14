package com.develogical.camera;

public class Camera implements WriteListener
{
    private Sensor sensor;
    private MemoryCard memoryCard;
    private Boolean power;
    private Boolean currentlyBeingWritten = false;

    public Camera(Sensor sensor, MemoryCard memoryCard)
    {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter()
    {
        if (this.power) {
            this.currentlyBeingWritten = true;
            this.memoryCard.write(this.sensor.readData());
        }
    }

    public void powerOn()
    {
        this.sensor.powerUp();
        this.power = true;
    }

    public void powerOff()
    {
        if (!this.currentlyBeingWritten) {
            this.sensor.powerDown();
            this.power = false;
        }
    }

    @Override
    public void writeComplete()
    {
        this.sensor.powerDown();
    }
}

