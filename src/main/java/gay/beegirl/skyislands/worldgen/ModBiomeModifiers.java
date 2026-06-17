package gay.beegirl.skyislands.worldgen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.tags.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> LAND_ALEXANDRITE_ORE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, SkysSkyIslands.createId("land_alexandrite_ore_modifier"));
    public static final ResourceKey<BiomeModifier> SKY_ALEXANDRITE_ORE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, SkysSkyIslands.createId("sky_alexandrite_ore_modifier"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(LAND_ALEXANDRITE_ORE_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LAND_ALEXANDRITE_ORE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(SKY_ALEXANDRITE_ORE_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.SKY_ISLANDS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SKY_ALEXANDRITE_ORE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
