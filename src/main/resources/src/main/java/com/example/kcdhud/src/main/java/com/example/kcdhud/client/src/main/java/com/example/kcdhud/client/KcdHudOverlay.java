package com.example.kcdhud.client;

import com.example.kcdhud.KcdHudMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class KcdHudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        int x = width / 2 - 91; 
        int y = height - 39;

        float stamina = KcdHudMod.PLAYER_STAMINA.getOrDefault(client.player.getUuid(), KcdHudMod.MAX_STAMINA);
        int staminaWidth = (int) ((stamina / KcdHudMod.MAX_STAMINA) * 182);

        drawContext.fill(x, y, x + 182, y + 5, 0xFF2B2B2B);
        drawContext.fill(x, y, x + staminaWidth, y + 5, 0xFFFFCC00);
        drawContext.drawBorder(x - 1, y - 1, 184, 7, 0xFF8B6508);
    }
}
