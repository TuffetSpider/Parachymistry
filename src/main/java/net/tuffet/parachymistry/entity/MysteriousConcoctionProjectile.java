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
        switch(testConcoctionComponent(this.getStack())){
            case"minecraft:ender_pearl":{
                //example, put effect here
                this.discard();
                break;
            }
            case"minecraft:brown_mushroom":{
                //example, put effect here
                this.discard();
                break;
            }
            default:{
                this.discard();
                break;
            }

        }}

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        switch(testConcoctionComponent(this.getStack())){
            case"minecraft:ender_pearl":{

                //Example, put effect here
                this.discard();
                break;
            }
            case"minecraft:brown_mushroom":{

                //Example, put effect here
                this.discard();break;
            }
            default:{
                this.discard();
                break;
            }
        }
    }
    public String testConcoctionComponent(ItemStack stack){
        return Objects.requireNonNull(stack.get(ModComponents.TINCTUREITEM)).toString().replace("TinctureIngredientComponent[ingredient=","").replace("]","");
    }

}
