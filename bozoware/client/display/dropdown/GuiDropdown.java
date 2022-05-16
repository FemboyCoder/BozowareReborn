package bozoware.client.display.dropdown;

import bozoware.client.display.dropdown.impl.CategoryDropdown;
import bozoware.client.module.ModuleCategory;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GuiDropdown extends GuiScreen {

    ArrayList<CategoryDropdown> categoryDropdowns = new ArrayList<>();

    public GuiDropdown() {
        initDropdowns();
    }

    private void initDropdowns() {
        float baseX = 20;
        float baseY = 50;
        for(ModuleCategory category : ModuleCategory.values()) {
            if(category == ModuleCategory.HIDDEN) continue;
            categoryDropdowns.add(new CategoryDropdown(this, category, baseX, baseY));
            baseX += Component.COMPONENT_WIDTH + 10;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(0,0,mc.displayWidth,mc.displayHeight,0x44222222);
        Collections.reverse(categoryDropdowns);
        for(CategoryDropdown categoryDropdown : categoryDropdowns)
            categoryDropdown.render(categoryDropdown.getX(), categoryDropdown.getY(), partialTicks);

        Collections.reverse(categoryDropdowns);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(CategoryDropdown categoryDropdown :  categoryDropdowns) {
            if(categoryDropdown.hovered(mouseX,mouseY)) {
                categoryDropdown.mouseClicked(mouseX, mouseY, mouseButton);
                return;
            }
        }
    }
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for(CategoryDropdown categoryDropdown : categoryDropdowns)
            categoryDropdown.mouseReleased(mouseX, mouseY, state);
    }
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for(CategoryDropdown categoryDropdown : categoryDropdowns)
            categoryDropdown.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
