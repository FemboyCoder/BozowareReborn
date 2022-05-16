package bozoware.client.value;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.ValueComponent;

public abstract class Value<Value> {

    protected Value currentValue;
    String valueName;

    public Value(String valueName, Value defaultValue) {
        this.valueName = valueName;
        this.currentValue = defaultValue;
    }

    public String getValueName() {
        return this.valueName;
    }

    public Value getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(Value currentValue) {
        this.currentValue = currentValue;
    }

    public abstract ValueComponent getComponent(ModuleDropdown parent);

}
