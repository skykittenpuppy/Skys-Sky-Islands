package gay.beegirl;

import gay.beegirl.datagen.*;
import gay.beegirl.registry.ModRegistryResourceKeys;
import gay.beegirl.registry.armor_trim.ModTrimMaterials;
import gay.beegirl.registry.armor_trim.ModTrimPatterns;
import gay.beegirl.registry.glider_pattern.GliderDesigns;
import gay.beegirl.worldgen.ModConfiguredFeatures;
import gay.beegirl.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class SkysSkyIslandsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModRegistryDataGenerator::new);
	}

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
        registryBuilder.add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap);
        registryBuilder.add(ModRegistryResourceKeys.GLIDER_DESIGN, GliderDesigns::bootstrap);
    }
}