package net.tuffet.parachymistry.gui;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.tuffet.parachymistry.Parachymistry;

public class ModGuis {

    public static ScreenHandlerType<AlchymyScreenHandler> ALCHYMY;

    public static void init(){
        ALCHYMY = Registry.register(Registries.SCREEN_HANDLER, Parachymistry.id("alchymy"),
                        new ScreenHandlerType<>(AlchymyScreenHandler::new,FeatureFlags.VANILLA_FEATURES));
    }
}
