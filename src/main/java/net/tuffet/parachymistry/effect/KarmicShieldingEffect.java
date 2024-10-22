package net.tuffet.parachymistry.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.entity.projectile.AbstractWindChargeEntity.EXPLOSION_BEHAVIOR;

public class KarmicShieldingEffect extends StatusEffect {
    protected KarmicShieldingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    Float DamageStored = 0f;

    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        DamageStored = DamageStored + amount/3;
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


