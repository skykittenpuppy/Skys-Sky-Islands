package gay.beegirl.block;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlock {
    private static Block registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, resourceLocation);
        Block block = function.apply(BlockBehaviour.Properties.of().setId(resourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }
    private static Block registerBlockWithUniqueItem(String name, String itemName, Function<BlockBehaviour.Properties, Block> function) {
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        Block block = function.apply(BlockBehaviour.Properties.of().setId(resourceKey));
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, itemName),
                new BlockItem(block, new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, itemName)))));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, resourceLocation);
        Block block = function.apply(BlockBehaviour.Properties.of().setId(resourceKey));
        Registry.register(BuiltInRegistries.ITEM, resourceLocation,
                new BlockItem(block, new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, resourceLocation))
                        .useBlockDescriptionPrefix()));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    public static  void registerModBlocks() {
        SkysSkyIslands.LOGGER.info("Registering Blocks for " + SkysSkyIslands.MOD_ID);
    }

    public static final Block CLOUDSHALE_GRASS = registerBlock("cloudshale_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final Block CLOUDSHALE_CHERRY_GRASS = registerBlock("cloudshale_cherry_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final Block CLOUDSHALE = registerBlock("cloudshale", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.0F, 3.0F)
                    .sound(SoundType.STONE) //TODO
    ));
    public static final Block COBBLED_CLOUDSHALE = registerBlock("cobbled_cloudshale", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.STONE) //TODO
    ));
    public static final Block MOSSY_COBBLED_CLOUDSHALE = registerBlock("mossy_cobbled_cloudshale", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.STONE) //TODO
    ));
    public static final Block CHERRY_COBBLED_CLOUDSHALE = registerBlock("cherry_cobbled_cloudshale", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.STONE) //TODO
    ));
    public static final Block STONE_ALEXANDRITE_ORE = registerBlock("stone_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE)
    ));
    public static final Block DEEPSLATE_ALEXANDRITE_ORE = registerBlock("deepslate_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)
    ));
    public static final Block CLOUDSHALE_ALEXANDRITE_ORE = registerBlock("cloudshale_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.STONE) //TODO
    ));
    public static final Block RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.STONE)
    ));
    public static final Block ALEXANDRITE_BLOCK = registerBlock("alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
    ));

    public static final Block GOLDENLEAF_LOG = registerBlock("goldenleaf_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_YELLOW : MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_LOG = registerBlock("sakura_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.SNOW : MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_LOG = registerBlock("frigid_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_LIGHT_BLUE : MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));

    public static final Block ARBOREAL_CACTUS_STEM = registerBlock("arboreal_cactus_stem", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_BLACK : MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .randomTicks()
                    .ignitedByLava()
    )); //TODO: Fix this to use ModCactusBlock
    public static final Block ARBOREAL_CACTUS_FRUIT = registerBlock("arboreal_cactus_fruit", properties -> new ModCactusFruitBlock(TreeGrower.OAK,
            properties
                    .mapColor(MapColor.CRIMSON_STEM)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(0.2F, 3.0F)
                    .sound(SoundType.WOOL)
                    .randomTicks()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block POTTED_ARBOREAL_CACTUS = registerBlock("potted_arboreal_cactus", properties -> new FlowerPotBlock(ARBOREAL_CACTUS_FRUIT,
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));
}
