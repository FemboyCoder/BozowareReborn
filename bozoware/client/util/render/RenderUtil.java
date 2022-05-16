package bozoware.client.util.render;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Vector2f;
import java.awt.*;

public class RenderUtil {

    public static void drawRectWidth(float posX, float posY, float width, float height, int colour) {
        Gui.drawRect(posX, posY, posX+width, posY+height, colour);
    }

    public static void drawRectOutline(float x, float y, float x2, float y2, int colour, float thickness) {
        Gui.drawRect(x-thickness,y-thickness,x2+thickness,y,colour); //TOP
        Gui.drawRect(x-thickness,y2,x2+thickness,y2+thickness,colour); //BOTTOM
        Gui.drawRect(x-thickness,y,x,y2,colour); //LEFT
        Gui.drawRect(x2+thickness,y,x2,y2,colour); //RIGHT
    }
    public static void drawRectOutlineWidth(float x, float y, float width, float height, int colour, float thickness) {
        Gui.drawRect(x-thickness,y-thickness,x+width+thickness,y,colour); //TOP
        Gui.drawRect(x-thickness,y+height,x+width+thickness,y+height+thickness,colour); //BOTTOM
        Gui.drawRect(x-thickness,y,x,y+height,colour); //LEFT
        Gui.drawRect(x+width+thickness,y,x+width,y+height,colour); //RIGHT
    }

    public static void drawGradientHorizontal(float left, float top, float right, float bottom, int startColor, int endColor)
    {
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, 0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(right, bottom, 0).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, top, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    public static void drawGradientVertical(float left, float top, float right, float bottom, int startColor, int endColor)
    {
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, 0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, 0).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    public static void drawCustomBox(float x, float y, float width, float height, int barColour, float barSize) {
        drawRectWidth(x,y,width,height,0xFF232323);
        drawGradientVertical(x,y,x+width,y+barSize,
                barColour, new Color(barColour).darker().darker().getRGB());
        drawGradientVertical(x,y+barSize,x+width,y+barSize+(height/10f),
                new Color(0xFF232323).brighter().getRGB(), 0xFF232323);
        drawGradientVertical(x,y+height-(height/10f), x+width, y+height,
                0xFF232323, new Color(0xFF232323).darker().getRGB());
        drawRectOutlineWidth(x,y,width,height,0xFF101010,1);
        drawRectOutlineWidth(x,y,width,barSize,0xFF101010, 0.5f);
    }
    public static void drawLine(Color colour, float thickness, Vector2f... vectors) {
        float r = colour.getRed()/255f;
        float g = colour.getGreen()/255f;
        float b = colour.getBlue()/255f;
        float a = colour.getAlpha()/255f;

        GL11.glColor4f(r,g,b,a);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);

        GL11.glLineWidth(thickness);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for(Vector2f vector : vectors) {
            GL11.glVertex2f(vector.x, vector.y);
        }
        GL11.glEnd();

        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
}
