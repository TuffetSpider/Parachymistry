package net.tuffet.parachymistry.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CrafterBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.block.custom.AlchymyBlockClass;


public class ModBlocks {
    // Methods for registering blocks
    public static void registerModBlocks() {
        Parachymistry.LOGGER.info("Engaging Chymistry");
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Parachymistry.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Parachymistry.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    public static final Block ALCHYMY_STATION = registerBlock("alchymy_station", new AlchymyBlockClass(Block.Settings.create().strength(2f).sounds(BlockSoundGroup.LODESTONE)));
}
