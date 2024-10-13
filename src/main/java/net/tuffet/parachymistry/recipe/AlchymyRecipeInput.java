//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.tuffet.parachymistry.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record AlchymyRecipeInput(ItemStack base, ItemStack reagent, ItemStack catalyst) implements RecipeInput {
    public ItemStack getStackInSlot(int slot) {
        ItemStack var10000;
        switch (slot) {
            case 0 -> var10000 = this.base;
            case 1 -> var10000 = this.reagent;
            case 2 -> var10000 = this.catalyst;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + slot);
        }

        return var10000;
    }

    public int getSize() {
        return 3;
    }

    public boolean isEmpty() {
        return this.base.isEmpty() && this.reagent.isEmpty() && this.catalyst.isEmpty();
    }

    public ItemStack base() {
        return this.base;
    }

    public ItemStack reagent() {
        return this.reagent;
    }

    public ItemStack catalyst() {
        return this.catalyst;
    }
}
