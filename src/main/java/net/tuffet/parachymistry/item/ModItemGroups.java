package net.tuffet.parachymistry.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.block.ModBlocks;

public class ModItemGroups {

    public static ItemGroup PARACHYMISTRY_ITEM_GROUP;

    public static void init() {
        PARACHYMISTRY_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
                Parachymistry.id("parachymistry"),
                FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.MYSTERIOUS_TINCTURE))
                        .displayName(Text.translatable("itemgroup.parachymistry.parachymistry"))
                        .entries((displayContext, entries) -> {
                            entries.add(ModBlocks.ALCHYMY_STATION);
                            entries.add(ModItems.ALCHYMICAL_VIAL);
                            entries.add(ModItems.SALT);
                            entries.add(ModItems.MERCURY);
                            entries.add(ModItems.QUICKLIME);
                            entries.add(ModItems.MYSTERIOUS_CONCOCTION);
                            entries.add(ModItems.MYSTERIOUS_TINCTURE);
                            entries.add(ModItems.AIR_VIAL);
                            entries.add(ModItems.FIRE_VIAL);
                            entries.add(ModItems.WATER_VIAL);
                            entries.add(ModItems.EARTH_VIAL);
                            entries.add(ModItems.AETHER_VIAL);
                        }).build());
    }
}