package gay.beegirl.skyislands.world.level.block;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.level.block.grower.ModTreeGrower;
import gay.beegirl.skyislands.world.level.block.state.properties.ModBlockSetType;
import gay.beegirl.skyislands.world.level.block.state.properties.ModWoodType;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    //TODO: tweak MapColors for EVERYTHING
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SkysSkyIslands.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SkysSkyIslands.MOD_ID);

    public record StoneBlockSet(
            DeferredBlock<Block> base,
            DeferredBlock<Block> wall,
            DeferredBlock<Block> slab,
            DeferredBlock<Block> stairs) {}
    public record LogBlockSet(
            DeferredBlock<Block> log,
            DeferredBlock<Block> wood,
            DeferredBlock<Block> strippedLog,
            DeferredBlock<Block> strippedWood) {}
    public record CactusBlockSet(
            DeferredBlock<Block> cactus,
            DeferredBlock<Block> despinedCactus) {}
    public record WoodBlockSet(
            DeferredBlock<Block> base,
            DeferredBlock<Block> button,
            DeferredBlock<Block> door,
            DeferredBlock<Block> fence,
            DeferredBlock<Block> fenceGate,
            DeferredBlock<Block> standingSign,
            DeferredBlock<Block> wallSign,
            DeferredBlock<Block> hangingSign,
            DeferredBlock<Block> hangingWallSign,
            DeferredBlock<Block> slab,
            DeferredBlock<Block> stairs,
            DeferredBlock<Block> pressurePlate,
            DeferredBlock<Block> trapdoor) {}

    private static DeferredBlock<Block> registerBlock(String name, Function<Properties, Block> function) {
        return BLOCKS.registerBlock(name, function);
    }
    private static DeferredBlock<Block> registerBlockWithItem(String name, Function<Properties, Block> function) {
        DeferredBlock<Block> BLOCK = registerBlock(name, function);
        ITEMS.registerSimpleBlockItem(name, BLOCK);
        return BLOCK;
    }

    private static StoneBlockSet registerStoneSetBlocks(String name, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor) {
        DeferredBlock<Block> BASE = registerBlockWithItem(name, properties -> new Block(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> WALL = registerBlockWithItem(name+"_wall", properties -> new WallBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> SLAB = registerBlockWithItem(name+"_slab", properties -> new SlabBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> STAIRS = registerBlockWithItem(name+"_stairs", properties -> new StairBlock(BASE.get().defaultBlockState(),
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        return new StoneBlockSet(BASE, WALL, SLAB, STAIRS);
    }
    private static LogBlockSet registerLogSetBlocks(String name, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor barkMapColor, MapColor woodMapColor) {
        DeferredBlock<Block> LOG = registerBlockWithItem(name+"_log", properties -> new RotatedPillarBlock(
                properties
                        .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? woodMapColor : barkMapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> WOOD = registerBlockWithItem(name+"_wood", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(barkMapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STRIPPED_LOG = registerBlockWithItem("stripped_"+name+"_log", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(woodMapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STRIPPED_WOOD = registerBlockWithItem("stripped_"+name+"_wood", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(woodMapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        return new LogBlockSet(LOG, WOOD, STRIPPED_LOG, STRIPPED_WOOD);
    }
    private static CactusBlockSet registerCactusSetBlocks(String name, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor) {
        DeferredBlock<Block> ARBOREAL_CACTUS = registerBlockWithItem(name, properties -> new IslandsCactusBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .randomTicks()
                        .ignitedByLava()
        ));
        DeferredBlock<Block> DESPINED_ARBOREAL_CACTUS = registerBlockWithItem("despined_"+name, properties -> new IslandsDespinedCactusBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        return new CactusBlockSet(ARBOREAL_CACTUS, DESPINED_ARBOREAL_CACTUS);
    }
    private static WoodBlockSet registerWoodSetBlocks(String name, WoodType woodType, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor) {
        DeferredBlock<Block> BASE = registerBlockWithItem(name+"_planks", properties -> new Block(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> BUTTON = registerBlockWithItem(name+"_button", properties -> new ButtonBlock(blockSetType, 30,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
        ));
        DeferredBlock<Block> DOOR = registerBlockWithItem(name+"_door", properties -> new DoorBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noOcclusion()
        ));
        DeferredBlock<Block> FENCE = registerBlockWithItem(name+"_fence", properties -> new FenceBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> FENCE_GATE = registerBlockWithItem(name+"_fence_gate", properties -> new FenceGateBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STANDING_SIGN = registerBlockWithItem(name+"_sign", properties -> new StandingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
        ));
        DeferredBlock<Block> WALL_SIGN = registerBlock(name+"_wall_sign", properties -> new WallSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
                        .dropsLike(STANDING_SIGN.get())
        ));
        DeferredBlock<Block> HANGING_SIGN = registerBlockWithItem(name+"_hanging_sign", properties -> new CeilingHangingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
        ));
        DeferredBlock<Block> WALL_HANGING_SIGN = registerBlock(name+"_wall_hanging_sign", properties -> new WallHangingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
                        .dropsLike(HANGING_SIGN.get())
        ));
        DeferredBlock<Block> SLAB = registerBlockWithItem(name+"_slab", properties -> new SlabBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STAIRS = registerBlockWithItem(name+"_stairs", properties -> new StairBlock(BASE.get().defaultBlockState(),
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> PRESSURE_PLATE = registerBlockWithItem(name+"_pressure_plate", properties -> new PressurePlateBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
        ));
        DeferredBlock<Block> TRAPDOOR = registerBlockWithItem(name+"_trapdoor", properties -> new TrapDoorBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noOcclusion()
        ));
        return new WoodBlockSet(BASE, BUTTON, DOOR, FENCE, FENCE_GATE, STANDING_SIGN, WALL_SIGN, HANGING_SIGN, WALL_HANGING_SIGN, SLAB, STAIRS, PRESSURE_PLATE, TRAPDOOR);
    }

    public static final DeferredBlock<Block> CLOUDSHALE_GRASS = registerBlockWithItem("cloudshale_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final DeferredBlock<Block> CLOUDSHALE_CHERRY_GRASS = registerBlockWithItem("cloudshale_cherry_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final DeferredBlock<Block> POINTED_CLOUDSHALE = registerBlockWithItem("pointed_cloudshale", properties -> new PointedCloudshaleBlock(
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
    public static final DeferredBlock<Block> CLOUDSHALE = registerBlockWithItem("cloudshale", properties -> new RotatedPillarBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.TUFF_BRICKS)
    ));

    public static final StoneBlockSet COBBLED_CLOUDSHALE = registerStoneSetBlocks("cobbled_cloudshale", ModBlockSetType.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA);
    public static final StoneBlockSet MOSSY_COBBLED_CLOUDSHALE = registerStoneSetBlocks("mossy_cobbled_cloudshale", ModBlockSetType.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA);
    public static final StoneBlockSet CHERRY_COBBLED_CLOUDSHALE = registerStoneSetBlocks("cherry_cobbled_cloudshale", ModBlockSetType.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA);

    public static final DeferredBlock<Block> STONE_ALEXANDRITE_ORE = registerBlockWithItem("stone_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlockWithItem("deepslate_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)
    ));
    public static final DeferredBlock<Block> CLOUDSHALE_ALEXANDRITE_ORE = registerBlockWithItem("cloudshale_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.TUFF_BRICKS)
    ));
    public static final DeferredBlock<Block> RAW_ALEXANDRITE_BLOCK = registerBlockWithItem("raw_alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> ALEXANDRITE_BLOCK = registerBlockWithItem("alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
    ));

    public static final LogBlockSet GOLDENLEAF_LOGS = registerLogSetBlocks("goldenleaf", NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_YELLOW, MapColor.COLOR_GREEN);
    public static final WoodBlockSet GOLDENLEAF_PLANKS = registerWoodSetBlocks("goldenleaf", ModWoodType.GOLDENLEAF, ModBlockSetType.GOLDENLEAF, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_YELLOW);
    public static final DeferredBlock<Block> GOLDENLEAF_LEAVES = registerBlockWithItem("goldenleaf_leaves", properties -> new LeavesBlock(
            properties
                    .mapColor(MapColor.PLANT)
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
    public static final DeferredBlock<Block> GOLDENLEAF_SAPLING = registerBlockWithItem("goldenleaf_sapling", properties -> new SaplingBlock(ModTreeGrower.GOLDENLEAF,
            properties
                    .mapColor(MapColor.PLANT)
                    .instrument(NoteBlockInstrument.HARP)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> POTTED_GOLDENLEAF_SAPLING = registerBlock("potted_goldenleaf_sapling", properties -> new FlowerPotBlock(GOLDENLEAF_SAPLING.get(),
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static final LogBlockSet SAKURA_LOGS = registerLogSetBlocks("sakura", NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.TERRACOTTA_WHITE, MapColor.COLOR_GRAY);
    public static final WoodBlockSet SAKURA_PLANKS = registerWoodSetBlocks("sakura", ModWoodType.SAKURA, ModBlockSetType.SAKURA, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.TERRACOTTA_WHITE);
    public static final DeferredBlock<Block> SAKURA_LEAVES = registerBlockWithItem("sakura_leaves", properties -> new SakuraLeavesBlock(
            properties
                    .mapColor(MapColor.PLANT)
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
    public static final DeferredBlock<Block> SAKURA_SAPLING = registerBlockWithItem("sakura_sapling", properties -> new SaplingBlock(ModTreeGrower.SAKURA,
            properties
                    .mapColor(MapColor.PLANT)
                    .instrument(NoteBlockInstrument.HARP)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> POTTED_SAKURA_SAPLING = registerBlock("potted_sakura_sapling", properties -> new FlowerPotBlock(SAKURA_SAPLING.get(),
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static final LogBlockSet FRIGID_LOGS = registerLogSetBlocks("frigid", NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_LIGHT_BLUE, MapColor.COLOR_BLUE);
    public static final WoodBlockSet FRIGID_PLANKS = registerWoodSetBlocks("frigid", ModWoodType.FRIGID, ModBlockSetType.FRIGID, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_LIGHT_BLUE);
    public static final DeferredBlock<Block> FRIGID_LEAVES = registerBlockWithItem("frigid_leaves", properties -> new LeavesBlock(
            properties
                    .mapColor(MapColor.PLANT)
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
    public static final DeferredBlock<Block> FRIGID_SAPLING = registerBlockWithItem("frigid_sapling", properties -> new SaplingBlock(ModTreeGrower.FRIGID,
            properties
                    .mapColor(MapColor.PLANT)
                    .instrument(NoteBlockInstrument.HARP)
                    .sound(SoundType.GRASS)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> POTTED_FRIGID_SAPLING = registerBlock("potted_frigid_sapling", properties -> new FlowerPotBlock(FRIGID_SAPLING.get(),
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static final CactusBlockSet ARBOREAL_CACTUSES = registerCactusSetBlocks("arboreal_cactus", NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_MAGENTA);
    public static final WoodBlockSet ARBOREAL_CACTUS_PLANKS = registerWoodSetBlocks("arboreal_cactus", ModWoodType.ARBOREAL_CACTUS, ModBlockSetType.ARBOREAL_CACTUS, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_LIGHT_BLUE);
    public static final DeferredBlock<Block> ARBOREAL_CACTUS_FRUIT = registerBlock("arboreal_cactus_fruit", properties -> new IslandsCactusFruitBlock(
            properties
                    .mapColor(MapColor.CRIMSON_STEM)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(0.2F, 3.0F)
                    .sound(SoundType.WOOL)
                    .randomTicks()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> ARBOREAL_CACTUS_PLANT = registerBlockWithItem("arboreal_cactus_plant", properties -> new IslandsCactusPlantBlock(ModTreeGrower.ARBOREAL_CACTUS,
            properties
                    .mapColor(MapColor.CRIMSON_STEM)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(0.2F, 3.0F)
                    .sound(SoundType.WOOL)
                    .randomTicks()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> POTTED_ARBOREAL_CACTUS = registerBlock("potted_arboreal_cactus", properties -> new FlowerPotBlock(ARBOREAL_CACTUS_FRUIT.get(),
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static void registerBlocks(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Blocks for " + SkysSkyIslands.MOD_ID);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
