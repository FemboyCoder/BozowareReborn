package bozoware.client.display.material;

import bozoware.client.display.material.impl.MaterialBody;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiClickMaterial extends GuiScreen {

    private final MaterialBody body;


    public GuiClickMaterial() {
        System.out.println("a");
        this.body = new MaterialBody(50,50,300,300,null);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.body.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
