package bozoware.client.command;

public enum CommandManager {
    INSTANCE;

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

    }
}
