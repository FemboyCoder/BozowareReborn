package bozoware.client.display.dropdown.impl;

import bozoware.client.display.dropdown.Component;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.module.Module;
import bozoware.client.module.ModuleManager;
import bozoware.client.module.impl.render.HUDModule;
import bozoware.client.util.client.TimerUtil;
import bozoware.client.util.input.MouseUtil;
import bozoware.client.util.render.RenderUtil;
import bozoware.client.value.Value;
import net.minecraft.client.renderer.GlStateManager;

import javax.vecmath.Vector2f;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ModuleDropdown extends Component {

	private ArrayList<ValueComponent> valueComponents = new ArrayList<>();
	private CategoryDropdown parent;
	private Module module;

	public ModuleDropdown(CategoryDropdown parent, Module module) {
		this.parent = parent;
		this.module = module;
		for (Value<?> value : module.getValues()) {
			valueComponents.add(value.getComponent(this));
		}
		lastDegree = 0;
	}

	public Module getModule() {
		return this.module;
	}

	boolean expanded = false;

	@Override
	public float getComponentHeight() {
		float height = COMPONENT_HEIGHT;
		if (expanded) {
			for (ValueComponent component : valueComponents) {
				height += component.getComponentHeight();
			}
		}
		return height;
	}

	private float posX, posY;

	private float lastDegree = 0;
	private final float expandedDegree = 180;
	private final TimerUtil timeSinceLastFrame = new TimerUtil();

	@Override
	public void render(float posX, float posY, float partialTicks) {
		this.posX = posX;
		this.posY = posY;
		Vector2f mouse = MouseUtil.getMousePos();
		Color hudColour = ((HUDModule) ModuleManager.INSTANCE.get(HUDModule.class)).hudColourValue.getCurrentValue();
		int componentColour = module.isEnabled() ?
				hudColour.getRGB() : 0xFF404040;
		componentColour = hoveredComponent((int) mouse.x, (int) mouse.y) ?
				new Color(componentColour).darker().getRGB() : componentColour;
		RenderUtil.drawRectWidth(posX, posY, COMPONENT_WIDTH, COMPONENT_HEIGHT, componentColour);
		mc.fontRendererObj.drawStringWithShadow(module.getModuleName(), posX + 4, posY + (COMPONENT_HEIGHT / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f), 0xFFDADADA);
		posY += COMPONENT_HEIGHT;
		if (valueComponents.size() > 0) {
			/*
			String expandedString = expanded ? "-" : ">";
			expandedColour = hoveredComponent((int) mouse.x, (int) mouse.y) ?
					new Color(expandedColour).brighter().getRGB() : expandedColour;
			mc.fontRendererObj.drawStringWithShadow(expandedString, this.posX + COMPONENT_WIDTH - 10, this.posY + 1 + (COMPONENT_HEIGHT / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f), expandedColour);
			 */

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


			if (expanded) {
				for (ValueComponent valueComponent : valueComponents) {
					valueComponent.render(posX, posY, partialTicks);
					posY += valueComponent.getComponentHeight();
				}
			}
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (hoveredComponent(mouseX, mouseY)) {
			if (mouseButton == 0) {
				module.toggleModule();
			} else if (mouseButton == 1) {
				expanded = !expanded;
				timeSinceLastFrame.reset();
			}
		} else if (expanded) {
			for (ValueComponent valueComponent : valueComponents) {
				valueComponent.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		if (expanded) {
			for (ValueComponent valueComponent : valueComponents) {
				valueComponent.mouseReleased(mouseX, mouseY, state);
			}
		}
	}

	@Override
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (expanded) {
			for (ValueComponent valueComponent : valueComponents) {
				valueComponent.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
			}
		}
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
