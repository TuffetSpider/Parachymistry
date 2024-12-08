package net.tuffet.parachymistry.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.item.custom.*;

public class ModItems {


    public static Item ALCHYMICAL_VIAL;
    public static Item FIRE_VIAL;
    public static Item WATER_VIAL;
    public static Item EARTH_VIAL;
    public static Item AIR_VIAL;
    public static Item AETHER_VIAL;
    public static Item QUICKLIME;
    public static Item MERCURY;
    public static Item SALT;
    public static Item MYSTERIOUS_TINCTURE;
    public static Item MYSTERIOUS_CONCOCTION;

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Parachymistry.id(name), item);
    }

    public static void init() {
        ALCHYMICAL_VIAL = registerItem("alchymical_vial", new VialItem(new Item.Settings()));
        FIRE_VIAL = registerItem("vial_of_fire", new FireVial(new Item.Settings().maxCount(16)));
        WATER_VIAL = registerItem("vial_of_water", new WaterVial(new Item.Settings().maxCount(16)));
        EARTH_VIAL = registerItem("vial_of_earth", new EarthVial(new Item.Settings().maxCount(16)));
        AIR_VIAL = registerItem("vial_of_air", new AirVial(new Item.Settings().maxCount(16)));
        AETHER_VIAL = registerItem("vial_of_aether", new AetherVial(new Item.Settings().maxCount(16)));
        QUICKLIME = registerItem("quicklime", new Item(new Item.Settings()));
        MERCURY = registerItem("mercury", new Item(new Item.Settings().food(ModFoodComponents.MERCURY)));
        SALT = registerItem("salt", new Item(new Item.Settings().food(ModFoodComponents.SALT)));
        MYSTERIOUS_TINCTURE = registerItem("mysterious_tincture", new MysteriousTincture(new Item.Settings().food(ModFoodComponents.TINCTURE).maxCount(16)));
        MYSTERIOUS_CONCOCTION = registerItem("mysterious_concoction", new MysteriousConcoction(new Item.Settings().maxCount(16)));

        DispenserBlock.registerProjectileBehavior(ModItems.MYSTERIOUS_CONCOCTION);
        DispenserBlock.registerProjectileBehavior(ModItems.FIRE_VIAL);
        DispenserBlock.registerProjectileBehavior(ModItems.WATER_VIAL);
        DispenserBlock.registerProjectileBehavior(ModItems.EARTH_VIAL);
        DispenserBlock.registerProjectileBehavior(ModItems.AIR_VIAL);
        DispenserBlock.registerProjectileBehavior(ModItems.AETHER_VIAL);
    }
}
