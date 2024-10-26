package net.tuffet.parachymistry.item.custom;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tuffet.parachymistry.component.ModComponents;
import net.tuffet.parachymistry.effect.ModEffects;

import java.util.List;
import java.util.Objects;

public class MysteriousTinctureClass extends Item{
    public MysteriousTinctureClass(Settings settings) {
        super(settings);

    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user.isPlayer()){
            PlayerEntity user1 = (PlayerEntity) user;
        }

        // This tests the components added during crafting, to add your own effects do  case "<ITEM>":{EFFECT}, added some examples that function here
        if(stack.get(ModComponents.TINCTUREITEM)!=null){
        switch(testComponent(stack)){
            // For example, this case tests if the tincture has an ender pearl component, and if it does, teleports the player to the entity that last attacked them, supposing it exists
            case "minecraft:ender_pearl":{
                {if(user.getLastAttacker()!=null){
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
            case"minecraft:ender_eye":{
                Vec3d Position = user.raycast(20,1f,true).getPos();
            user.teleport(Position.getX(),Position.getY(),Position.getZ(),true);
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

            }}


            default:{
                break;
            }
        }}
        return super.finishUsing(stack, world, user);
    }
    // This is the function being used to test what component an item has
    public String testComponent(ItemStack stack){
        return Objects.requireNonNull(stack.get(ModComponents.TINCTUREITEM)).toString().replace("TinctureIngredientComponent[ingredient=","").replace("]","");
    }
}






