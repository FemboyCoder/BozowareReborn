package bozoware.client.display.dropdown.impl.values;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.util.client.TimerUtil;
import bozoware.client.util.input.MouseUtil;
import bozoware.client.util.render.RenderUtil;
import bozoware.client.value.impl.DropdownValue;
import net.minecraft.client.renderer.GlStateManager;

import javax.vecmath.Vector2f;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DropdownComponent extends ValueComponent {

    private final ModuleDropdown parent;
    private final DropdownValue value;

    private final List<ValueComponent> componentList = new ArrayList<>();


    private boolean expanded = false;

    public DropdownComponent(ModuleDropdown parent, DropdownValue value) {
        this.parent = parent;
        this.value = value;

        this.value.getCurrentValue().forEach( dropdownValue -> {
            this.componentList.add(dropdownValue.getComponent(parent));
        } );

    }

    private float posX, posY;

    private float lastDegree = 0;
    private final float expandedDegree = 180;
    private final TimerUtil timeSinceLastFrame = new TimerUtil();

    @Override
    public void render(float posX, float posY, float partialTicks) {
        this.posX = posX;
        this.posY = posY;
        RenderUtil.drawRectWidth(posX,posY,getComponentWidth(),getComponentHeight(),0xFF3A3A3A);
        Vector2f mouse = MouseUtil.getMousePos();
        mc.fontRendererObj.drawStringWithShadow(value.getValueName(),posX+5,posY+COMPONENT_HEIGHT/2f-mc.fontRendererObj.FONT_HEIGHT/2f,
                hoveredComponent((int) mouse.x, (int) mouse.y) ? 0xFFEEEEEE : 0xFFDADADA);
        if(componentList.size() >= 1) {
            if(expanded && lastDegree != expandedDegree) {
                lastDegree = Math.min(expandedDegree, lastDegree + Math.max(0,(0.5f * timeSinceLastFrame.elapsed())));
            } else if (!expanded && lastDegree != 0) {
                lastDegree = Math.max(0, lastDegree -  Math.max(0,(0.5f * timeSinceLastFrame.elapsed())));
            }
            timeSinceLastFrame.reset();

            float middleY = this.posY + 4.6f + (COMPONENT_HEIGHT / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f);
            float middleX = this.posX+COMPONENT_WIDTH-7.5f;

            GlStateManager.pushMatrix();
            GlStateManager.translate(middleX, middleY, 0);
            GlStateManager.rotate(lastDegree, 0,0,0);
            GlStateManager.translate(-middleX, -middleY, 0);
            RenderUtil.drawLine(new Color(0xFFDADADA), 2.5f,
                    new Vector2f(middleX+2.5f, middleY+3),
                    new Vector2f(middleX-2.5f, middleY),
                    new Vector2f(middleX+2.5f, middleY-3));
            GlStateManager.popMatrix();
        }
        if(expanded) {
            posY += COMPONENT_HEIGHT;
            int index = 0;
            for(ValueComponent valueComponent : componentList) {
                float newY = posY+(index*COMPONENT_HEIGHT);
                valueComponent.render(posX, newY, partialTicks);
                index++;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(hoveredComponent(mouseX,mouseY)) {
            this.expanded = !this.expanded;
        }
        if(expanded) {
            for(ValueComponent valueComponent : componentList) {
                valueComponent.mouseClicked(mouseX,mouseY,mouseButton);
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if(expanded) {
            for(ValueComponent valueComponent : componentList) {
                valueComponent.mouseReleased(mouseX,mouseY,state);
            }
        }
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if(expanded) {
            for(ValueComponent valueComponent : componentList) {
                valueComponent.mouseClickMove(mouseX,mouseY,clickedMouseButton, timeSinceLastClick);
            }
        }
    }

    public boolean hoveredComponent(int mouseX, int mouseY) {
        return mouseX > posX && mouseX < posX + COMPONENT_WIDTH &&
                mouseY > posY && mouseY < posY + COMPONENT_HEIGHT;
    }

    @Override
    public float getComponentHeight() {
        return super.getComponentHeight()+ (expanded ? (COMPONENT_HEIGHT*value.getCurrentValue().size()) : 0);
    }
}
