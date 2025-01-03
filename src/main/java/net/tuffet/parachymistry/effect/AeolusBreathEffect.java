package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AeolusBreathEffect extends StatusEffect {

    protected AeolusBreathEffect() {
        super(StatusEffectCategory.HARMFUL, 16113331);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.limitFallDistance();
        return super.applyUpdateEffect(entity, amplifier);
    }

}
