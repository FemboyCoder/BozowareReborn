package bozoware.client;

import best.azura.eventbus.core.EventBus;
import bozoware.client.command.CommandManager;
import bozoware.client.module.ModuleManager;
import bozoware.client.util.client.CapeUtil;
import bozoware.client.util.client.ClientUser;
import bozoware.client.util.client.SecurityUtil;
import bozoware.client.util.render.ImageUtil;
import bozoware.launcher.LaunchClient;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public enum Bozoware {
    INSTANCE;

    {
        if(LaunchClient.isMac) {
            bozowareFolderLocation = new File(LaunchClient.USER_HOME,"Library/Application Support/bozoware");
        } else {
            bozowareFolderLocation = new File(LaunchClient.APPDATA,"bozoware");
        }
    }

    private String currentAuthKey = "";

    private final File bozowareFolderLocation;

    public final String CLIENT_NAME = "Bozoware Reborn";
    public final String CLIENT_VERSION = "Dev-0.1";

    private ClientUser user;

    private CapeUtil capeUtil;

    private final Minecraft mc = Minecraft.getMinecraft();

    private EventBus eventBus;
    private MicrosoftAuthenticator microsoftAuthenticator;

    public void startup() {

        System.out.println("File integrity check");
        System.out.println(SecurityUtil.getChecksum());

        /* TODO
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
        if (!bozowareFolderLocation.exists()) {
            bozowareFolderLocation.mkdirs();
        }

        setIcon();

        init();

        System.out.println("Bozoware Loaded.\nHello Bozo!");

    }

    public void init() {
        /* TODO
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


        eventBus = new EventBus();
        microsoftAuthenticator = new MicrosoftAuthenticator();

        ModuleManager.INSTANCE.init();
        CommandManager.INSTANCE.init();
        /*
        if(user == null) {
            System.exit(0);
        }

         */

    }

    public File getBozowareFolderLocation() {
        return this.bozowareFolderLocation;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public CapeUtil getCapeUtil() {
        return capeUtil;
    }

    public MicrosoftAuthenticator getMicrosoftAuthenticator() {
        return microsoftAuthenticator;
    }

    public ClientUser getUser() {
        return user;
    }

    public String getCurrentAuthKey() {
        return currentAuthKey;
    }

    public void setCurrentAuthKey(String currentAuthKey) {
        this.currentAuthKey = currentAuthKey;
    }

    private void setIcon() {
        try {
            Display.setIcon(new ByteBuffer[]{
                    ImageUtil.readImageToBuffer(mc.getResourceManager().getResource(new ResourceLocation("bozoware/logo/logo16x.png")).getInputStream()),
                    ImageUtil.readImageToBuffer(mc.getResourceManager().getResource(new ResourceLocation("bozoware/logo/logo32x.png")).getInputStream())});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
