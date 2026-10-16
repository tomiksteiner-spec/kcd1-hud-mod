package com.example.kcdhud;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KcdHudMod implements ModInitializer {
    public static final String MOD_ID = "kcd_hud";
    public static final Map<UUID, Float> PLAYER_STAMINA = new HashMap<>();
    public static final float MAX_STAMINA = 100.0f;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                UUID uuid = player.getUuid();
                float stamina = PLAYER_STAMINA.getOrDefault(uuid, MAX_STAMINA);

                if (player.isSprinting()) {
                    stamina = Math.max(0.0f, stamina - 0.5f);
                    if (stamina <= 0.0f) {
                        player.setSprinting(false);
                    }
                } else {
                    stamina = Math.min(MAX_STAMINA, stamina + 0.25f);
                }

                PLAYER_STAMINA.put(uuid, stamina);
            }
        });
    }
}
