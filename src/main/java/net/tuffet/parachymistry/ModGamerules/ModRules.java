package net.tuffet.parachymistry.ModGamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.world.GameRules;

public class ModRules implements ModInitializer {

    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_HAVE_DAMAGING_VIALS =
            GameRuleRegistry.register("moreDamagingVials", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

    public static void initialize(){

    }
    @Override
    public void onInitialize() {

    }
}
