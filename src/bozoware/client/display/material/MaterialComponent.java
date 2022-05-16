package bozoware.client.display.material;

import bozoware.client.display.material.impl.MaterialBody;

import java.util.Arrays;
import java.util.List;

public abstract class MaterialComponent {

    private float componentWidth, componentHeight, componentX, componentY;

    public MaterialComponent(float componentX, float componentY, float componentWidth, float componentHeight, MaterialComponent parent) {
        this.componentWidth = componentWidth;
        this.componentHeight = componentHeight;
        this.componentX = componentX;
        this.componentY = componentY;
        this.parent = parent;
    }

    private final MaterialComponent parent;
    private List<MaterialComponent> children;

    public MaterialComponent getParent() {
        return parent;
    }
    public List<MaterialComponent> getChildren() {
        return this.children;
    }
    public void addChildren(MaterialComponent... children) {
        this.children.addAll(Arrays.asList(children));
    }
    public void addChild(MaterialComponent child) {
        this.children.add(child);
    }

    // COMPONENT METHODS

    public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);

    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);
    public abstract void mouseReleased(int mouseX, int mouseY, int state);

    public abstract void keyTyped(char typedChar, int keyCode);

    // GETTERS & SETTERS

    public float getComponentWidth() {
        return componentWidth;
    }
    public void setComponentWidth(float componentWidth) {
        this.componentWidth = componentWidth;
    }
    public float getComponentHeight() {
        return componentHeight;
    }
    public void setComponentHeight(float componentHeight) {
        this.componentHeight = componentHeight;
    }
    public float getComponentX() {
        return componentX;
    }
    public void setComponentX(float componentX) {
        this.componentX = componentX;
    }
    public float getComponentY() {
        return componentY;
    }
    public void setComponentY(float componentY) {
        this.componentY = componentY;
    }

}
