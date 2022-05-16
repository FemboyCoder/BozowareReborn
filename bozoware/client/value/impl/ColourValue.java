package bozoware.client.value.impl;

import bozoware.client.display.dropdown.impl.ModuleDropdown;
import bozoware.client.display.dropdown.impl.values.ColourComponent;
import bozoware.client.display.dropdown.impl.values.ValueComponent;
import bozoware.client.value.Value;

import java.awt.*;

public class ColourValue extends Value<Color> {

	ColourModes colourMode = ColourModes.DEFAULT;

	public ColourValue(String settingName, Color defaultValue) {
		super(settingName, defaultValue);
	}

	public ColourValue(String settingName, int defaultValue) {
		super(settingName, new Color(defaultValue));
	}

	public int getInt() {
		return getCurrentValue().getRGB();
	}

	public void setInt(int colourInt) {
		setCurrentValue(new Color(colourInt));
	}

	public void setMode(ColourModes mode) {
		colourMode = mode;
	}

	public void switchMode() {
		if (colourMode == ColourModes.DEFAULT) {
			colourMode = ColourModes.ASTOLFO;
		} else if (colourMode == ColourModes.ASTOLFO) {
			colourMode = ColourModes.WAVY;
		} else if (colourMode == ColourModes.WAVY) {
			colourMode = ColourModes.DEFAULT;
		}
	}

	@Override
	public ValueComponent getComponent(ModuleDropdown parent) {
		return new ColourComponent(parent, this);
	}

	public enum ColourModes {
		DEFAULT,
		ASTOLFO,
		WAVY;
	}
}
