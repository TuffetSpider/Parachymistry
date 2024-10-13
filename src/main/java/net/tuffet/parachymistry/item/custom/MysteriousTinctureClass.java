package net.tuffet.parachymistry.item.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tuffet.parachymistry.component.ModComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MysteriousTinctureClass extends Item{
    public MysteriousTinctureClass(Settings settings) {
        super(settings);


    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Objects.requireNonNull(context.getPlayer()).sendMessage(Text.of(Objects.requireNonNull(context.getStack().get(ModComponents.TINCTUREITEM)).toString()));
        return super.useOnBlock(context);

    }

}



