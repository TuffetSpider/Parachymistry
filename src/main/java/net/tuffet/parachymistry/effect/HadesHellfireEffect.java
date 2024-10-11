package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class HadesHellfireEffect extends StatusEffect {
    protected HadesHellfireEffect(StatusEffectCategory category, int color) {
        super(StatusEffectCategory.HARMFUL,0xe9b8b3);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setOnFireFor(10);
        entity.setOnFire(true);
        return super.applyUpdateEffect(entity, amplifier);
    }
}

