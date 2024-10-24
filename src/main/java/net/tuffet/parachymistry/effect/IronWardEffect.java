package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.random.Random;

public class IronWardEffect extends StatusEffect {
    protected IronWardEffect(StatusEffectCategory category, int color) {

        super(category, color);
    }

    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        super.onEntityDamage(entity, amplifier, source, amount);
    }
}



