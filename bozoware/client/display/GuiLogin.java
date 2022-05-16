package bozoware.client.display;

import bozoware.client.Bozoware;
import bozoware.client.util.render.RenderUtil;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

import java.io.File;

public class GuiLogin extends GuiScreen {

    boolean hasAutoLogin = new File(Bozoware.INSTANCE.getBozowareFolderLocation(),"creds.bozo").exists();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRectWidth(0,0,this.width,this.height,0xFFDADADA);
        super.drawScreen(mouseX, mouseY, partialTicks);

        mc.displayGuiScreen(new GuiMainMenu());

    }

    public static void doLogin() {
        /*
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        List<String> args = runtime.getInputArguments();
        boolean foundAttach = false;
        for (String s : args) {
            if (s.toLowerCase().contains("disableattachmechanism")) foundAttach = true;
            if (s.contains("-agentlib:jdwp") || s.contains("-Xdebug") || s.contains("-javaagent:") || s.contains("-Xrunjdwp:")) {
                throw new Error("Invalid arguments");
            }
        }
        if (!foundAttach) throw new Error("Invalid arguments. Please add -XX:+DisableAttachMechanism");

         */



    }
}
