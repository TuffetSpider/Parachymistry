package net.tuffet.parachymistry.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.block.custom.AlchymyBlock;

public final class ModBlocks {

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Parachymistry.id(name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Parachymistry.id(name),
                new BlockItem(block, new Item.Settings()));
    }
    public static Block ALCHYMY_STATION;

    public static void init() {
        ALCHYMY_STATION = registerBlock("alchymy_station", new AlchymyBlock(Block.Settings.create().strength(2f).sounds(BlockSoundGroup.LODESTONE).requiresTool()));
    }
}
