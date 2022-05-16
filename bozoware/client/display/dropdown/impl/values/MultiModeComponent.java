package bozoware.client.display.dropdown.impl.values;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.module.ModuleManager;
import bozoware.client.module.impl.render.HUDModule;
import bozoware.client.util.client.ModeEnum;
import bozoware.client.util.input.MouseUtil;
import bozoware.client.util.render.RenderUtil;
import bozoware.client.value.impl.MultiEnumValue;

import javax.vecmath.Vector2f;
import java.io.IOException;

public class MultiModeComponent extends ValueComponent {

    private ModuleDropdown parent;
    private MultiEnumValue<? extends ModeEnum> value;
    private boolean expanded = false;

    public MultiModeComponent(ModuleDropdown parent, MultiEnumValue<?> value) {
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
        String expandedString = expanded ? "-" : ">";
        int expandedColour = expanded ? 0xFFBABABA : 0xFFDADADA;
        mc.fontRendererObj.drawStringWithShadow(expandedString, this.posX + COMPONENT_WIDTH - 10, this.posY + 1 + (COMPONENT_HEIGHT / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f), expandedColour);
        if(expanded) {
            posY += COMPONENT_HEIGHT;
            int index = 0;
            for(String mode : value.getAllValuesString()) {
                int width = mc.fontRendererObj.getStringWidth(mode);
                boolean enabled = value.isEnabled(index);
                HUDModule hudModule = (HUDModule) ModuleManager.INSTANCE.get(HUDModule.class);
                int modeColour = enabled ? hudModule.hudColourValue.getCurrentValue().getRGB() : 0xffdadada;
                mc.fontRendererObj.drawStringWithShadow(mode, this.posX + COMPONENT_WIDTH/2-width/2f, posY + 1 + (COMPONENT_HEIGHT / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f), modeColour);
                posY += COMPONENT_HEIGHT;
                index++;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(hoveredComponent(mouseX,mouseY)) {
            if(mouseButton == 0 || mouseButton == 1) {
                expanded = !expanded;
            }
        } else if (expanded && hovered(mouseX,mouseY) && mouseButton == 0) {
            int clickedIndex = (int) ((mouseY - this.posY) / COMPONENT_HEIGHT)-1;
            value.toggleValue(clickedIndex);
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

    private boolean hoveredComponent(int mouseX, int mouseY) {
        return mouseX > posX && mouseX < posX + COMPONENT_WIDTH &&
                mouseY > posY && mouseY < posY + COMPONENT_HEIGHT;
    }

    @Override
    public float getComponentHeight() {
        return expanded ? super.getComponentHeight()+(value.getAllValues().size()*COMPONENT_HEIGHT) :super.getComponentHeight();
    }
}
