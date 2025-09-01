package gay.beegirl.render;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.entity.StaminaHolder;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;

public class PlayerStaminaHUD {
    public static final ResourceLocation STAMINA_LAYER = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "hud-stamina-layer");

    public static void render(GuiGraphics context, DeltaTracker tickCounter) {
        int colorNormal = 0x00FF00;
        int colorLow = 0xFF0000;
        int colorBG = 0x000000;
        int colorOpaque = 0xFF000000;
        int colorTransparent = 0x00000000;

        StaminaHolder player = (StaminaHolder) Minecraft.getInstance().player;
        assert player != null;
        int stamina = Math.max(player.getStamina(), 0);
        int maxStamina = player.getMaxStamina();
        float staminaFraction =  stamina/(float)maxStamina;
        int finalColor = ARGB.lerp(Math.clamp(staminaFraction*2, 0, 1), colorLow, colorNormal);
        int finalAlpha = ARGB.lerp(player.getStaminaHideDelay(), colorTransparent, colorOpaque);

        int midX = context.guiWidth()/2;
        int midY = context.guiHeight()/2;

        context.drawCenteredString(Minecraft.getInstance().font,
                stamina+"/"+maxStamina,
                midX,
                midY+24,
                finalColor + finalAlpha); /* */
        context.fill(midX-17,
                midY+15,
                midX+17,
                midY+21,
                colorBG + finalAlpha);
        context.fill(midX-16,
                midY+16,
                midX-16 + (int)Math.ceil(Math.clamp(staminaFraction, 0, 1)*32),
                midY+20,
                finalColor + finalAlpha);

        //TODO: Better UI
    }
}