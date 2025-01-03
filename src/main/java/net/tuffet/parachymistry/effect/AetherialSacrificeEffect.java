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

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import java.util.List;

import static net.tuffet.parachymistry.Parachymistry.SHOULD_HAVE_DAMAGING_VIALS;

public class AetherialSacrificeEffect extends StatusEffect {

    protected AetherialSacrificeEffect() {
        super(StatusEffectCategory.HARMFUL,0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        StatusEffectInstance effect = entity.getStatusEffect(ModEffects.AETHERIAL_SACRIFICE_EFFECT);
        entity.setGlowing(true);
        assert effect != null;
        int timeRemaining = effect.getDuration();
        if (timeRemaining == 1) {
            entity.setGlowing(false);
            if (entity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS) && !entity.isSpectator())
                if (entity instanceof PlayerEntity player) if (!player.isCreative()) entity.damage(entity.getWorld().getDamageSources().outOfWorld(), 8);
                else entity.damage(entity.getWorld().getDamageSources().generic(), 8);

            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 0));

            entity.getEntityWorld().addParticle(ParticleTypes.GUST_EMITTER_LARGE, true, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);

            Box box = entity.getBoundingBox().expand(5, -20, 5);
            List<LivingEntity> list = entity.getWorld().getNonSpectatingEntities(LivingEntity.class,box);
            list.remove(entity);
            for (LivingEntity livingEntity : list) {
                entity.getWorld().spawnEntity(new ShulkerBulletEntity(entity.getWorld(), entity, livingEntity, Direction.Axis.Z));
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }


    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        entity.getWorld().spawnEntity(new ShulkerBulletEntity(entity.getWorld(),  entity, entity.getLastAttacker(), Direction.Axis.Y));
        super.onEntityDamage(entity, amplifier, source, amount);
    }
}
