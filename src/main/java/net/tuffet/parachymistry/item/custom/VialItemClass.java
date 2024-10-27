package net.tuffet.parachymistry.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.sensor.NearestLivingEntitiesSensor;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.item.ModItems;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VialItemClass extends Item {
    public VialItemClass(Settings settings) {
        super(settings);

    }
    protected ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(stack, player, outputStack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity.getClass() == net.minecraft.entity.mob.BlazeEntity.class){
            user.getWorld().playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.FIRE_VIAL));
            }
        } else if (entity.getClass()==net.minecraft.entity.mob.GuardianEntity.class) {
            user.getWorld().playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.WATER_VIAL));
            }
        }else if(entity.getClass()==net.minecraft.entity.passive.SnifferEntity.class){
            user.getWorld().playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.EARTH_VIAL));
            }
        } else if (entity.getClass()==net.minecraft.entity.mob.BreezeEntity.class) {
            user.getWorld().playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.AIR_VIAL));
            }
        } else if (entity.getClass()==net.minecraft.entity.mob.ShulkerEntity.class) {
            user.getWorld().playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            entity.damage(user.getWorld().getDamageSources().indirectMagic(user,user),1);
            if (user instanceof ServerPlayerEntity) {
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.AETHER_VIAL));
            }
        }
        return super.useOnEntity(stack, user, entity, hand);

    }



    }
