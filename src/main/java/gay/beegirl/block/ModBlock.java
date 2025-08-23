package gay.beegirl.block;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlock {
    public static final Block RAW_ZONAITE_BLOCK = registerBlock("raw_zonaite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
    ));
    public static final Block REFINED_ZONAITE_BLOCK = registerBlock("refined_zonaite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
    ));
    public static final Block STONE_ZONAITE_ORE = registerBlock("stone_zonaite_ore", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    ));
    public static final Block DEEPSLATE_ZONAITE_ORE = registerBlock("deepslate_zonaite_ore", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
    ));
    public static final Block SKYSTONE_ZONAITE_ORE = registerBlock("skystone_zonaite_ore", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.SPONGE)
    ));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, resourceLocation);
        Block block = function.apply(BlockBehaviour.Properties.of().setId(resourceKey));
        registerBlockItem(resourceLocation, block);
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }
    private static void registerBlockItem(ResourceLocation resourceLocation, Block block) {
        Registry.register(BuiltInRegistries.ITEM, resourceLocation,
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, resourceLocation))));
    }

    public static  void registerModBlocks() {
        SkysSkyIslands.LOGGER.info("Registering Blocks for " + SkysSkyIslands.MOD_ID);
    }
}
