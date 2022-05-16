package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.BooleanComponent;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.value.Value;

public class BooleanValue extends Value<Boolean> {
    public BooleanValue(String settingName, boolean enabled) {
        super(settingName, enabled);
    }
    public void toggleValue() {
        super.setCurrentValue(!super.getCurrentValue());
    }

    @Override
    public ValueComponent getComponent(ModuleDropdown parent) {
        return new BooleanComponent(parent, this);
    }
}
