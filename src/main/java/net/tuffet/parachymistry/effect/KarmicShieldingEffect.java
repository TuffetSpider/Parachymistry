package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class KarmicShieldingEffect extends StatusEffect {
    protected KarmicShieldingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    Float DamageStored = 0f;

    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        DamageStored = DamageStored + Math.max(amount/3,1);
            entity.setAbsorptionAmount(DamageStored);
            DamageStored=0f;
        super.onEntityDamage(entity, amplifier, source, amount);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        DamageStored=0f;
        super.onApplied(entity, amplifier);
    }
}


