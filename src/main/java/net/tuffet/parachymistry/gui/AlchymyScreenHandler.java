//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.tuffet.parachymistry.gui;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.world.World;
import net.tuffet.parachymistry.block.ModBlocks;
import net.tuffet.parachymistry.recipe.AlchymyRecipe;
import net.tuffet.parachymistry.recipe.AlchymyRecipeInput;
import net.tuffet.parachymistry.recipe.ModRecipes;

public class AlchymyScreenHandler extends ForgingScreenHandler {
    public static final int TEMPLATE_ID = 0;
    public static final int EQUIPMENT_ID = 1;
    public static final int MATERIAL_ID = 2;
    public static final int OUTPUT_ID = 3;
    public static final int TEMPLATE_X = 8;
    public static final int EQUIPMENT_X = 26;
    public static final int MATERIAL_X = 44;
    private static final int OUTPUT_X = 98;
    public static final int SLOT_Y = 48;
    private final World world;
    private final List<RecipeEntry<AlchymyRecipe>> recipes;

    public AlchymyScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public AlchymyScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModGuis.ALCHYMY, syncId, playerInventory, context);
        this.world = playerInventory.player.getWorld();
        this.recipes = this.world.getRecipeManager().listAllOfType(ModRecipes.ALCHYMY);
    }

    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create().input(0, 8, 48, (stack) -> {
            return this.recipes.stream().anyMatch((recipe) -> {
                return ((AlchymyRecipe) recipe.value()).testBase(stack);
            });
        }).input(1, 26, 48, (stack) -> {
            return this.recipes.stream().anyMatch((recipe) -> {
                return ((AlchymyRecipe) recipe.value()).testReagant(stack);
            });
        }).input(2, 44, 48, (stack) -> {
            return this.recipes.stream().anyMatch((recipe) -> {
                return ((AlchymyRecipe) recipe.value()).testCatalyst(stack);
            });
        }).output(3, 98, 48).build();
    }

    protected boolean canUse(BlockState state) {
        {
            return state.isOf(ModBlocks.ALCHYMY_STATION);
        }
    }

    @Override
    public void updateResult() {

    }


    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return false;
    }

    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraftByPlayer(player.getWorld(), player, stack.getCount());
        this.output.unlockLastRecipe(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
        this.context.run((world, pos) -> {
            world.syncWorldEvent(1044, pos, 0);
        });
    }

    private List<ItemStack> getInputStacks() {
        return List.of(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2));
    }

    private AlchymyRecipeInput createRecipeInput() {
        return new AlchymyRecipeInput(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2));
    }

    private void decrementStack(int slot) {
        ItemStack itemStack = this.input.getStack(slot);
        if (!itemStack.isEmpty()) {
            itemStack.decrement(1);
            this.input.setStack(slot, itemStack);
        }

    }


        }












