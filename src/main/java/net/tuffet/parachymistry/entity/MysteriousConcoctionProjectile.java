package net.tuffet.parachymistry.entity;


import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;

import net.minecraft.server.world.ServerWorld;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import net.minecraft.world.event.GameEvent;

import net.tuffet.parachymistry.item.ModItems;

import java.util.List;
import java.util.Objects;

import static net.tuffet.parachymistry.Parachymistry.SHOULD_HAVE_DAMAGING_VIALS;
import static net.tuffet.parachymistry.Parachymistry.TINCTUREITEM;

public class MysteriousConcoctionProjectile extends ThrownItemEntity {
    public MysteriousConcoctionProjectile(EntityType<? extends MysteriousConcoctionProjectile> entityType, World world) {
        super(entityType, world);
    }

    public MysteriousConcoctionProjectile(World world, LivingEntity owner) {
        super(ModEntities.CONCOCTION_PROJECTILE, owner, world);
    }

    public MysteriousConcoctionProjectile(World world, double x, double y, double z) {
        super(ModEntities.CONCOCTION_PROJECTILE, x, y, z, world);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MYSTERIOUS_CONCOCTION;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) entityHitResult.getEntity().damage(this.getDamageSources().indirectMagic(this, this.getOwner()), (float)2.0);
        switch (testConcoctionComponent(this.getStack())) {
            case "minecraft:ender_pearl": {
                World world = this.getWorld();

                if (!world.isClient&&entityHitResult.getEntity().isLiving()) {
                    LivingEntity user = (LivingEntity) entityHitResult.getEntity();
                    for(int i = 0; i < 16; ++i) {
                        double d = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                        double e = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                        double f = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                        if (user.hasVehicle()) {
                            user.stopRiding();
                        }

                        Vec3d vec3d = user.getPos();
                        if (user.teleport(d, e, f, true)) {
                            world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                            user.onLanding();
                            this.discard();
                            break;
                        }
                    }}

                break;
            }
            case "minecraft:brown_mushroom": {
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    Objects.requireNonNull(livingEntity.getAttributeInstance(EntityAttributes.GENERIC_SCALE)).setBaseValue(livingEntity.getAttributeValue(EntityAttributes.GENERIC_SCALE)-0.1);
                    this.discard();
                }
                break;
            }
            case "minecraft:golden_apple": {
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                if (entityHitResult.getEntity().isLiving()) {
                    livingEntity.setAbsorptionAmount(0);

                }
                }this.discard();
                break;
            }
            case "minecraft:red_mushroom": {
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    if(livingEntity.getAttributeInstance(EntityAttributes.GENERIC_SCALE).getValue()<1.8) {
                        Objects.requireNonNull(livingEntity.getAttributeInstance(EntityAttributes.GENERIC_SCALE)).setBaseValue(livingEntity.getAttributeValue(EntityAttributes.GENERIC_SCALE) + 0.1);
                    }
                    this.discard();
                }
                break;
            }
            case "minecraft:glowstone": {
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0));
                    this.discard();
                }
                break;
            }
            case "minecraft:soul_soil": {
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    if(livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,200,0));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,200,0));
                    this.discard();
                }
                break;
            }
            case "parachymistry:mercury": {
                if (entityHitResult.getEntity().isLiving()) {
                LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.removeStatusEffect(StatusEffects.BLINDNESS);
                    livingEntity.removeStatusEffect(StatusEffects.WITHER);
                    livingEntity.removeStatusEffect(StatusEffects.SLOWNESS);
                    livingEntity.removeStatusEffect(StatusEffects.HUNGER);
                    livingEntity.removeStatusEffect(StatusEffects.WEAKNESS);
                    livingEntity.removeStatusEffect(StatusEffects.DARKNESS);
                    if(livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,200,0));
                }
            }

            case "parachymistry:quicklime": {
                if (entityHitResult.getEntity().isLiving()) {
                    LivingEntity livingEntity = (LivingEntity) entityHitResult.getEntity();
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,50,0));
                    if (livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) livingEntity.setOnFireForTicks(50);
                    if (livingEntity.isTouchingWater()) {
                        livingEntity.setVelocity(0,5,0);
                        livingEntity.getWorld().createExplosion(livingEntity,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(),0f, World.ExplosionSourceType.MOB);
                        if(livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) livingEntity.damage(livingEntity.getWorld().getDamageSources().explosion(livingEntity,livingEntity),2);
                    }
                }
            }
            case "minecraft:rabbit_foot": {
                if (!entityHitResult.getEntity().isPlayer()&&entityHitResult.getEntity().isLiving() &&!entityHitResult.getEntity().isInvulnerable()) {
                    RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT,entityHitResult.getEntity().getWorld());
                    rabbit.setPos(entityHitResult.getEntity().getX(),entityHitResult.getEntity().getY()+1,entityHitResult.getEntity().getZ());
                    rabbit.setVariant(RabbitEntity.RabbitType.byId(random.nextBetween(0,7)));
                    rabbit.addCommandTag("parachymistryrabbit");
                    entityHitResult.getEntity().getWorld().spawnEntity(rabbit);
                    entityHitResult.getEntity().remove(RemovalReason.DISCARDED);
                }
            }
            default: {
                this.discard();
                break;
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.getWorld().playSound(this,this.getBlockPos(),SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.PLAYERS,1f,1f);
        if(this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0, 0, 1.0);
        }
        switch(testConcoctionComponent(this.getStack())){
            case "minecraft:glowstone": {
                if (!this.getWorld().isClient) {
                    this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
                    Box box = this.getBoundingBox().expand(3.5, 2.0, 3.5);
                    List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
                    if(this.getWorld() instanceof ServerWorld serverworld) {
                    serverworld.spawnParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(),this.getY(),this.getZ(), 0, 0, 0, 0, 1.0);
                    }
                    for (LivingEntity livingEntity : list) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,600,0));
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,600,0));
                    }
                }
                this.discard();
                break;
            }
            case"minecraft:armadillo_scute": {
                if (!this.getWorld().isClient) {
                    this.playSound(SoundEvents.BLOCK_GLASS_BREAK,1f,1f);
                    Box box = this.getBoundingBox().expand(10, 10, 10);
                    List<ProjectileEntity> list = this.getWorld().getNonSpectatingEntities(ProjectileEntity.class, box);
                    if(this.getWorld() instanceof ServerWorld serverworld) {
                     serverworld.spawnParticles(ParticleTypes.POOF, this.getX(),this.getY(),this.getZ(), 0, 0, 0, 0, 1.0);
                    }
                    for (ProjectileEntity projectileEntity : list) {
                      projectileEntity.setVelocity(0,0,0);
                      ;
                    }
                }
                this.discard();
                break;
            }
            case "minecraft:gunpowder": {
                Box box = this.getBoundingBox().expand(7);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class,box);
                for (LivingEntity livingEntity : list) {
                    if(livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) livingEntity.setOnFireForTicks(20);
                    livingEntity.getWorld().createExplosion(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0f , World.ExplosionSourceType.MOB);
                    double magnitude = (Math.pow(livingEntity.getY() - this.getY(), 2)) + ((Math.pow(livingEntity.getX() - this.getX(), 2)) + (Math.pow(livingEntity.getZ() - this.getZ(), 2)));
                    livingEntity.setVelocity((new Vec3d((livingEntity.getX() - this.getX()), (livingEntity.getY() - this.getY()) + 0.4, (livingEntity.getZ() - this.getZ())).multiply(Math.min((3 / magnitude), 3))));
                }
                this.discard();
                break;
            }
            case "minecraft:pufferfish": {
                PufferfishEntity pufferfish = new PufferfishEntity(EntityType.PUFFERFISH, this.getWorld());
                pufferfish.setPos(this.getX(),this.getY(),this.getZ());
                this.getWorld().spawnEntity(pufferfish);
                this.discard();
                break;
            }
            case "minecraft:iron_block": {
                Box box = this.getBoundingBox().expand(3);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);

                for (LivingEntity livingEntity : list) {
                    FallingBlockEntity anvil = FallingBlockEntity.spawnFromBlock(livingEntity.getWorld(),livingEntity.getBlockPos().add(0,2,0), Blocks.ANVIL.getDefaultState());anvil.setDestroyedOnLanding();
                    if (livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) anvil.setHurtEntities(5,5);
                }
                this.discard();
                break;
            }
            case "parachymistry:salt": {
                Box box = this.getBoundingBox().expand(3);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);

                for (LivingEntity livingEntity : list) livingEntity.clearStatusEffects();
                this.discard();
                break;
            }
            case "minecraft:ender_eye": {
                Box box = this.getBoundingBox().expand(3);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);

                for (LivingEntity livingEntity : list) {
                    if(!livingEntity.isPlayer()) {
                        livingEntity.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, Objects.requireNonNull(this.getPos()));
                        livingEntity.setAttacker(livingEntity.getEntityWorld().getClosestEntity(LivingEntity.class, TargetPredicate.createAttackable(),livingEntity,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(),livingEntity.getBoundingBox().expand(5)));
                    }
                }
                this.discard();
                break;
            }
            case "minecraft:powder_snow_bucket": {
                Box box = this.getBoundingBox().expand(3);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
                for (LivingEntity livingEntity : list) {
                    if (livingEntity.getWorld().getGameRules().getBoolean(SHOULD_HAVE_DAMAGING_VIALS)) livingEntity.setFrozenTicks(600);
                    else livingEntity.setFrozenTicks(140);
                }
                this.discard();
                break;
            }
            case "minecraft:sculk_sensor": {
                Box box = this.getBoundingBox().expand(4);
                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
                if (Objects.requireNonNull(this.getOwner()).isLiving()){
                    LivingEntity livingOwner = (LivingEntity) this.getOwner();
                    livingOwner.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,100,0));
                }

                for (LivingEntity livingEntity : list) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,200,0),this.getOwner());
                    livingEntity.playSoundIfNotSilent(SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK);
                    livingEntity.getWorld().addParticle(ParticleTypes.SCULK_CHARGE_POP,true,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(),0,0,0);
                    if (livingEntity.isMobOrPlayer()&&!livingEntity.isPlayer()){
                        MobEntity mob = (MobEntity) livingEntity;
                        mob.setTarget(null);
                    }
                }
                this.discard();
                break;
            }
            default:{
                this.discard();
                break;
            }
        }
    }

    public String testConcoctionComponent(ItemStack stack){
        if (stack.get(TINCTUREITEM) == null) {
            return "null";
        }
        else return Objects.requireNonNull(stack.get(TINCTUREITEM)).toString().replace("TinctureIngredientComponent[ingredient=","").replace("]","");
    }

}
