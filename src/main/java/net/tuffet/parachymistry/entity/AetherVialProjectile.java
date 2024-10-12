package net.tuffet.parachymistry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.item.ModItems;

import java.util.List;

import static net.minecraft.entity.projectile.AbstractWindChargeEntity.EXPLOSION_BEHAVIOR;

public class AetherVialProjectile extends ThrownItemEntity {
    public AetherVialProjectile(EntityType<? extends FireVialProjectile> entityType, World world) {
        super(entityType, world);
    }


    public AetherVialProjectile(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public AetherVialProjectile(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return ModItems.AETHER_VIAL;
    }


    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient && !this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
            Box box = this.getBoundingBox().expand(5.0, 4.0, 5.0);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity livingEntity : list) {
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.AETHERIAL_SACRIFICE_EFFECT, 100, 0));
                this.getWorld().spawnEntity(new ShulkerBulletEntity(this.getWorld(), (LivingEntity) this.getOwner(), livingEntity, Direction.Axis.pickRandomAxis(random)));
                this.getWorld().spawnEntity(new ShulkerBulletEntity(this.getWorld(), (LivingEntity) this.getOwner(), livingEntity, Direction.Axis.pickRandomAxis(random)));
                }




        }this.discard();
    }}
