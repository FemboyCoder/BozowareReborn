package bozoware.client.util.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class CapeUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private static final int devCape_MIN = 0;
    private static final int devCape_MAX = 59;
    private static int devCape_CURRENT = -1;
    private static long lastCapeUpdate = System.currentTimeMillis();

    public static void updateDevCape(int delayBetweenFrames, Function<ResourceLocation, Void> setCapeFunction) {
        if(System.currentTimeMillis() - lastCapeUpdate >= delayBetweenFrames) {
            if (devCape_CURRENT == -1) {
                devCape_CURRENT = 0;
            } else if (devCape_CURRENT >= devCape_MAX) {
                devCape_CURRENT = devCape_MIN;
            } else {
                devCape_CURRENT++;
            }
            lastCapeUpdate = System.currentTimeMillis();
        }
        ResourceLocation currentFrameLocation = new ResourceLocation("bozoware/misc/devcape/frames/capeframe"+devCape_CURRENT+".png");
        setCapeFunction.apply(currentFrameLocation);
    }

}
