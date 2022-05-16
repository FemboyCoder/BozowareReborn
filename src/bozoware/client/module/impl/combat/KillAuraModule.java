package bozoware.client.module.impl.combat;

import bozoware.client.module.Module;
import bozoware.client.module.ModuleAnnotation;
import bozoware.client.module.ModuleCategory;
import bozoware.client.value.impl.DropdownValue;
import bozoware.client.value.impl.NumberValue;

@ModuleAnnotation(name = "KillAura", description = "Attacks Entities", category = ModuleCategory.COMBAT)
public class KillAuraModule extends Module {

    private final NumberValue attackSpeedValue = new NumberValue("APS", 9, 1, 20, 1);
    private final NumberValue attackSpeedRandomValue = new NumberValue("APS-Random", 0.5, 0, 10, 0.25);

    private final DropdownValue apsDropdownValue = new DropdownValue("Attack", attackSpeedValue, attackSpeedRandomValue);

    public KillAuraModule() {
        super();
        setValues(apsDropdownValue);
    }

}
