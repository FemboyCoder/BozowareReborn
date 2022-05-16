package bozoware.client.module;

public enum ModuleCategory {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    OTHER("Other"),
    EXPLOIT("Exploit"),
    HIDDEN("Hidden");

    final String name;
    ModuleCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
