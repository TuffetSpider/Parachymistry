package net.tuffet.parachymistry.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent TINCTURE = new FoodComponent.Builder().nutrition(0).saturationModifier(0).statusEffect(new StatusEffectInstance(StatusEffects.POISON,200),0.2f).alwaysEdible().snack().build();
    public static final FoodComponent MERCURY = new FoodComponent.Builder().nutrition(0).saturationModifier(0).statusEffect(new StatusEffectInstance(StatusEffects.POISON,800,3),1f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,800,0),1f).alwaysEdible().build();
    public static final FoodComponent SALT = new FoodComponent.Builder().nutrition(1).saturationModifier(1).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER,200,2),1f).alwaysEdible().build();

}
