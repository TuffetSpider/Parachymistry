package net.tuffet.parachymistry.item.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tuffet.parachymistry.component.ModComponents;
import net.tuffet.parachymistry.effect.ModEffects;

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
        if(testComponent(stack,"minecraft:ender_pearl")){
        {if(user.getLastAttacker()!=null){
            user.teleport(Objects.requireNonNull(user.getLastAttacker()).getX(),user.getLastAttacker().getY(),user.getLastAttacker().getZ(),true);

        }}}
        if(testComponent(stack, "parachymistry:vial_of_air")){
        user.addStatusEffect(new StatusEffectInstance(ModEffects.AEOLUS_EFFECT,300,0));
            Vec3d vec3d2 = new Vec3d(0, 0.25, 0);
            user.setVelocity(user.getVelocity().add(vec3d2));
            if (user instanceof PlayerEntity playerEntity) {
                if (!playerEntity.isSpectator() && (!playerEntity.isCreative() || !playerEntity.getAbilities().flying)) {
                    user.setVelocity(vec3d2);
                }
            }
        }else if (testComponent(stack,"minecraft:soul_soil")){
            user.addStatusEffect(new StatusEffectInstance(ModEffects.SWIFTSTRIDE_EFFECT,2400,0));
        }



        return super.finishUsing(stack, world, user);
    }
    public boolean testComponent(ItemStack stack, String item){
        return Objects.equals(Objects.requireNonNull(stack.get(ModComponents.TINCTUREITEM)).toString(), "TinctureIngredientComponent[ingredient=" + item + "]");

    }
}






