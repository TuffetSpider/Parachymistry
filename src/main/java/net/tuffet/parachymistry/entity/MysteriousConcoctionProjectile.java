package net.tuffet.parachymistry.entity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.tuffet.parachymistry.component.ModComponents;
import net.tuffet.parachymistry.item.ModItems;

import java.util.Objects;

public class MysteriousConcoctionProjectile extends ThrownItemEntity {
    public MysteriousConcoctionProjectile(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public MysteriousConcoctionProjectile(World world, LivingEntity owner) {
        super(EntityType.POTION, owner, world);
    }

    public MysteriousConcoctionProjectile(World world, double x, double y, double z) {
        super(EntityType.POTION, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return ModItems.MYSTERIOUS_CONCOCTION;
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            if (testConcoctionComponent("minecraft:ender_pearl")) {
                if (!this.getWorld().isClient&&entityHitResult.getEntity().isLiving()) {
                LivingEntity user = (LivingEntity) entityHitResult.getEntity();
                    for(int i = 0; i < 16; ++i) {
                        double d = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                        double e = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(16) - 8), (double)this.getWorld().getBottomY(), (double)(this.getWorld().getBottomY() + ((ServerWorld)this.getWorld()).getLogicalHeight() - 1));
                        double f = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                        if (user.hasVehicle()) {
                            user.stopRiding();
                        }

                        Vec3d vec3d = user.getPos();
                        if (user.teleport(d, e, f, true)) {
                            this.getWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                            SoundCategory soundCategory;
                            SoundEvent soundEvent;
                            if (user instanceof FoxEntity) {
                                soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                                soundCategory = SoundCategory.NEUTRAL;
                            } else {
                                soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                                soundCategory = SoundCategory.PLAYERS;
                            }}}}
            }}
            this.discard();
        }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (testConcoctionComponent("minecraft:ender_pearl")) {
           // example, true effect is in onEntityHit
        }
        this.discard();
    }
public boolean testConcoctionComponent(String item){
    return Objects.equals(Objects.requireNonNull(this.getStack().get(ModComponents.TINCTUREITEM)).toString(), "TinctureIngredientComponent[ingredient="+item+"]");

}}
