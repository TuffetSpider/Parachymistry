package net.tuffet.parachymistry.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
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
import net.tuffet.parachymistry.ModGamerules.ModRules;
import net.tuffet.parachymistry.effect.ModEffects;
import net.tuffet.parachymistry.item.ModItems;
import java.util.List;

public class WaterVialProjectile extends ThrownItemEntity {
    public WaterVialProjectile(EntityType<? extends WaterVialProjectile> entityType, World world) {
        super(entityType, world);
    }

    public WaterVialProjectile(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public WaterVialProjectile(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    @Override
    public void tick() {
        if (this.getVelocity().lengthSquared() < 0.001) this.discard();
        super.tick();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.WATER_VIAL;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            if(entity.getWorld().getGameRules().getBoolean(ModRules.SHOULD_HAVE_DAMAGING_VIALS)) entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float) 5.0);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
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
            ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.SPLASH, x, y, z, 0, 0, 0, 0, 1.0);
        }
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1f, 1f);

            Box box = this.getBoundingBox().expand(3.5, 2.0, 3.5);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity livingEntity : list) {
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.POSEIDON_EFFECT, 400, 0),this.getOwner());
            }
        }
        this.discard();
    }
}
