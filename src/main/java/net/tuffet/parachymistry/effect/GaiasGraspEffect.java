package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class GaiasGraspEffect extends StatusEffect {

    public GaiasGraspEffect() {
        super(StatusEffectCategory.HARMFUL, 2889224);


    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
            entity.setOnGround(false);
            entity.setSprinting(false);
        return super.applyUpdateEffect(entity, amplifier);
    }
}
