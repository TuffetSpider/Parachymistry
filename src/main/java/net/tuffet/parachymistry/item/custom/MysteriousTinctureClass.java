package net.tuffet.parachymistry.item.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.tuffet.parachymistry.component.ModComponents;

import java.util.Objects;

public class MysteriousTinctureClass extends Item{
    public MysteriousTinctureClass(Settings settings) {
        super(settings);

    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        System.out.println("TinctureIngredientComponent[ingredient=minecraft:ender_pearl]");
        System.out.println((Objects.requireNonNull(stack.get(ModComponents.TINCTUREITEM)).toString()));
        if(testComponent(stack,"minecraft:ender_pearl"))
        {if(user.getLastAttacker()!=null){
            user.teleport(Objects.requireNonNull(user.getLastAttacker()).getX(),user.getLastAttacker().getY(),user.getLastAttacker().getZ(),true);
        }}


        return super.finishUsing(stack, world, user);
    }
    public boolean testComponent(ItemStack stack, String item){
        return Objects.equals(Objects.requireNonNull(stack.get(ModComponents.TINCTUREITEM)).toString(), "TinctureIngredientComponent[ingredient=" + item + "]");

    }
}






