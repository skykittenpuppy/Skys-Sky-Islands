package gay.beegirl.block;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
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
        item: {
            Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, itemName),
                    new BlockItem(block, new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, itemName)))));
        }
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, resourceLocation);
        Block block = function.apply(BlockBehaviour.Properties.of().setId(resourceKey));
        item: {
            Registry.register(BuiltInRegistries.ITEM, resourceLocation,
                    new BlockItem(block, new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, resourceLocation))
                            .useBlockDescriptionPrefix()));
        }
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
    public static final Block GOLDENLEAF_WOOD = registerBlock("goldenleaf_wood", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_GOLDENLEAF_LOG = registerBlock("stripped_goldenleaf_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_YELLOW) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_GOLDENLEAF_WOOD = registerBlock("stripped_goldenleaf_wood", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_YELLOW) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_PLANKS = registerBlock("goldenleaf_planks", properties -> new Block(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_STAIRS = registerBlock("goldenleaf_stairs", properties -> new StairBlock(GOLDENLEAF_PLANKS.defaultBlockState(),
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_SLAB = registerBlock("goldenleaf_slab", properties -> new SlabBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_BUTTON = registerBlock("goldenleaf_button", properties -> new ButtonBlock(ModBlockSetType.GOLDENLEAF, 30,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noCollission()
    ));
    public static final Block GOLDENLEAF_PRESSURE_PLATE = registerBlock("goldenleaf_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.GOLDENLEAF,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_FENCE = registerBlock("goldenleaf_fence", properties -> new FenceBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_FENCE_GATE = registerBlock("goldenleaf_fence_gate", properties -> new FenceGateBlock(ModWoodType.GOLDENLEAF,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block GOLDENLEAF_DOOR = registerBlock("goldenleaf_door", properties -> new DoorBlock(ModBlockSetType.GOLDENLEAF,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block GOLDENLEAF_TRAPDOOR = registerBlock("goldenleaf_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.GOLDENLEAF,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block GOLDENLEAF_LEAVES = registerBlock("goldenleaf_leaves", properties -> new TintedParticleLeavesBlock(0.1F,
            properties
                    .mapColor(MapColor.PLANT) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .randomTicks()
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
    ));
    public static final Block GOLDENLEAF_SAPLING = registerBlock("goldenleaf_sapling", properties -> new SaplingBlock(TreeGrower.OAK,
            properties
                    .mapColor(MapColor.PLANT) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block POTTED_GOLDENLEAF_SAPLING = registerBlock("potted_goldenleaf_sapling", properties -> new FlowerPotBlock(GOLDENLEAF_SAPLING,
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static final Block SAKURA_LOG = registerBlock("sakura_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_YELLOW : MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_WOOD = registerBlock("sakura_wood", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_SAKURA_LOG = registerBlock("stripped_sakura_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_YELLOW) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_SAKURA_WOOD = registerBlock("stripped_sakura_wood", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_YELLOW) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_PLANKS = registerBlock("sakura_planks", properties -> new Block(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_STAIRS = registerBlock("sakura_stairs", properties -> new StairBlock(SAKURA_PLANKS.defaultBlockState(),
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_SLAB = registerBlock("sakura_slab", properties -> new SlabBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_BUTTON = registerBlock("sakura_button", properties -> new ButtonBlock(ModBlockSetType.SAKURA, 30,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noCollission()
    ));
    public static final Block SAKURA_PRESSURE_PLATE = registerBlock("sakura_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.SAKURA,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_FENCE = registerBlock("sakura_fence", properties -> new FenceBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_FENCE_GATE = registerBlock("sakura_fence_gate", properties -> new FenceGateBlock(ModWoodType.SAKURA,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block SAKURA_DOOR = registerBlock("sakura_door", properties -> new DoorBlock(ModBlockSetType.SAKURA,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block SAKURA_TRAPDOOR = registerBlock("sakura_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.SAKURA,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block SAKURA_LEAVES = registerBlock("sakura_leaves", properties -> new UntintedParticleLeavesBlock(0.1F, ParticleTypes.CHERRY_LEAVES,
            properties
                    .mapColor(MapColor.PLANT) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .randomTicks()
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
    ));
    public static final Block SAKURA_SAPLING = registerBlock("sakura_sapling", properties -> new SaplingBlock(TreeGrower.OAK,
            properties
                    .mapColor(MapColor.PLANT) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block POTTED_SAKURA_SAPLING = registerBlock("potted_sakura_sapling", properties -> new FlowerPotBlock(SAKURA_SAPLING,
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static final Block FRIGID_LOG = registerBlock("frigid_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_YELLOW : MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_WOOD = registerBlock("frigid_wood", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_FRIGID_LOG = registerBlock("stripped_frigid_log", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_YELLOW) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_FRIGID_WOOD = registerBlock("stripped_frigid_wood", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_YELLOW) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_PLANKS = registerBlock("frigid_planks", properties -> new Block(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_STAIRS = registerBlock("frigid_stairs", properties -> new StairBlock(FRIGID_PLANKS.defaultBlockState(),
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_SLAB = registerBlock("frigid_slab", properties -> new SlabBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_BUTTON = registerBlock("frigid_button", properties -> new ButtonBlock(ModBlockSetType.FRIGID, 30,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noCollission()
    ));
    public static final Block FRIGID_PRESSURE_PLATE = registerBlock("frigid_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.FRIGID,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_FENCE = registerBlock("frigid_fence", properties -> new FenceBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_FENCE_GATE = registerBlock("frigid_fence_gate", properties -> new FenceGateBlock(ModWoodType.FRIGID,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block FRIGID_DOOR = registerBlock("frigid_door", properties -> new DoorBlock(ModBlockSetType.FRIGID,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block FRIGID_TRAPDOOR = registerBlock("frigid_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.FRIGID,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block FRIGID_LEAVES = registerBlock("frigid_leaves", properties -> new TintedParticleLeavesBlock(0.1F,
            properties
                    .mapColor(MapColor.PLANT) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .randomTicks()
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
    ));
    public static final Block FRIGID_SAPLING = registerBlock("frigid_sapling", properties -> new SaplingBlock(TreeGrower.OAK,
            properties
                    .mapColor(MapColor.PLANT) //TODO
                    .instrument(NoteBlockInstrument.HARP)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block POTTED_FRIGID_SAPLING = registerBlock("potted_frigid_sapling", properties -> new FlowerPotBlock(FRIGID_SAPLING,
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
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
    public static final Block ARBOREAL_CACTUS_HYPHAE = registerBlock("arboreal_cactus_hyphae", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    )); //TODO: Fix this to use ModCactusBlock
    public static final Block STRIPPED_ARBOREAL_CACTUS_STEM = registerBlock("stripped_arboreal_cactus_stem", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_BLACK) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block STRIPPED_ARBOREAL_CACTUS_HYPHAE = registerBlock("stripped_arboreal_cactus_hyphae", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_BLACK) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_PLANKS = registerBlock("arboreal_cactus_planks", properties -> new Block(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_STAIRS = registerBlock("arboreal_cactus_stairs", properties -> new StairBlock(ARBOREAL_CACTUS_PLANKS.defaultBlockState(),
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_SLAB = registerBlock("arboreal_cactus_slab", properties -> new SlabBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_BUTTON = registerBlock("arboreal_cactus_button", properties -> new ButtonBlock(ModBlockSetType.ARBOREAL_CACTUS, 30,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noCollission()
    ));
    public static final Block ARBOREAL_CACTUS_PRESSURE_PLATE = registerBlock("arboreal_cactus_pressure_plate", properties -> new PressurePlateBlock(ModBlockSetType.ARBOREAL_CACTUS,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_FENCE = registerBlock("arboreal_cactus_fence", properties -> new FenceBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_FENCE_GATE = registerBlock("arboreal_cactus_fence_gate", properties -> new FenceGateBlock(ModWoodType.ARBOREAL_CACTUS,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final Block ARBOREAL_CACTUS_DOOR = registerBlock("arboreal_cactus_door", properties -> new DoorBlock(ModBlockSetType.ARBOREAL_CACTUS,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
    public static final Block ARBOREAL_CACTUS_TRAPDOOR = registerBlock("arboreal_cactus_trapdoor", properties -> new TrapDoorBlock(ModBlockSetType.ARBOREAL_CACTUS,
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
    ));
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
