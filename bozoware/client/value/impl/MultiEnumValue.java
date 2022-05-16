package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.MultiModeComponent;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.util.client.ModeEnum;
import bozoware.client.value.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MultiEnumValue<T extends Enum<T> & ModeEnum> extends Value<ArrayList<T>> {

    private final ArrayList<T> enumValues;

    public MultiEnumValue(String name, T[] values, T... enabledValues) {
        super(name, Arrays.stream(enabledValues).collect(Collectors.toCollection(ArrayList::new)));
        this.enumValues = Arrays.stream(values).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> getAllValuesString() {
        ArrayList<String> strings = new ArrayList<>();
        enumValues.forEach((value) -> {
            strings.add(value.getName());
        });
        return strings;
    }
    public ArrayList<String> getEnabledValuesString() {
        ArrayList<String> strings = new ArrayList<>();
        currentValue.forEach((value) -> {
            strings.add(value.getName());
        });
        return strings;
    }

    public void toggleValue(int index) {
        index = Math.max(0,Math.min(index, enumValues.size()-1));
        T toggleValue = enumValues.get(index);
        if (currentValue.contains(toggleValue)) {
            currentValue.remove(toggleValue);
        } else {
            currentValue.add(toggleValue);
        }
    }

    public boolean isEnabled(int index) {
        index = Math.max(0,Math.min(index, enumValues.size()-1));
        T toggleValue = enumValues.get(index);
        return currentValue.contains(toggleValue);
    }

    public ArrayList<T> getAllValues() {
        return enumValues;
    }

    public ArrayList<T> getEnabledValues() {
        return currentValue;
    }

    public boolean isEnabled(T value) {
        return currentValue.contains(value);
    }

    @Override
    public ValueComponent getComponent(ModuleDropdown parent) {
        return new MultiModeComponent(parent, this);
    }

    public void toggleValue(T value) {
        if (enumValues.contains(value)) {
            if (!currentValue.remove(value)) {
                currentValue.add(value);
            }
        }
    }
}