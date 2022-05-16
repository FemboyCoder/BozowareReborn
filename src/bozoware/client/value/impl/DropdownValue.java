package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.DropdownComponent;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.value.Value;

import java.util.Arrays;
import java.util.List;

public class DropdownValue extends Value<List<Value<?>>> {

    public DropdownValue(String valueName, List<Value<?>> defaultValue) {
        super(valueName, defaultValue);
    }

    public DropdownValue(String valueName, Value<?>... values) {
        super(valueName, Arrays.asList(values));
    }

    @Override
    public ValueComponent getComponent(ModuleDropdown parent) {
        return new DropdownComponent(parent, this);
    }
}
