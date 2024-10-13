package net.tuffet.parachymistry.component;

import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;

public class ModComponents implements ModInitializer {
    public static ComponentType<TinctureIngredientComponent> TINCTUREITEM = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Parachymistry.MOD_ID, "tincture_item"),
            ComponentType.<TinctureIngredientComponent>builder().codec(TinctureIngredientComponent.CODEC).build()
    );
    public static void intialize(){

    }

    @Override
    public void onInitialize() {

    }
}
