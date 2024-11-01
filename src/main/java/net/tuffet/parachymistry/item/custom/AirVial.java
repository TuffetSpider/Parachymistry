package net.tuffet.parachymistry.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.tuffet.parachymistry.entity.AirVialProjectile;

public class AirVial extends Item implements ProjectileItem {
    public AirVial(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 20);
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            AirVialProjectile airVialProjectile = new AirVialProjectile(world, user);
            airVialProjectile.setItem(itemStack);
            airVialProjectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(airVialProjectile);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        AirVialProjectile airVialProjectile = new AirVialProjectile(world, pos.getX(), pos.getY(), pos.getZ());
        airVialProjectile.setItem(stack);
        return airVialProjectile;
    }
}