package bozoware.client.display.dropdown.impl.values;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.util.client.ModeEnum;
import bozoware.client.util.input.MouseUtil;
import bozoware.client.util.render.RenderUtil;
import bozoware.client.value.impl.EnumValue;

import javax.vecmath.Vector2f;
import java.io.IOException;

public class ModeComponent extends ValueComponent {

    private ModuleDropdown parent;
    private EnumValue<? extends ModeEnum> value;

    public ModeComponent(ModuleDropdown parent, EnumValue<? extends ModeEnum> value) {
        this.parent = parent;
        this.value = value;
    }

    private float posX, posY;
    @Override
    public void render(float posX, float posY, float partialTicks) {
        this.posX = posX;
        this.posY = posY;
        RenderUtil.drawRectWidth(posX,posY,getComponentWidth(),getComponentHeight(),0xFF3A3A3A);
        Vector2f mouse = MouseUtil.getMousePos();
        mc.fontRendererObj.drawStringWithShadow(value.getValueName(),posX+5,posY+COMPONENT_HEIGHT/2f-mc.fontRendererObj.FONT_HEIGHT/2f,
                hoveredComponent((int) mouse.x, (int) mouse.y) ? 0xFFEEEEEE : 0xFFDADADA);
        int width = mc.fontRendererObj.getStringWidth(value.getValueString());
        mc.fontRendererObj.drawStringWithShadow(value.getValueString(),posX+COMPONENT_WIDTH-width-5,posY+COMPONENT_HEIGHT/2f-mc.fontRendererObj.FONT_HEIGHT/2f, 0xFFDADADA);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(hovered(mouseX,mouseY)) {
            if(mouseButton == 1) {
                value.decrement();
            } else if (mouseButton == 0) {
                value.increment();
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

    }
    public boolean hovered(int mouseX, int mouseY) {
        return mouseX > posX && mouseX < posX + getComponentWidth() &&
                mouseY > posY && mouseY < posY + getComponentHeight();
    }
    public boolean hoveredComponent(int mouseX, int mouseY) {
        return mouseX > posX && mouseX < posX + COMPONENT_WIDTH &&
                mouseY > posY && mouseY < posY + COMPONENT_HEIGHT;
    }
}
