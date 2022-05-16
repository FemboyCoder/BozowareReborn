package bozoware.client.display.material.impl;

import bozoware.client.display.material.MaterialComponent;
import bozoware.client.module.ModuleCategory;
import net.minecraft.client.gui.Gui;

public class MaterialCategory extends MaterialComponent {


    public MaterialCategory(float offsetY, MaterialComponent parent) {
        super(
                parent.getComponentX(),
                parent.getComponentY()+(offsetY*(ModuleCategory.values().length - 1)),
                50,
                parent.getComponentHeight()/(ModuleCategory.values().length - 1),
                parent
        );
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(getComponentX(), getComponentY(), getComponentWidth(), getComponentHeight(), 0xFFDADADA);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }
}
