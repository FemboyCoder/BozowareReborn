package bozoware.client.event.player;

import best.azura.eventbus.events.EventCancellable;

public class EventUpdate extends EventCancellable {

    private double posX, posY, posZ;
    private float rotationYaw, rotationPitch;
    private boolean onGround;

    public EventUpdate(double posX, double posY, double posZ, float rotationYaw, float rotationPitch, boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
    }

    public double getPosX() {
        return posX;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public double getPosY() {
        return posY;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }
    public double getPosZ() {
        return posZ;
    }
    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }
    public float getRotationYaw() {
        return rotationYaw;
    }
    public void setRotationYaw(float rotationYaw) {
        this.rotationYaw = rotationYaw;
    }
    public float getRotationPitch() {
        return rotationPitch;
    }
    public void setRotationPitch(float rotationPitch) {
        this.rotationPitch = rotationPitch;
    }
    public boolean isOnGround() {
        return onGround;
    }
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setPos(double posX, double posY, double posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
    public void setRotations(float rotationYaw, float rotationPitch) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
    }

}
