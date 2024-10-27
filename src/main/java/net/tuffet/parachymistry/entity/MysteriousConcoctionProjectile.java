package net.tuffet.parachymistry.entity;


import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;

import net.minecraft.server.world.ServerWorld;

import net.minecraft.sound.SoundEvents;

import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import net.tuffet.parachymistry.component.ModComponents;

import net.tuffet.parachymistry.item.ModItems;

import java.util.List;
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

                this.discard();
                break;
            }
            case"minecraft:brown_mushroom":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    Objects.requireNonNull(livingEntity.getAttributeInstance(EntityAttributes.GENERIC_SCALE)).setBaseValue(livingEntity.getAttributeValue(EntityAttributes.GENERIC_SCALE)-0.1);
                    this.discard();
                }
                break;
            }
            case"minecraft:golden_apple":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                if (entityHitResult.getEntity().isLiving()) {
                    livingEntity.setAbsorptionAmount(0);
                    this.discard();
                }
                }
                break;
            }


            case"minecraft:red_mushroom":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    Objects.requireNonNull(livingEntity.getAttributeInstance(EntityAttributes.GENERIC_SCALE)).setBaseValue(livingEntity.getAttributeValue(EntityAttributes.GENERIC_SCALE)+0.1);
                    this.discard();
                }
                break;
            }

            case"minecraft:glowstone":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,600,0));
                    this.discard();
                }
                break;
            }


            case"minecraft:soul_soil":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,200,0));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,200,0));
                    this.discard();
                }
                break;
            }
            case"parachymistry:mercury":{
                if (entityHitResult.getEntity().isLiving()) {
                LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.removeStatusEffect(StatusEffects.BLINDNESS);
                    livingEntity.removeStatusEffect(StatusEffects.WITHER);
                    livingEntity.removeStatusEffect(StatusEffects.SLOWNESS);
                    livingEntity.removeStatusEffect(StatusEffects.HUNGER);
                    livingEntity.removeStatusEffect(StatusEffects.WEAKNESS);
                    livingEntity.removeStatusEffect(StatusEffects.DARKNESS);
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,200,0));
            }}

            case"parachymistry:quicklime":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,50,0));
                    livingEntity.setOnFireForTicks(50);
                    if(livingEntity.isTouchingWater()){
                        livingEntity.setVelocity(0,5,0);
                        livingEntity.getWorld().createExplosion(livingEntity,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(),0f, World.ExplosionSourceType.MOB);
                        livingEntity.damage(livingEntity.getWorld().getDamageSources().explosion(livingEntity,livingEntity),2);
                }}}



            case "minecraft:ender_eye":{
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    Vec3d Position = livingEntity.raycast(20,1f,true).getPos();
                    livingEntity.teleport(Position.getX(),Position.getY(),Position.getZ(),true);
                    }}

            default:{
                this.discard();
                break;
            }

        }}

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        switch(testConcoctionComponent(this.getStack())){
            case"minecraft:glowstone":{

                if (!this.getWorld().isClient && this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
                    Box box = this.getBoundingBox().expand(3.5, 2.0, 3.5);
                    List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
                    ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(),this.getY(),this.getZ(), 0, 0, 0, 0, 1.0);
                    for (LivingEntity livingEntity : list) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,600,0));
                    }}
                this.discard();
                break;
            }

            case"minecraft:armadillo_scute":{

                if (!this.getWorld().isClient && this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
                    Box box = this.getBoundingBox().expand(10, 10, 10);
                    List<ProjectileEntity> list = this.getWorld().getNonSpectatingEntities(ProjectileEntity.class, box);
                    ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.POOF, this.getX(),this.getY(),this.getZ(), 0, 0, 0, 0, 1.0);
                    for (ProjectileEntity projectileEntity : list) {
                      projectileEntity.setVelocity(0,0,0);
                      ;
                    }}
                this.discard();
                break;
            }
            case"minecraft:gunpowder":{

                Box box = this.getBoundingBox().expand(7);
                List<Entity> list = this.getWorld().getNonSpectatingEntities(Entity.class,box);
                list.remove(this);
                for (Entity entity : list) {
                    entity.setOnFireForTicks(20);
                    entity.getWorld().createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), 0f , World.ExplosionSourceType.MOB);
                    double magnitude = (Math.pow(entity.getY() - this.getY(), 2)) + ((Math.pow(entity.getX() - this.getX(), 2)) + (Math.pow(entity.getZ() - this.getZ(), 2)));
                    entity.setVelocity((new Vec3d((entity.getX() - this.getX()), (entity.getY() - this.getY()) + 0.4, (entity.getZ() - this.getZ())).multiply(Math.min((3 / magnitude), 3))));
                }

                    //Example, put effect here
                this.discard();break;
            }
            case"minecraft:pufferfish":{
                PufferfishEntity pufferfish = new PufferfishEntity(EntityType.PUFFERFISH, this.getWorld());
                pufferfish.setPos(this.getX(),this.getY(),this.getZ());
                this.getWorld().spawnEntity(pufferfish);


                this.discard();
                break;
            }
            case"minecraft:iron_block":{
                Box box = this.getBoundingBox().expand(3);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);

                for (LivingEntity livingEntity : list) {
                    FallingBlockEntity anvil = FallingBlockEntity.spawnFromBlock(livingEntity.getWorld(),livingEntity.getBlockPos().add(0,2,0), Blocks.ANVIL.getDefaultState());
                    anvil.setDestroyedOnLanding();
                    anvil.setHurtEntities(5,5);
                    livingEntity.getWorld().spawnEntity(anvil);
            }this.discard();
                break;
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
