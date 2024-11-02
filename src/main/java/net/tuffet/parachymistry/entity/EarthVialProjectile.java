package net.tuffet.parachymistry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.world.World;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.item.ModItems;

import java.util.List;

import static net.tuffet.parachymistry.Parachymistry.SHOULD_HAVE_DAMAGING_VIALS;

public class EarthVialProjectile extends ThrownItemEntity {
    public EarthVialProjectile(EntityType<? extends EarthVialProjectile> entityType, World world) {
        super(entityType, world);
    }

    public EarthVialProjectile(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public EarthVialProjectile(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.EARTH_VIAL;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            if (entity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) entity.damage(this.getDamageSources().inWall(), (float)5.0);
            this.discard();
        }
    }

    @Override
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
            ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.DUST_PLUME, x, y, z, 0, 0, 0, 0, 1.0);
        }
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
            Box box = this.getBoundingBox().expand(3.5, 2.0, 3.5);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity livingEntity : list) {
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.GAIA_EFFECT, 400, 0),this.getOwner());
            }
        }
        this.discard();
    }
}
