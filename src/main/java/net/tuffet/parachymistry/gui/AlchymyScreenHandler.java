//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.tuffet.parachymistry.gui;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.world.World;
import net.tuffet.parachymistry.block.ModBlocks;
import net.tuffet.parachymistry.recipe.AlchymyRecipe;

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
    private RecipeEntry<AlchymyRecipe> currentRecipe;
    private final List<RecipeEntry<SmithingRecipe>> recipes;

    public AlchymyScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public AlchymyScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlerType.SMITHING, syncId, playerInventory, context);
        this.world = playerInventory.player.getWorld();
        this.recipes = this.world.getRecipeManager().listAllOfType(RecipeType.SMITHING);
    }

    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create().input(0, 8, 48, (stack) -> {
            return this.recipes.stream().anyMatch((recipe) -> {
                return ((SmithingRecipe)recipe.value()).testTemplate(stack);
            });
        }).input(1, 26, 48, (stack) -> {
            return this.recipes.stream().anyMatch((recipe) -> {
                return ((SmithingRecipe)recipe.value()).testBase(stack);
            });
        }).input(2, 44, 48, (stack) -> {
            return this.recipes.stream().anyMatch((recipe) -> {
                return ((SmithingRecipe)recipe.value()).testAddition(stack);
            });
        }).output(3, 98, 48).build();
    }

    protected boolean canUse(BlockState state) { {
        return state.isOf(ModBlocks.ALCHYMY_STATION);
    }
    }

    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return this.currentRecipe != null && ((AlchymyRecipe)this.currentRecipe.value()).matches(this.createRecipeInput(), this.world);
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

    private SmithingRecipeInput createRecipeInput() {
        return new SmithingRecipeInput(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2));
    }

    private void decrementStack(int slot) {
        ItemStack itemStack = this.input.getStack(slot);
        if (!itemStack.isEmpty()) {
            itemStack.decrement(1);
            this.input.setStack(slot, itemStack);
        }

    }

    public void updateResult() {
        SmithingRecipeInput smithingRecipeInput = this.createRecipeInput();
        List<RecipeEntry<SmithingRecipe>> list = this.world.getRecipeManager().getAllMatches(RecipeType.SMITHING, smithingRecipeInput, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            RecipeEntry<AlchymyRecipe> recipeEntry = (RecipeEntry)list.get(0);
            ItemStack itemStack = ((AlchymyRecipe)recipeEntry.value()).craft(smithingRecipeInput, this.world.getRegistryManager());
            if (itemStack.isItemEnabled(this.world.getEnabledFeatures())) {
                this.currentRecipe = recipeEntry;
                this.output.setLastRecipe(recipeEntry);
                this.output.setStack(0, itemStack);
            }
        }

    }


    }







