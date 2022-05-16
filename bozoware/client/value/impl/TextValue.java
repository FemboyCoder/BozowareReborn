package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.value.Value;

public class TextValue extends Value<String> {

    public TextValue(String valueName, String defaultValue) {
        super(valueName, defaultValue);
    }

    @Override
    public ValueComponent getComponent(ModuleDropdown parent) {
        return null;
    }
}
