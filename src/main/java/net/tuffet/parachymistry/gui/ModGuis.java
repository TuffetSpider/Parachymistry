package net.tuffet.parachymistry.gui;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;

public class ModGuis implements ModInitializer {


    public static final ScreenHandlerType<AlchymyScreenHandler> ALCHYMY =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Parachymistry.MOD_ID, "alchymy"),
                    new ScreenHandlerType<>(AlchymyScreenHandler::new,FeatureFlags.VANILLA_FEATURES));


    @Override
    public void onInitialize() {

    }
}
