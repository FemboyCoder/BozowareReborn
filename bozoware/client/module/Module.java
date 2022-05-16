package bozoware.client.module;

import bozoware.client.Bozoware;
import bozoware.client.value.Value;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module {

    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final EntityPlayerSP player = mc.thePlayer;

    private final String name;
    private final String description;
    private final ModuleCategory category;

    private String displayName;
    private int key;
    private boolean enabled;

    private List<Value<?>> valueList = new ArrayList<>();

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDisplayName() { return displayName; }
    public int getKey() { return key; }
    public boolean isEnabled() { return enabled; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public void setKey(int key) { this.key = key; }

    protected Module() {
        ModuleAnnotation annotation = getClass().getAnnotation(ModuleAnnotation.class);
        if(annotation == null) {
            throw new IllegalStateException("Instantiating Module "+getClass().getName()+" Without Annotation");
        }

        this.name = annotation.name();
        this.description = annotation.description();
        this.category = annotation.category();

        this.displayName = annotation.displayName();
        this.key = annotation.key();
    }

    protected void onEnable() {
        Bozoware.INSTANCE.getEventBus().register(this);
    }
    protected void onDisable() {
        Bozoware.INSTANCE.getEventBus().unregister(this);
    }

    public void toggleModule() {
        this.enabled = !this.enabled;
        if(this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    public void setEnabled(boolean enabled) {
        if(this.enabled == enabled) return;
        this.enabled = !this.enabled;
        if(this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    public void forceEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public List<Value<?>> getValues() {
        return valueList;
    }
    public void setValues(Value<?>... values) {
        this.valueList.addAll(Arrays.asList(values));
    }

    public String getModuleName() {
        return this.name;
    }
}
