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

public class AlchymyBlockClass extends CraftingTableBlock {
    public static final MapCodec<net.tuffet.parachymistry.block.custom.AlchymyBlockClass> CODEC = createCodec(net.tuffet.parachymistry.block.custom.AlchymyBlockClass::new);
    private static final Text SCREEN_TITLE = Text.translatable("container.upgrade");

    public MapCodec<net.tuffet.parachymistry.block.custom.AlchymyBlockClass> getCodec() {
        return CODEC;
    }

    public AlchymyBlockClass(AbstractBlock.Settings settings) {
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

