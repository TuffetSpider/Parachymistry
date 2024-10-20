package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Objects;

public class VenomousReciprocationEffect extends StatusEffect {
    protected VenomousReciprocationEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        if(Objects.requireNonNull(source.getAttacker()).isLiving()){
            LivingEntity attacker = (LivingEntity) source.getAttacker();
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,120,amplifier));
        }
        super.onEntityDamage(entity, amplifier, source, amount);
    }
}
