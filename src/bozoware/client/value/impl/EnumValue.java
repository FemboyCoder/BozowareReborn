package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.ModeComponent;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.util.client.ModeEnum;
import bozoware.client.value.Value;

import java.util.Arrays;

public class EnumValue<T extends Enum<T> & ModeEnum> extends Value<T> {

    private final T[] enumValues;

    public EnumValue(String name, T defaultMode) {
        super(name, defaultMode);
        enumValues = defaultMode.getDeclaringClass().getEnumConstants();
    }

    public void increment() {
        T currentValue = getCurrentValue();
        for (T constant : getEnumValues()) {
            if (constant != currentValue) {
                continue;
            }
            T newValue;
            int ordinal = Arrays.asList(getEnumValues()).indexOf(constant);
            if (ordinal == getEnumValues().length - 1) {
                newValue = getEnumValues()[0];
            } else {
                newValue = getEnumValues()[ordinal + 1];
            }
            setCurrentValue(newValue);
            return;
        }
    }

    public void decrement() {
        T currentValue = getCurrentValue();
        for (T constant : getEnumValues()) {
            if (constant != currentValue) {
                continue;
            }
            T newValue;
            int ordinal = Arrays.asList(getEnumValues()).indexOf(constant);
            if (ordinal == 0) {
                newValue = getEnumValues()[getEnumValues().length - 1];
            } else {
                newValue = getEnumValues()[ordinal - 1];
            }
            setCurrentValue(newValue);
            return;
        }
    }

    @Override
    public ValueComponent getComponent(ModuleDropdown parent) {
        return new ModeComponent(parent, this);
    }

    public String getValueString() {
        return currentValue.name();
    }

    public T[] getEnumValues() {
        return enumValues;
    }
}