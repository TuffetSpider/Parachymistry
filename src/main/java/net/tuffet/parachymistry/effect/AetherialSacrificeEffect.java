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
import net.tuffet.parachymistry.ModGamerules.ModRules;

import java.util.List;


import static net.minecraft.entity.projectile.AbstractWindChargeEntity.EXPLOSION_BEHAVIOR;

public class AetherialSacrificeEffect extends StatusEffect {
    protected AetherialSacrificeEffect(StatusEffectCategory category, int color) {
        super(StatusEffectCategory.HARMFUL,0xe9b8b3);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        StatusEffectInstance Status = entity.getStatusEffect(ModEffects.AETHERIAL_SACRIFICE_EFFECT);
        entity.setGlowing(true);
        assert Status != null;
        int TimeRemaining = Status.getDuration();
        if(TimeRemaining==1){
            if (entity instanceof PlayerEntity player) {
                if (!player.isCreative() && !player.isSpectator()) if(entity.getWorld().getGameRules().getBoolean(ModRules.SHOULD_HAVE_DAMAGING_VIALS)) entity.damage(entity.getWorld().getDamageSources().outOfWorld(),10);
            }
            else if(entity.getWorld().getGameRules().getBoolean(ModRules.SHOULD_HAVE_DAMAGING_VIALS)) entity.damage(entity.getWorld().getDamageSources().outOfWorld(),10);
            entity.getWorld().createExplosion(entity, null, EXPLOSION_BEHAVIOR, entity.getX(), entity.getY(), entity.getZ(), 5.0F, false, World.ExplosionSourceType.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 0));
            Box box = entity.getBoundingBox().expand(5, -20, 5);
            List<LivingEntity> list = entity.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity livingEntity : list) {
                entity.getWorld().spawnEntity(new ShulkerBulletEntity(entity.getWorld(), entity, livingEntity, Direction.Axis.Z));
                entity.getWorld().spawnEntity(new ShulkerBulletEntity(entity.getWorld(), entity, livingEntity, Direction.Axis.Z));
                entity.setGlowing(false);
            }
        }
        return super.applyUpdateEffect(entity, amplifier);


    }


    @Override
    public void onEntityDamage(LivingEntity entity, int amplifier, DamageSource source, float amount) {
        entity.getWorld().spawnEntity(new ShulkerBulletEntity(entity.getWorld(),  entity, entity.getLastAttacker(), Direction.Axis.Y));

        super.onEntityDamage(entity, amplifier, source, amount);
    }}




