package net.tuffet.parachymistry.tags;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;

public class ModTags implements ModInitializer {
public static class Items {
    public static final TagKey<Item> TINCTURE_INGREDIENTS = TagKey.of(RegistryKeys.ITEM,Identifier.of(Parachymistry.MOD_ID,"tincture_ingredients"));
}
private static TagKey<Item> createTag(String name){
    return TagKey.of(RegistryKeys.ITEM, Identifier.of(Parachymistry.MOD_ID, name));
}
public static void initialize(){

}
    @Override
    public void onInitialize() {

    }
}
