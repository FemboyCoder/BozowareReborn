package bozoware.launcher;

import net.minecraft.client.main.Main;

import java.io.File;
import java.util.Arrays;

public class LaunchClient {

    public static final String APPDATA = System.getenv("APPDATA");
    public static final File WORKING_DIRECTORY_WIN = new File(APPDATA, ".minecraft/");

    public static final boolean isMac = System.getProperty("os.name").contains("Mac");
    public static final String USER_HOME = System.getProperty("user.home");
    public static final File WORKING_DIRECTORY_MAC = new File(USER_HOME, "Library/Application Support/minecraft/");

    public static void launch() {

        final File WORKING_DIRECTORY = isMac ? WORKING_DIRECTORY_MAC : WORKING_DIRECTORY_WIN;
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

        Main.main(new String[]{
                "--version", "1.8.8",
                "--assetIndex", "1.8",
                "--userProperties", "{}",
                "--gameDir", new File(WORKING_DIRECTORY, ".").getAbsolutePath(),
                "--assetsDir", new File(WORKING_DIRECTORY, "assets/").getAbsolutePath(),
                "--accessToken", ""
        });
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        launch();
    }
}
