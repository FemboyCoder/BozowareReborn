package bozoware.client.display.material.impl;

import bozoware.client.display.material.MaterialComponent;
import bozoware.client.module.ModuleCategory;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

public class MaterialBody extends MaterialComponent {


    public MaterialBody(float componentWidth, float componentHeight, float componentX, float componentY, MaterialComponent parent) {
        super(componentWidth, componentHeight, componentX, componentY, null);
        int i = 0;
        for (ModuleCategory category : ModuleCategory.values()) {
            if(category == ModuleCategory.HIDDEN) continue;
            addChild(new MaterialCategory(i,this));
            i++;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(getComponentX(), getComponentY(), getComponentWidth(), getComponentHeight(), 0xFF303030);
        getChildren().forEach(component -> component.drawScreen(mouseX,mouseY,partialTicks));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        getChildren().forEach(component -> component.mouseClicked(mouseX,mouseY,mouseButton));

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        getChildren().forEach(component -> component.mouseReleased(mouseX,mouseY,state));

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        getChildren().forEach(component -> component.keyTyped(typedChar,keyCode));
    }
}
