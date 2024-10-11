package net.tuffet.parachymistry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.tuffet.parachymistry.item.ModItems;

import java.util.Iterator;
import java.util.List;

public class WaterVialProjectile extends ThrownItemEntity {
    public WaterVialProjectile(EntityType<? extends FireVialProjectile> entityType, World world) {
        super(entityType, world);
    }

    public WaterVialProjectile(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, owner, world);
    }

    public WaterVialProjectile(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return ModItems.WATER_VIAL;
    }


    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            entity.damage(this.getDamageSources().drown(), (float)5.0);
            this.discard();
        }}

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient && !this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            Box box = this.getBoundingBox().expand(4.0, 2.0, 4.0);
            List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity livingEntity : list) {

            }

    }
}}
