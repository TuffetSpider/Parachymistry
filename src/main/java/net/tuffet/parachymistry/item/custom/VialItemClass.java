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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        List<BlazeEntity> listblaze = world.getEntitiesByClass(BlazeEntity.class, user.getBoundingBox().expand(1.0), (entity) -> entity != null && entity.isAlive());
        List<GuardianEntity> listguardian = world.getEntitiesByClass(GuardianEntity.class, user.getBoundingBox().expand(1.0), (entity) -> entity != null && entity.isAlive());
        List<SnifferEntity> listsniffer = world.getEntitiesByClass(SnifferEntity.class, user.getBoundingBox().expand(1.0), (entity) -> entity != null && entity.isAlive());
        List<BreezeEntity> listbreeze = world.getEntitiesByClass(BreezeEntity.class, user.getBoundingBox().expand(1.0), (entity) -> entity != null && entity.isAlive());
        List<ShulkerEntity> listshulker = world.getEntitiesByClass(ShulkerEntity.class, user.getBoundingBox().expand(1.0), (entity) -> entity != null && entity.isAlive());
        ItemStack itemStack = user.getStackInHand(hand);
        if (!listblaze.isEmpty()) {
            BlazeEntity blazeEntity = (BlazeEntity) listblaze.get(0);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.getPos());
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayerEntity, itemStack, blazeEntity);
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.FIRE_VIAL));
            }
        }


        else if(!listguardian.isEmpty()) {
            GuardianEntity guardianEntity = (GuardianEntity)listguardian.get(0);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.getPos());
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayerEntity, itemStack, guardianEntity);
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.WATER_VIAL));
            }}
        else if (!listsniffer.isEmpty()) {
            SnifferEntity snifferEntity = (SnifferEntity)listsniffer.get(0);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.getPos());
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayerEntity, itemStack, snifferEntity);
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.EARTH_VIAL));

            }}
        else if (!listbreeze.isEmpty()) {
            BreezeEntity breezeEntity = (BreezeEntity)listbreeze.get(0);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.getPos());
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayerEntity, itemStack, breezeEntity);
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.AIR_VIAL));

            }}
        else if(!listshulker.isEmpty()) {
            ShulkerEntity shulkerEntity = (ShulkerEntity)listshulker.get(0);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_OMINOUS_BOTTLE_DISPOSE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.getPos());
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) user;
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayerEntity, itemStack, shulkerEntity);
                this.fill(user.getWeaponStack(), user, new ItemStack(ModItems.AETHER_VIAL));
            }}  return TypedActionResult.success(user.getWeaponStack());



    }}
