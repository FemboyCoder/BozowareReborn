package bozoware.client.module.impl.render;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import bozoware.client.event.player.EventUpdate;
import bozoware.client.event.render.EventRender2D;
import bozoware.client.module.Module;
import bozoware.client.module.ModuleAnnotation;
import bozoware.client.module.ModuleCategory;
import bozoware.client.value.impl.ColourValue;
import net.minecraft.client.entity.AbstractClientPlayer;

import static bozoware.client.util.client.CapeUtil.updateDevCape;

@ModuleAnnotation(name = "HUD", category = ModuleCategory.RENDER)
public class HUDModule extends Module {

    public ColourValue hudColourValue = new ColourValue("HUD Colour", 0xffadad);

    public HUDModule() {
        super();
        setEnabled(true);
        setValues(hudColourValue);
    }

    @EventHandler
    public final Listener<EventUpdate> eventUpdateListener = event -> {
        mc.theWorld.getLoadedEntityList().forEach(entity -> {
            if(entity instanceof AbstractClientPlayer) {
                AbstractClientPlayer player = (AbstractClientPlayer) entity;
                updateDevCape(70, func -> {
                    player.setLocationOfCape(func);
                    return null;
                });
            }
        });
    };

    @EventHandler
    public final Listener<EventRender2D> eventRender2DListener = event -> {
    };

}
