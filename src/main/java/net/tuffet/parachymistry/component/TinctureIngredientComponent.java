package net.tuffet.parachymistry.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;

public record TinctureIngredientComponent(String ingredient) {
    public static final Codec<TinctureIngredientComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.STRING.fieldOf("ingredient").forGetter(TinctureIngredientComponent::ingredient)
        ).apply(builder, TinctureIngredientComponent::new);
    });
}
