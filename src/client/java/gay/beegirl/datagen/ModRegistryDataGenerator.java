package gay.beegirl.datagen;

import gay.beegirl.registry.ModRegistries;
import gay.beegirl.registry.ModRegistryResourceKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataGenerator extends FabricDynamicRegistryProvider {
    public ModRegistryDataGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.TRIM_MATERIAL));
        entries.addAll(registries.lookupOrThrow(Registries.TRIM_PATTERN));
        entries.addAll(registries.lookupOrThrow(ModRegistryResourceKeys.GLIDER_DESIGN));
    }

    @Override
    public String getName() {
        return "Registries";
    }
}
