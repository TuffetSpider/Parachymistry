package net.tuffet.parachymistry.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CrafterBlock;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AlchymyBlockClass extends CrafterBlock {
    public static final MapCodec<CrafterBlock> CODEC = createCodec(CrafterBlock::new);
    private static final Text SCREEN_TITLE = Text.of("Create Alchymical Wonders!");

    public MapCodec<CrafterBlock> getCodec() {
        return CODEC;
    }

    public AlchymyBlockClass(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
            return new SmithingScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos));
        }, SCREEN_TITLE);
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
