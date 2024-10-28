

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
    private final World world;
    private RecipeEntry<AlchymyRecipe> currentRecipe;
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
        return ForgingSlotsManager.create().input(0, 79, 15, (stack) -> this.recipes.stream().anyMatch((recipe) -> (recipe.value()).testBase(stack))).input(1, 102, 52, (stack) -> this.recipes.stream().anyMatch((recipe) -> (recipe.value()).testReagent(stack))).input(2, 56, 52, (stack) -> this.recipes.stream().anyMatch((recipe) -> (recipe.value()).testCatalyst(stack))).output(3, 79, 47).build();
    }

    protected boolean canUse(BlockState state) {
        {
            return state.isOf(ModBlocks.ALCHYMY_STATION);
        }
    }


    @Override
    public void updateResult() {
        AlchymyRecipeInput alchymyRecipeInput = this.createRecipeInput();
        List<RecipeEntry<AlchymyRecipe>> list = this.world.getRecipeManager().getAllMatches(ModRecipes.ALCHYMY, alchymyRecipeInput, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            RecipeEntry<AlchymyRecipe> recipeEntry = list.getFirst();
            ItemStack itemStack = recipeEntry.value().craft(alchymyRecipeInput, this.world.getRegistryManager());
            if (itemStack.isItemEnabled(this.world.getEnabledFeatures())) {
                this.currentRecipe = recipeEntry;
                this.output.setLastRecipe(recipeEntry);
                this.output.setStack(0, itemStack);
            }
        }

    }



    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return this.currentRecipe != null && this.currentRecipe.value().matches(this.createRecipeInput(), this.world);
    }

    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraftByPlayer(player.getWorld(), player, stack.getCount());
        this.output.unlockLastRecipe(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
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












