package net.tuffet.parachymistry.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class MysteriousTinctureRecipe implements Recipe<AlchymyRecipeInput> {

    final Ingredient base;
    final Ingredient reagent;
    final Ingredient catalyst;
    final ItemStack result;


    public MysteriousTinctureRecipe(Ingredient base, Ingredient reagent, Ingredient catalyst, ItemStack result) {



        this.base = base;
        this.reagent = reagent;
        this.catalyst = catalyst;
        this.result = result;
    }



    public boolean matches(AlchymyRecipeInput alchymyRecipeInput, World world) {
        return this.base.test(alchymyRecipeInput.base()) && this.reagent.test(alchymyRecipeInput.reagent()) && this.catalyst.test(alchymyRecipeInput.catalyst());
    }


    public ItemStack craft(AlchymyRecipeInput alchymyRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        return this.result;
    }


    public boolean fits(int width, int height) {
        return false;
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TINCTURE_SERIALIZER;
    }

    public RecipeType<?> getType() {
        return ModRecipes.TINCTURE;
    }


    public boolean isEmpty() {
        return Stream.of(this.base, this.reagent, this.catalyst).anyMatch(Ingredient::isEmpty);
    }

    public static class Serializer implements RecipeSerializer<net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe> {
        private static final MapCodec<MysteriousTinctureRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter((recipe) -> recipe.base), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("reagent").forGetter((recipe) -> recipe.reagent), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("catalyst").forGetter((recipe) -> recipe.catalyst), ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter((recipe) -> recipe.result)).apply(instance, MysteriousTinctureRecipe::new));
        public static final PacketCodec<RegistryByteBuf, MysteriousTinctureRecipe> PACKET_CODEC = PacketCodec.ofStatic(net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe.Serializer::write, net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe.Serializer::read);

        public Serializer() {

        }

        public MapCodec<net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe> codec() {
            return CODEC;
        }

        public PacketCodec<RegistryByteBuf, net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe read(RegistryByteBuf buf) {
            Ingredient ingredient = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredient2 = Ingredient.PACKET_CODEC.decode(buf);
            Ingredient ingredient3 = Ingredient.PACKET_CODEC.decode(buf);
            ItemStack itemStack = ItemStack.PACKET_CODEC.decode(buf);
            return new net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe(ingredient, ingredient2, ingredient3, itemStack);
        }

        private static void write(RegistryByteBuf buf, net.tuffet.parachymistry.recipe.MysteriousTinctureRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.base);
            Ingredient.PACKET_CODEC.encode(buf, recipe.reagent);
            Ingredient.PACKET_CODEC.encode(buf, recipe.catalyst);
            ItemStack.PACKET_CODEC.encode(buf, recipe.result);
        }
    }
}

