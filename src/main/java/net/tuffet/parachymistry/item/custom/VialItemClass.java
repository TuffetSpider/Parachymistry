package net.tuffet.parachymistry.item.custom;


import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.tuffet.parachymistry.item.ModItems;



public class VialItemClass extends Item {
    public VialItemClass(Settings settings) {
        super(settings);

    }
    protected void fill(ItemStack stack, PlayerEntity player, ItemStack outputStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        ItemUsage.exchangeStack(stack, player, outputStack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity.getClass() == net.minecraft.entity.mob.BlazeEntity.class){
            user.getWorld().playSound( null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.FIRE_VIAL));
            }
        } else if (entity.getClass()==net.minecraft.entity.mob.GuardianEntity.class) {
            user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.WATER_VIAL));
            }
        }else if(entity.getClass()==net.minecraft.entity.passive.SnifferEntity.class){
            user.getWorld().playSound( null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.EARTH_VIAL));
            }
        } else if (entity.getClass()==net.minecraft.entity.mob.BreezeEntity.class) {
            user.getWorld().playSound( null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.AIR_VIAL));
            }
        } else if (entity.getClass()==net.minecraft.entity.mob.ShulkerEntity.class) {
            user.getWorld().playSound( null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.AETHER_VIAL));
            }
        }
        return super.useOnEntity(stack, user, entity, hand);

    }



    }
