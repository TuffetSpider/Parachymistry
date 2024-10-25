package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SwiftStrideEffect extends StatusEffect {
    protected SwiftStrideEffect(StatusEffectCategory category, int color) {
        super(StatusEffectCategory.BENEFICIAL, 102156172);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setFrozenTicks(0);
        return super.applyUpdateEffect(entity, amplifier);
    }
}
