package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

import static net.tuffet.parachymistry.Parachymistry.SHOULD_HAVE_DAMAGING_VIALS;

public class HadesHellfireEffect extends StatusEffect {

    protected HadesHellfireEffect() {
        super(StatusEffectCategory.HARMFUL,0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            if (!player.isCreative() && !player.isSpectator()) {
                if(entity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)){
                    entity.setOnFireFor(5);
                }
                player.setOnFire(true);
            }
        } else {
            if(entity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)){
                entity.setOnFireFor(5);
            }
            entity.setOnFire(true);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }
}


