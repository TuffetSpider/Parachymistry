package net.tuffet.parachymistry.entity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.item.custom.AirVial;

public class ModEntities implements ModInitializer {

    public static final EntityType<AirVialProjectile> AIR_VIAL;
    public static final EntityType<WaterVialProjectile> WATER_VIAL;
    public static final EntityType<EarthVialProjectile> EARTH_VIAL;
    public static final EntityType<FireVialProjectile> FIRE_VIAL;
    public static final EntityType<AetherVialProjectile> AETHER_VIAL;
    public static final EntityType<MysteriousConcoctionProjectile> CONCOCTION_PROJECTILE;

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }
static {
    AIR_VIAL =register ("air_vial", EntityType.Builder.<AirVialProjectile>create(AirVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    WATER_VIAL =register ("water_vial", EntityType.Builder.<WaterVialProjectile>create(WaterVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    EARTH_VIAL =register ("earth_vial", EntityType.Builder.<EarthVialProjectile>create(EarthVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    FIRE_VIAL =register ("fire_vial", EntityType.Builder.<FireVialProjectile>create(FireVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    AETHER_VIAL =register ("aether_vial", EntityType.Builder.<AetherVialProjectile>create(AetherVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    CONCOCTION_PROJECTILE =register ("concoction", EntityType.Builder.<MysteriousConcoctionProjectile>create(MysteriousConcoctionProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));

}
public static void Initialize(){

}



    @Override
    public void onInitialize() {

    }
}
