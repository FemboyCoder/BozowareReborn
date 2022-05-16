package bozoware.client.module;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import bozoware.client.Bozoware;
import bozoware.client.event.input.EventKey;
import bozoware.client.module.impl.combat.KillAuraModule;
import bozoware.client.module.impl.movement.Sprint;
import bozoware.client.module.impl.render.ClickGUIModule;
import bozoware.client.module.impl.render.HUDModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum ModuleManager {
    INSTANCE;

    private final List<Module> moduleList = new ArrayList<>();
    private boolean initiated = false;

    public void init() {
        if(initiated) return;
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

        Bozoware.INSTANCE.getEventBus().register(this);
        moduleList.add(new HUDModule());
        moduleList.add(new KillAuraModule());
        moduleList.add(new ClickGUIModule());
        moduleList.add(new Sprint());

        initiated = true;
    }

    @EventHandler
    private final Listener<EventKey> eventKeyListener = event -> {
        for(Module module : moduleList) {
            if(module.getKey() == event.getKey()) {
                module.toggleModule();
            }
        }
    };

    public List<Module> getModules() {
        return this.moduleList;
    }

    public Module get(Class<? extends Module> m) {
        return getModules().stream().filter(module -> module.getClass() == m).findFirst().orElse(null);
    }

    public Module get(String name) {
        return getModules().stream()
                .filter(mod -> mod.getModuleName().equalsIgnoreCase(name) ||
                        mod.getModuleName().replace(" ", "").equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public ArrayList<Module> getModulesByCategory(ModuleCategory c) {
        return getModules().stream().filter(mod -> mod.getCategory() == c).collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<Module> getEnabledModules() {
        return getModules().stream().filter(Module::isEnabled).collect(Collectors.toCollection(ArrayList::new));
    }
}
