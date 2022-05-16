package bozoware.client.module.impl.render;

import bozoware.client.display.dropdown.GuiDropdown;
import bozoware.client.module.Module;
import bozoware.client.module.ModuleAnnotation;
import bozoware.client.module.ModuleCategory;
import org.lwjgl.input.Keyboard;

@ModuleAnnotation(name = "ClickGUI", category = ModuleCategory.RENDER, key = Keyboard.KEY_RSHIFT)
public class ClickGUIModule extends Module {

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(new GuiDropdown());
        this.forceEnabled(false);
    }

    @Override
    protected void onDisable() {

    }
}
