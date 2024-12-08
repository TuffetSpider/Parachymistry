package net.tuffet.parachymistry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModEntities {

    public static EntityType<AirVialProjectile> AIR_VIAL;
    public static EntityType<WaterVialProjectile> WATER_VIAL;
    public static EntityType<EarthVialProjectile> EARTH_VIAL;
    public static EntityType<FireVialProjectile> FIRE_VIAL;
    public static EntityType<AetherVialProjectile> AETHER_VIAL;
    public static EntityType<MysteriousConcoctionProjectile> CONCOCTION_PROJECTILE;

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }

	public static void init() {
        AIR_VIAL = register("air_vial", EntityType.Builder.<AirVialProjectile>create(AirVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
        WATER_VIAL = register("water_vial", EntityType.Builder.<WaterVialProjectile>create(WaterVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
        EARTH_VIAL = register("earth_vial", EntityType.Builder.<EarthVialProjectile>create(EarthVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
        FIRE_VIAL = register("fire_vial", EntityType.Builder.<FireVialProjectile>create(FireVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
        AETHER_VIAL = register("aether_vial", EntityType.Builder.<AetherVialProjectile>create(AetherVialProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
        CONCOCTION_PROJECTILE = register("concoction", EntityType.Builder.<MysteriousConcoctionProjectile>create(MysteriousConcoctionProjectile::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    }
}
