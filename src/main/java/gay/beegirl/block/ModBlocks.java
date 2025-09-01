package gay.beegirl.block;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.particle.ModParticleTypes;
import gay.beegirl.worldgen.ModConfiguredFeatures;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
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

import java.util.Optional;
import java.util.function.Function;

public class ModBlocks {
    public record StoneSetBlocks(Block base, Block button, Block wall, Block slab, Block stairs, Block pressurePlate) {}
    public record WoodSetBlocks(Block log, Block wood, Block strippedLog, Block strippedWood, Block planks, Block button, Block door, Block fence, Block fenceGate, Block standingSign, Block wallSign, Block hangingSign, Block hangingWallSign, Block slab, Block stairs, Block pressurePlate, Block trapdoor) {}

    private static Block registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        Block block = function.apply(BlockBehaviour.Properties.of().setId(resourceKey));
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
    private static StoneSetBlocks registerStoneSetBlocks(String name, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor) {
        Block BASE = registerBlock(name, properties -> new Block(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        Block BUTTON = registerBlock(name+"_button", properties -> new ButtonBlock(blockSetType, 20,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
                        .noCollission()
        ));
        Block WALL = registerBlock(name+"_wall", properties -> new WallBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        Block SLAB = registerBlock(name+"_slab", properties -> new SlabBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        Block STAIRS = registerBlock(name+"_stairs", properties -> new StairBlock(BASE.defaultBlockState(),
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        Block PRESSURE_PLATE = registerBlock(name+"_pressure_plate", properties -> new PressurePlateBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
                        .noCollission()
        ));
        return new StoneSetBlocks(BASE, BUTTON, WALL, SLAB, STAIRS, PRESSURE_PLATE);
    }
    private static WoodSetBlocks registerWoodSetBlocks(Block log, Block wood, Block strippedLog, Block strippedWood, String name, WoodType woodType, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor, MapColor mapColor2) {
        Block PLANKS = registerBlock(name+"_planks", properties -> new Block(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block BUTTON = registerBlock(name+"_button", properties -> new ButtonBlock(blockSetType, 30,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
        ));
        Block DOOR = registerBlock(name+"_door", properties -> new DoorBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noOcclusion()
        ));
        Block FENCE = registerBlock(name+"_fence", properties -> new FenceBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block FENCE_GATE = registerBlock(name+"_fence_gate", properties -> new FenceGateBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block STANDING_SIGN = registerBlock(name+"_sign", properties -> new StandingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
        ));
        Block WALL_SIGN = registerBlockWithoutItem(name+"_wall_sign", properties -> new WallSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
                        .overrideLootTable(STANDING_SIGN.getLootTable())
                        .overrideDescription(STANDING_SIGN.getDescriptionId())
        ));
        Block HANGING_SIGN = registerBlock(name+"_hanging_sign", properties -> new CeilingHangingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
        ));
        Block WALL_HANGING_SIGN = registerBlockWithoutItem(name+"_wall_hanging_sign", properties -> new WallHangingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
                        .overrideLootTable(HANGING_SIGN.getLootTable())
                        .overrideDescription(HANGING_SIGN.getDescriptionId())
        ));
        Block SLAB = registerBlock(name+"_slab", properties -> new SlabBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block STAIRS = registerBlock(name+"_stairs", properties -> new StairBlock(PLANKS.defaultBlockState(),
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block PRESSURE_PLATE = registerBlock(name+"_pressure_plate", properties -> new PressurePlateBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
        ));
        Block TRAPDOOR = registerBlock(name+"_trapdoor", properties -> new TrapDoorBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noOcclusion()
        ));
        StrippableBlockRegistry.register(log, strippedLog);
        StrippableBlockRegistry.register(wood, strippedWood);
        FlammableBlockRegistry.getDefaultInstance().add(log, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(wood, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(strippedLog, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(strippedWood, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(FENCE_GATE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(STAIRS, 5, 20);
        return new WoodSetBlocks(log, wood, strippedLog, strippedWood, PLANKS, BUTTON, DOOR, FENCE, FENCE_GATE, STANDING_SIGN, WALL_SIGN, HANGING_SIGN, WALL_HANGING_SIGN, SLAB, STAIRS, PRESSURE_PLATE, TRAPDOOR);
    }
    private static WoodSetBlocks registerWoodSetBlocks(String name, WoodType woodType, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor, MapColor mapColor2) {
        Block LOG = registerBlock(name+"_log", properties -> new RotatedPillarBlock(
                properties
                        .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor2)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block WOOD = registerBlock(name+"_wood", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(mapColor2)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block STRIPPED_LOG = registerBlock("stripped_"+name+"_log", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        Block STRIPPED_WOOD = registerBlock("stripped_"+name+"_wood", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        return registerWoodSetBlocks(LOG, WOOD, STRIPPED_LOG, STRIPPED_WOOD, name, woodType, blockSetType, noteBlockInstrument, soundType, mapColor, mapColor2);
    }

    public static  void registerBlocks() {
        SkysSkyIslands.LOGGER.info("Registering Blocks for " + SkysSkyIslands.MOD_ID);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GOLDENLEAF_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.SAKURA_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.FRIGID_LEAVES, 30, 60);

        //TODO: Custom Cloudshale SoundType?
    }

    public static final Block CLOUDSHALE_GRASS = registerBlock("cloudshale_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final Block CLOUDSHALE_CHERRY_GRASS = registerBlock("cloudshale_cherry_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final Block POINTED_CLOUDSHALE = registerBlock("pointed_cloudshale", properties -> new PointedCloudshaleBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.POINTED_DRIPSTONE)
                    .randomTicks()
                    .noOcclusion()
                    .forceSolidOn()
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
    ));
    public static final StoneSetBlocks CLOUDSHALE = registerStoneSetBlocks("cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    public static final BlockFamily CLOUDSHALE_FAMILY = new BlockFamily.Builder(CLOUDSHALE.base)
            .button(CLOUDSHALE.button)
            .wall(CLOUDSHALE.wall)
            .slab(CLOUDSHALE.slab)
            .stairs(CLOUDSHALE.stairs)
            .pressurePlate(CLOUDSHALE.pressurePlate)
            .getFamily();
    public static final StoneSetBlocks COBBLED_CLOUDSHALE = registerStoneSetBlocks("cobbled_cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    public static final BlockFamily COBBLED_CLOUDSHALE_FAMILY = new BlockFamily.Builder(COBBLED_CLOUDSHALE.base)
            .button(COBBLED_CLOUDSHALE.button)
            .wall(COBBLED_CLOUDSHALE.wall)
            .slab(COBBLED_CLOUDSHALE.slab)
            .stairs(COBBLED_CLOUDSHALE.stairs)
            .pressurePlate(COBBLED_CLOUDSHALE.pressurePlate)
            .getFamily();
    public static final StoneSetBlocks MOSSY_COBBLED_CLOUDSHALE = registerStoneSetBlocks("mossy_cobbled_cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    public static final BlockFamily MOSSY_COBBLED_CLOUDSHALE_FAMILY = new BlockFamily.Builder(MOSSY_COBBLED_CLOUDSHALE.base)
            .button(MOSSY_COBBLED_CLOUDSHALE.button)
            .wall(MOSSY_COBBLED_CLOUDSHALE.wall)
            .slab(MOSSY_COBBLED_CLOUDSHALE.slab)
            .stairs(MOSSY_COBBLED_CLOUDSHALE.stairs)
            .pressurePlate(MOSSY_COBBLED_CLOUDSHALE.pressurePlate)
            .getFamily();
    public static final StoneSetBlocks CHERRY_COBBLED_CLOUDSHALE = registerStoneSetBlocks("cherry_cobbled_cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    public static final BlockFamily CHERRY_COBBLED_CLOUDSHALE_FAMILY = new BlockFamily.Builder(CHERRY_COBBLED_CLOUDSHALE.base)
            .button(CHERRY_COBBLED_CLOUDSHALE.button)
            .wall(CHERRY_COBBLED_CLOUDSHALE.wall)
            .slab(CHERRY_COBBLED_CLOUDSHALE.slab)
            .stairs(CHERRY_COBBLED_CLOUDSHALE.stairs)
            .pressurePlate(CHERRY_COBBLED_CLOUDSHALE.pressurePlate)
            .getFamily();

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
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.TUFF_BRICKS)
    ));
    public static final Block RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.STONE)
    ));
    public static final Block ALEXANDRITE_BLOCK = registerBlock("alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
    ));

    public static final WoodSetBlocks GOLDENLEAF_PLANKS = registerWoodSetBlocks("goldenleaf", ModWoodTypes.GOLDENLEAF, ModBlockSetTypes.GOLDENLEAF, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_YELLOW, MapColor.COLOR_GREEN); //TODO: tweak MapColors
    public static final BlockFamily GOLDENLEAF_PLANKS_FAMILY = new BlockFamily.Builder(GOLDENLEAF_PLANKS.planks)
            .button(GOLDENLEAF_PLANKS.button)
            .door(GOLDENLEAF_PLANKS.door)
            .fence(GOLDENLEAF_PLANKS.fence)
            .fenceGate(GOLDENLEAF_PLANKS.fenceGate)
            .sign(GOLDENLEAF_PLANKS.standingSign, GOLDENLEAF_PLANKS.wallSign)
            .slab(GOLDENLEAF_PLANKS.slab)
            .stairs(GOLDENLEAF_PLANKS.stairs)
            .pressurePlate(GOLDENLEAF_PLANKS.pressurePlate)
            .trapdoor(GOLDENLEAF_PLANKS.trapdoor)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
    public static final Block GOLDENLEAF_LEAVES = registerBlock("goldenleaf_leaves", properties -> new TintedParticleLeavesBlock(0.1F,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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
    public static final Block GOLDENLEAF_SAPLING = registerBlock("goldenleaf_sapling", properties -> new SaplingBlock(ModTreeGrowers.GOLDENLEAF,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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

    public static final WoodSetBlocks SAKURA_PLANKS = registerWoodSetBlocks("sakura", ModWoodTypes.SAKURA, ModBlockSetTypes.SAKURA, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.TERRACOTTA_WHITE, MapColor.COLOR_GRAY); //TODO: tweak MapColors
    public static final BlockFamily SAKURA_PLANKS_FAMILY = new BlockFamily.Builder(SAKURA_PLANKS.planks)
            .button(SAKURA_PLANKS.button)
            .door(SAKURA_PLANKS.door)
            .fence(SAKURA_PLANKS.fence)
            .fenceGate(SAKURA_PLANKS.fenceGate)
            .sign(SAKURA_PLANKS.standingSign, SAKURA_PLANKS.wallSign)
            .slab(SAKURA_PLANKS.slab)
            .stairs(SAKURA_PLANKS.stairs)
            .pressurePlate(SAKURA_PLANKS.pressurePlate)
            .trapdoor(SAKURA_PLANKS.trapdoor)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
    public static final Block SAKURA_LEAVES = registerBlock("sakura_leaves", properties -> new UntintedParticleLeavesBlock(0.1F, ModParticleTypes.SAKURA_PETALS,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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
    public static final Block SAKURA_SAPLING = registerBlock("sakura_sapling", properties -> new SaplingBlock(ModTreeGrowers.SAKURA,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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

    public static final WoodSetBlocks FRIGID_PLANKS = registerWoodSetBlocks("frigid", ModWoodTypes.FRIGID, ModBlockSetTypes.FRIGID, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_LIGHT_BLUE, MapColor.COLOR_BLUE); //TODO: tweak MapColors
    public static final BlockFamily FRIGID_PLANKS_FAMILY = new BlockFamily.Builder(FRIGID_PLANKS.planks)
            .button(FRIGID_PLANKS.button)
            .door(FRIGID_PLANKS.door)
            .fence(FRIGID_PLANKS.fence)
            .fenceGate(FRIGID_PLANKS.fenceGate)
            .sign(FRIGID_PLANKS.standingSign, FRIGID_PLANKS.wallSign)
            .slab(FRIGID_PLANKS.slab)
            .stairs(FRIGID_PLANKS.stairs)
            .pressurePlate(FRIGID_PLANKS.pressurePlate)
            .trapdoor(FRIGID_PLANKS.trapdoor)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
    public static final Block FRIGID_LEAVES = registerBlock("frigid_leaves", properties -> new TintedParticleLeavesBlock(0.1F,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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
    public static final Block FRIGID_SAPLING = registerBlock("frigid_sapling", properties -> new SaplingBlock(ModTreeGrowers.FRIGID,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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

    private static final Block ARBOREAL_CACTUS_STEM = registerBlock("arboreal_cactus_stem", properties -> new RotatedPillarBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_BLACK : MapColor.COLOR_MAGENTA) //TODO: tweak MapColors
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .randomTicks()
                    .ignitedByLava()
    )); //TODO: Fix this to use ModCactusBlock
    private static final Block ARBOREAL_CACTUS_HYPHAE = registerBlock("arboreal_cactus_hyphae", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    )); //TODO: Fix this to use ModCactusBlock
    private static final Block STRIPPED_ARBOREAL_CACTUS_STEM = registerBlock("stripped_arboreal_cactus_stem", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_BLACK) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    private static final Block STRIPPED_ARBOREAL_CACTUS_HYPHAE = registerBlock("stripped_arboreal_cactus_hyphae", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_BLACK) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    public static final WoodSetBlocks ARBOREAL_CACTUS_PLANKS = registerWoodSetBlocks(ARBOREAL_CACTUS_STEM, ARBOREAL_CACTUS_HYPHAE, STRIPPED_ARBOREAL_CACTUS_STEM, STRIPPED_ARBOREAL_CACTUS_HYPHAE, "arboreal_cactus", ModWoodTypes.ARBOREAL_CACTUS, ModBlockSetTypes.ARBOREAL_CACTUS, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_BLACK, MapColor.COLOR_LIGHT_GREEN); //TODO: tweak MapColors
    public static final BlockFamily ARBOREAL_CACTUS_PLANKS_FAMILY = new BlockFamily.Builder(ARBOREAL_CACTUS_PLANKS.planks)
            .button(ARBOREAL_CACTUS_PLANKS.button)
            .door(ARBOREAL_CACTUS_PLANKS.door)
            .fence(ARBOREAL_CACTUS_PLANKS.fence)
            .fenceGate(ARBOREAL_CACTUS_PLANKS.fenceGate)
            .sign(ARBOREAL_CACTUS_PLANKS.standingSign, ARBOREAL_CACTUS_PLANKS.wallSign)
            .slab(ARBOREAL_CACTUS_PLANKS.slab)
            .stairs(ARBOREAL_CACTUS_PLANKS.stairs)
            .pressurePlate(ARBOREAL_CACTUS_PLANKS.pressurePlate)
            .trapdoor(ARBOREAL_CACTUS_PLANKS.trapdoor)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
    public static final Block ARBOREAL_CACTUS_FRUIT = registerBlock("arboreal_cactus_fruit", properties -> new CactusFruitBlock(ModTreeGrowers.ARBOREAL_CACTUS,
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

    public static class ModBlockSetTypes {
        public static final BlockSetType CLOUDSHALE = BlockSetTypeBuilder.copyOf(BlockSetType.STONE).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "cloudshale"));
        public static final BlockSetType GOLDENLEAF = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "goldenleaf"));
        public static final BlockSetType SAKURA = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sakura"));
        public static final BlockSetType FRIGID = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "frigid"));
        public static final BlockSetType ARBOREAL_CACTUS = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "arboreal_cactus"));
    }
    public static class ModWoodTypes {
        public static final WoodType GOLDENLEAF = WoodTypeBuilder.copyOf(WoodType.OAK).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "goldenleaf"), ModBlockSetTypes.GOLDENLEAF);
        public static final WoodType SAKURA = WoodTypeBuilder.copyOf(WoodType.CHERRY).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sakura"), ModBlockSetTypes.SAKURA);
        public static final WoodType FRIGID = WoodTypeBuilder.copyOf(WoodType.OAK).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "frigid"), ModBlockSetTypes.FRIGID);
        public static final WoodType ARBOREAL_CACTUS = WoodTypeBuilder.copyOf(WoodType.CRIMSON).register(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "arboreal_cactus"), ModBlockSetTypes.ARBOREAL_CACTUS);
    }
    public static class ModTreeGrowers {
        public static final TreeGrower GOLDENLEAF = new TreeGrower("goldenleaf", Optional.empty(), Optional.of(ModConfiguredFeatures.GOLDENLEAF), Optional.of(ModConfiguredFeatures.GOLDENLEAF_BEES_005));
        public static final TreeGrower SAKURA = new TreeGrower("sakura", Optional.empty(), Optional.of(ModConfiguredFeatures.SAKURA), Optional.of(ModConfiguredFeatures.SAKURA_BEES_005));
        public static final TreeGrower FRIGID = new TreeGrower("frigid", Optional.empty(), Optional.of(ModConfiguredFeatures.FRIGID), Optional.of(ModConfiguredFeatures.FRIGID_BEES_005));
        public static final TreeGrower ARBOREAL_CACTUS = new TreeGrower("arboreal_cactus", Optional.empty(), Optional.of(ModConfiguredFeatures.ARBOREAL_CACTUS), Optional.empty());

    }
}
