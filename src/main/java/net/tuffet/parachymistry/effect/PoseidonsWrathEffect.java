package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;
import java.util.jar.Attributes;

public class PoseidonsWrathEffect extends StatusEffect {

    public PoseidonsWrathEffect() {
        super(StatusEffectCategory.HARMFUL, 0xe9b8b3);


    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setAir(-19);
        if (entity.isTouchingWater()){
            entity.setSwimming(true);

        }

        return super.applyUpdateEffect(entity, amplifier);
    }
}


