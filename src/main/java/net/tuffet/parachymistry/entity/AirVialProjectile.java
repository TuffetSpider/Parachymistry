package net.tuffet.parachymistry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.item.ModItems;

import java.util.List;

import static net.minecraft.entity.projectile.AbstractWindChargeEntity.EXPLOSION_BEHAVIOR;

public class AirVialProjectile extends ThrownItemEntity {
    public AirVialProjectile(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public AirVialProjectile(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public AirVialProjectile(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return ModItems.AIR_VIAL;
    }


    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            entity.damage(this.getDamageSources().windCharge((Entity) this, (LivingEntity) this.getOwner()), (float)5.0);
            this.discard();
        }}

    protected void onCollision(HitResult hitResult) {
        int particleCount = 200;
        double radius = 4.0;
        Vec3d center = hitResult.getPos();
        for (int i = 0; i < particleCount; i++) {
            // Calculate the angle in radians
            double angle = 2 * Math.PI * i / particleCount;

            // Calculate x and z coordinates for the particle
            double x = center.x + radius * Math.cos(angle);
            double z = center.z + radius * Math.sin(angle);

            // y can be the height above ground where you want the particles to appear
            double y = center.y;

            // Spawn the fire particle at the calculated position
            ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.POOF, x, y, z, 0, 0, 0, 0, 1.0);
        }
        super.onCollision(hitResult);

        if (!this.getWorld().isClient && this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
            Box box = this.getBoundingBox().expand(3.5, 2.0, 3.5);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.GUST_EMITTER_LARGE, this.getX(),this.getY(),this.getZ(), 0, 0, 0, 0, 1.0);
            for (LivingEntity livingEntity : list) {
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.AEOLUS_EFFECT,400));
            }this.discard();


        }this.discard();
    }}





