package net.tuffet.parachymistry.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tuffet.parachymistry.gui.AlchymyScreenHandler;

public class AlchymyBlock extends CraftingTableBlock {
    public static final MapCodec<AlchymyBlock> CODEC = createCodec(AlchymyBlock::new);
    private static final Text SCREEN_TITLE = Text.translatable("Undergo Alchymy");

    public MapCodec<AlchymyBlock> getCodec() {
        return CODEC;
    }

    public AlchymyBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new AlchymyScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), SCREEN_TITLE);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));

            return ActionResult.CONSUME;
        }
    }
}

