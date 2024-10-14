package net.tuffet.parachymistry.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent TINCTURE = new FoodComponent.Builder().nutrition(0).saturationModifier(0).statusEffect(new StatusEffectInstance(StatusEffects.POISON,200),0.2f).alwaysEdible().build();

}
