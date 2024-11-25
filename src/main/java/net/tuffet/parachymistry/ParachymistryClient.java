package net.tuffet.parachymistry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.tuffet.parachymistry.entity.ModEntities;
import net.tuffet.parachymistry.gui.AlchymyScreen;

import static net.tuffet.parachymistry.gui.ModGuis.ALCHYMY;

public class ParachymistryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ALCHYMY, AlchymyScreen::new);
        EntityRendererRegistry.register(ModEntities.AIR_VIAL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.EARTH_VIAL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FIRE_VIAL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WATER_VIAL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.AETHER_VIAL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CONCOCTION_PROJECTILE, FlyingItemEntityRenderer::new);

    }

}
