

package net.tuffet.parachymistry.recipe;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.SmithingRecipeInput;

public interface AlchymyRecipe extends Recipe<SmithingRecipeInput> {
        default RecipeType<?> getType() {
            return RecipeType.SMITHING;
        }

        default boolean fits(int width, int height) {
            return width >= 3 && height >= 1;
        }

        default ItemStack createIcon() {
            return new ItemStack(Blocks.SMITHING_TABLE);
        }

        boolean testTemplate(ItemStack stack);

        boolean testBase(ItemStack stack);

        boolean testAddition(ItemStack stack);
    }


