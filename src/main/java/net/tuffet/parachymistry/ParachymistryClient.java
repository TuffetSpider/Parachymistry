package net.tuffet.parachymistry;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.tuffet.parachymistry.gui.AlchymyScreen;

import static net.tuffet.parachymistry.gui.ModGuis.ALCHYMY;

public class ParachymistryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ALCHYMY, AlchymyScreen::new);
    }
}
