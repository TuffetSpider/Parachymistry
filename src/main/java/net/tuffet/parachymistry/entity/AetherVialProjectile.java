package net.tuffet.parachymistry.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.EntityTrackerEntry;
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

public class AetherVialProjectile extends ThrownItemEntity {
    public AetherVialProjectile(EntityType<? extends AetherVialProjectile> entityType, World world) {
        super(entityType, world);
    }

    public AetherVialProjectile(World world, LivingEntity owner) {
        super(ModEntities.AETHER_VIAL, owner, world);
    }

    public AetherVialProjectile(World world, double x, double y, double z) {
        super(ModEntities.AETHER_VIAL, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.AETHER_VIAL;
    }

    @Override
    public void tick() {
        if (this.age>450) this.discard();
        super.tick();
    }



    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        int particleCount = 200;
        double radius = 4.0;
        Vec3d center = hitResult.getPos();
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
            Box box = this.getBoundingBox().expand(3.5, 4.0, 3.5);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (int i = 0; i < particleCount; i++) {
                // Calculate the angle in radians
                double angle = 2 * Math.PI * i / particleCount;

                // Calculate x and z coordinates for the particle
                double x = center.x + radius * Math.cos(angle);
                double z = center.z + radius * Math.sin(angle);

                // y can be the height above ground where you want the particles to appear
                double y = center.y;

                // Spawn the fire particle at the calculated position
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.END_ROD, x, y+0.2, z, 0, 0, 0, 0, 1.0);
            }
            for (LivingEntity livingEntity : list) {
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.AETHERIAL_SACRIFICE_EFFECT, 100, 0),this.getOwner());
            }
        }
        this.discard();
    }
}
