package net.tuffet.parachymistry.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;
import net.tuffet.parachymistry.item.custom.*;

public class ModItems {


    public static final Item ALCHYMICAL_VIAL = registerItem("alchymical_vial", new VialItemClass(new Item.Settings()));
    public static final Item FIRE_VIAL = registerItem("vial_of_fire", new FireVialClass(new Item.Settings()));
    public static final Item WATER_VIAL = registerItem("vial_of_water", new WaterVialClass(new Item.Settings()));
    public static final Item EARTH_VIAL = registerItem("vial_of_earth", new EarthVialClass(new Item.Settings()));
    public static final Item AIR_VIAL = registerItem("vial_of_air", new AirVialClass(new Item.Settings()));
    public static final Item AETHER_VIAL = registerItem("vial_of_aether", new AetherVialClass(new Item.Settings()));
    public static final Item QUICKLIME = registerItem("quicklime", new Item(new Item.Settings()));

         private static Item registerItem(String name, Item item) {
            return Registry.register(Registries.ITEM, Identifier.of(Parachymistry.MOD_ID, name), item);
        }

        public static void registerModItems() {

        }

}
