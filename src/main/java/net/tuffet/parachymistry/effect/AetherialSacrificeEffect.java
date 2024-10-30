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

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.tuffet.parachymistry.ModGamerules.ModRules;

import java.util.List;



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
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 0));
            if(!entity.isPlayer()) {
                ((ServerWorld) entity.getWorld()).spawnParticles(ParticleTypes.GUST_EMITTER_LARGE, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0, 0, 1.0);
            }
            if(entity.isPlayer()){
                assert entity instanceof PlayerEntity;
                entity.getEntityWorld().addParticle(ParticleTypes.GUST_EMITTER_LARGE, true, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
            }


            Box box = entity.getBoundingBox().expand(5, -20, 5);
            List<LivingEntity> list = entity.getWorld().getNonSpectatingEntities(LivingEntity.class,box);
            list.remove(entity);
            for (LivingEntity livingEntity : list) {
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




