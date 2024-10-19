package net.tuffet.parachymistry.effect;

import jdk.jshell.Snippet;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tuffet.parachymistry.Parachymistry;

import static net.minecraft.util.PathUtil.validatePath;

public class ModEffects implements ModInitializer {

    public static final RegistryEntry<StatusEffect> POSEIDON_EFFECT;
    public static final RegistryEntry<StatusEffect> GAIA_EFFECT;
    public static final RegistryEntry<StatusEffect> AEOLUS_EFFECT;
    public static final RegistryEntry<StatusEffect> HADES_EFFECT;
    public static final RegistryEntry<StatusEffect> AETHERIAL_SACRIFICE_EFFECT;
    public static final RegistryEntry<StatusEffect> SWIFTSTRIDE_EFFECT;

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Parachymistry.MOD_ID,id), statusEffect);
    }



    static {
        GAIA_EFFECT = register("gaiasgrasp", new GaiasGraspEffect().addAttributeModifier(EntityAttributes.GENERIC_GRAVITY, Identifier.of(Parachymistry.MOD_ID,"effect.gaiasgrasp"),10,EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        POSEIDON_EFFECT = register("poseidonswrath", new PoseidonsWrathEffect());
        AEOLUS_EFFECT= register("aeolusbreath", new AeolusBreathEffect().addAttributeModifier(EntityAttributes.GENERIC_GRAVITY, Identifier.of(Parachymistry.MOD_ID,"effect.aeolusbreath"), -0.95f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        HADES_EFFECT = register("hadeshellfire", new HadesHellfireEffect(StatusEffectCategory.HARMFUL, 0xe9b8b3));
        AETHERIAL_SACRIFICE_EFFECT = register("aetherialsacrifice", new AetherialSacrificeEffect(StatusEffectCategory.HARMFUL, 0).addAttributeModifier(EntityAttributes.GENERIC_GRAVITY, Identifier.of(Parachymistry.MOD_ID,"effect.aetherialsacrifice"),-1.05f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        SWIFTSTRIDE_EFFECT = register("swiftstride", new SwiftStrideEffect(StatusEffectCategory.BENEFICIAL,102156172).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY,Identifier.of(Parachymistry.MOD_ID,"effect.swiftstride"),1f, EntityAttributeModifier.Operation.ADD_VALUE));
    }

    public static void registerModEffects() {
    }
    @Override
    public void onInitialize() {
        // ...
    }}

