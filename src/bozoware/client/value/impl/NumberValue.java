package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.NumberComponent;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.value.Value;

public class NumberValue extends Value<Double> {

    private final double increment;
    private double min;
    private double max;

    public NumberValue(String name, double value, double min, double max, double increment) {
        super(name, value);
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public long getLong() {
        return currentValue.longValue();
    }
    public double getDouble() {
        return currentValue;
    }
    public float getFloat() {
        return currentValue.floatValue();
    }
    public int getInt() {
        return currentValue.intValue();
    }

    public double getMin() {
        return min;
    }
    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }
    public void setMax(double max) {
        this.max = max;
    }

    public double getIncrement() {
        return increment;
    }

    @Override
    public ValueComponent getComponent(ModuleDropdown parent) {
        return new NumberComponent(parent, this);
    }

    public void increment(boolean positive) {
        double newValue = positive ? currentValue + increment : currentValue - increment;
        if (newValue > max) {
            newValue = max;
        }
        if (newValue < min) {
            newValue = min;
        }
        setCurrentValue( newValue );
    }

}