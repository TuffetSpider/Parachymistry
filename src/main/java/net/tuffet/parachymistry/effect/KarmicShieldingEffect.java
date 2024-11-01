package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.HashMap;
import java.util.Map;


public class KarmicShieldingEffect extends StatusEffect {

    protected KarmicShieldingEffect() {
        super(StatusEffectCategory.BENEFICIAL, 16445630);
    }

    private final Map<LivingEntity, Float> damageStored = new HashMap<>();

    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        entity.setAbsorptionAmount(damageStored.getOrDefault(entity, 0F) + Math.max(amount / 3,1));
        damageStored.remove(entity);
        super.onEntityDamage(entity, amplifier, source, amount);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        damageStored.remove(entity);
        super.onApplied(entity, amplifier);
    }
}


