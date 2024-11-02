package net.tuffet.parachymistry.item.custom;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tuffet.parachymistry.effect.ModEffects;
import java.util.List;
import java.util.Objects;

import static net.tuffet.parachymistry.Parachymistry.TINCTUREITEM;

public class MysteriousTincture extends Item{
    public MysteriousTincture(Settings settings) {
        super(settings);

    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user.isPlayer()){
            PlayerEntity playeruser = (PlayerEntity) user;
            playeruser.getItemCooldownManager().set(this, 40);

        }

        // This tests the components added during crafting, to add your own effects do  case "<ITEM>":{EFFECT}, added some examples that function here
        if (stack.get(TINCTUREITEM) !=null) {
        switch(testComponent(stack)){
            // For example, this case tests if the tincture has an ender pearl component, and if it does, teleports the player to the entity that last attacked them, supposing it exists
            case "minecraft:ender_pearl":{
                {if (user.getLastAttacker() != null) {
                    user.teleport(Objects.requireNonNull(user.getLastAttacker()).getX(),user.getLastAttacker().getY(),user.getLastAttacker().getZ(),true);
                }}
                break;
            }
            // These cases just use status effects, most of them being attributes, these are the simplest to add if you'd like to do something
            case"minecraft:soul_soil":{
                user.addStatusEffect(new StatusEffectInstance(ModEffects.SWIFTSTRIDE_EFFECT,2400,0));
                break;
            }
            case"minecraft:iron_block":{
                user.addStatusEffect(new StatusEffectInstance(ModEffects.IRONWARD_EFFECT,1200,0));
                break;
            }
            case"minecraft:pufferfish":{
                user.addStatusEffect(new StatusEffectInstance(ModEffects.VENOMOUS_RECIPROCATION_EFFECT,1200,0));
                break;
            }
            case"minecraft:golden_apple":{
                user.addStatusEffect(new StatusEffectInstance(ModEffects.KARMIC_SHIELDING_EFFECT,240,0));
                break;
            }
            case"minecraft:armadillo_scute":{
                user.addStatusEffect(new StatusEffectInstance(ModEffects.STEADFAST_EFFECT,1200));
                break;
            }

            case"minecraft:red_mushroom":{
                Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_SCALE)).setBaseValue(user.getAttributeValue(EntityAttributes.GENERIC_SCALE)+0.1);
                break;
            }

            case"minecraft:brown_mushroom":{
                Objects.requireNonNull(user.getAttributeInstance(EntityAttributes.GENERIC_SCALE)).setBaseValue(user.getAttributeValue(EntityAttributes.GENERIC_SCALE)-0.1);
                break;
            }
            case"minecraft:glowstone":{
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,600,0));
                break;
            }
            case"minecraft:ender_eye":{
                Vec3d Position = user.raycast(20,1f,true).getPos();
            user.teleport(Position.getX(),Position.getY(),Position.getZ(),true);
            break;
            }
            case"minecraft:gunpowder":{
                user.getWorld().createExplosion(user,user.getX(),user.getY()+1,user.getZ(),0,World.ExplosionSourceType.MOB);
                Box box = user.getBoundingBox().expand(7);
                List<Entity> list = user.getWorld().getNonSpectatingEntities(Entity.class,box);
                list.remove(user);
                for (Entity entity : list) {
                    entity.setOnFireForTicks(20);
                    double magnitude = (Math.pow(entity.getY()-user.getY(),2))+((Math.pow(entity.getX()-user.getX(),2))+(Math.pow(entity.getZ()-user.getZ(),2)));
                    entity.setVelocity((new Vec3d((entity.getX()-user.getX()),(entity.getY()-user.getY())+0.4,(entity.getZ()-user.getZ())).multiply(Math.min((3/magnitude),3))));

            }break;}
            case"parachymistry:mercury":{
                user.removeStatusEffect(StatusEffects.BLINDNESS);
                user.removeStatusEffect(StatusEffects.WITHER);
                user.removeStatusEffect(StatusEffects.SLOWNESS);
                user.removeStatusEffect(StatusEffects.HUNGER);
                user.removeStatusEffect(StatusEffects.WEAKNESS);
                user.removeStatusEffect(StatusEffects.DARKNESS);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,300,3));
            break;


            }
            case"parachymistry:quicklime":{
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,200,0));
                user.setOnFireForTicks(200);
                if(user.isTouchingWater()){
                    user.setVelocity(0,5,0);
                    user.getWorld().createExplosion(user,user.getX(),user.getY(),user.getZ(),0f, World.ExplosionSourceType.MOB);
                    user.damage(user.getWorld().getDamageSources().explosion(user,user),5);
                }
            }break;
            case"parachymistry:salt": {
                user.clearStatusEffects();
                user.setHealth(user.getMaxHealth());
                user.setAbsorptionAmount(0);
                user.setVelocity(0, 0, 0);
                user.extinguishWithSound();
                if (user.isPlayer()) {
                    PlayerEntity userPlayer = (PlayerEntity) user;
                    userPlayer.getHungerManager().setFoodLevel(0);
                }
            }break;
            case"minecraft:powder_snow_bucket":{
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,300,1));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,300,1));
                user.setFrozenTicks(140);
                break;
            }
            case"minecraft:sculk_sensor":{
                user.addVelocity(user.getRotationVecClient().negate().multiply(2));
                user.getWorld().addParticle(ParticleTypes.SONIC_BOOM,true,user.getX(),user.getY(),user.getZ(),0,0,0);
                user.getWorld().playSound(user,user.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS,1f,1f);
                break;

            }
            case"minecraft:rabbit_foot":{
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK,600,1));
            break;
            }

            default:{
                break;
            }
        }}
        return super.finishUsing(stack, world, user);
    }
    // This is the function being used to test what component an item has
    public String testComponent(ItemStack stack){
        return Objects.requireNonNull(stack.get(TINCTUREITEM)).toString().replace("TinctureIngredientComponent[ingredient=","").replace("]","");
    }

}






