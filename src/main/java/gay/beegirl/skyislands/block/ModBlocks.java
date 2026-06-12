package gay.beegirl.skyislands.block;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SkysSkyIslands.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SkysSkyIslands.MOD_ID);

    public record StoneSetBlocks(DeferredBlock<Block> base, DeferredBlock<Block> button, DeferredBlock<Block> wall, DeferredBlock<Block> slab, DeferredBlock<Block> stairs, DeferredBlock<Block> pressurePlate) {}
    public record WoodSetBlocks(DeferredBlock<Block> log, DeferredBlock<Block> wood, DeferredBlock<Block> strippedLog, DeferredBlock<Block> strippedWood, DeferredBlock<Block> planks, DeferredBlock<Block> button, DeferredBlock<Block> door, DeferredBlock<Block> fence, DeferredBlock<Block> fenceGate, DeferredBlock<Block> standingSign, DeferredBlock<Block> wallSign, DeferredBlock<Block> hangingSign, DeferredBlock<Block> hangingWallSign, DeferredBlock<Block> slab, DeferredBlock<Block> stairs, DeferredBlock<Block> pressurePlate, DeferredBlock<Block> trapdoor) {}

    private static DeferredBlock<Block> registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        return BLOCKS.registerBlock(name, function);
    }
    private static DeferredBlock<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        DeferredBlock<Block> BLOCK = BLOCKS.registerBlock(name, function);
        ITEMS.registerSimpleBlockItem(name, BLOCK);
        return BLOCK;
    }
    private static StoneSetBlocks registerStoneSetBlocks(String name, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor) {
        DeferredBlock<Block> BASE = registerBlock(name, properties -> new Block(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> BUTTON = registerBlock(name+"_button", properties -> new ButtonBlock(blockSetType, 20,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
                        .noCollission()
        ));
        DeferredBlock<Block> WALL = registerBlock(name+"_wall", properties -> new WallBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> SLAB = registerBlock(name+"_slab", properties -> new SlabBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> STAIRS = registerBlock(name+"_stairs", properties -> new StairBlock(BASE.get().defaultBlockState(),
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
        ));
        DeferredBlock<Block> PRESSURE_PLATE = registerBlock(name+"_pressure_plate", properties -> new PressurePlateBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.5F, 3.0F)
                        .sound(soundType)
                        .noCollission()
        ));
        return new StoneSetBlocks(BASE, BUTTON, WALL, SLAB, STAIRS, PRESSURE_PLATE);
    }
    private static WoodSetBlocks registerWoodSetBlocks(DeferredBlock<Block> log, DeferredBlock<Block> wood, DeferredBlock<Block> strippedLog, DeferredBlock<Block> strippedWood, String name, WoodType woodType, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor, MapColor mapColor2) {
        DeferredBlock<Block> PLANKS = registerBlock(name+"_planks", properties -> new Block(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> BUTTON = registerBlock(name+"_button", properties -> new ButtonBlock(blockSetType, 30,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
        ));
        DeferredBlock<Block> DOOR = registerBlock(name+"_door", properties -> new DoorBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noOcclusion()
        ));
        DeferredBlock<Block> FENCE = registerBlock(name+"_fence", properties -> new FenceBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> FENCE_GATE = registerBlock(name+"_fence_gate", properties -> new FenceGateBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STANDING_SIGN = registerBlock(name+"_sign", properties -> new StandingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
        ));
        DeferredBlock<Block> WALL_SIGN = registerBlockWithoutItem(name+"_wall_sign", properties -> new WallSignBlock(woodType,
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
        DeferredBlock<Block> HANGING_SIGN = registerBlock(name+"_hanging_sign", properties -> new CeilingHangingSignBlock(woodType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(1.0f)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
                        .forceSolidOn()
        ));
        DeferredBlock<Block> WALL_HANGING_SIGN = registerBlockWithoutItem(name+"_wall_hanging_sign", properties -> new WallHangingSignBlock(woodType,
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
        DeferredBlock<Block> SLAB = registerBlock(name+"_slab", properties -> new SlabBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STAIRS = registerBlock(name+"_stairs", properties -> new StairBlock(PLANKS.get().defaultBlockState(),
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> PRESSURE_PLATE = registerBlock(name+"_pressure_plate", properties -> new PressurePlateBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noCollission()
        ));
        DeferredBlock<Block> TRAPDOOR = registerBlock(name+"_trapdoor", properties -> new TrapDoorBlock(blockSetType,
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F, 3.0F)
                        .sound(soundType)
                        .ignitedByLava()
                        .noOcclusion()
        ));
        //StrippableBlockRegistry.register(log, strippedLog);
        //StrippableBlockRegistry.register(wood, strippedWood);
        //FlammableBlockRegistry.getDefaultInstance().add(log, 5, 5);
        //FlammableBlockRegistry.getDefaultInstance().add(wood, 5, 5);
        //FlammableBlockRegistry.getDefaultInstance().add(strippedLog, 5, 5);
        //FlammableBlockRegistry.getDefaultInstance().add(strippedWood, 5, 5);
        //FlammableBlockRegistry.getDefaultInstance().add(PLANKS, 5, 20);
        //FlammableBlockRegistry.getDefaultInstance().add(FENCE, 5, 20);
        //FlammableBlockRegistry.getDefaultInstance().add(FENCE_GATE, 5, 20);
        //FlammableBlockRegistry.getDefaultInstance().add(SLAB, 5, 20);
        //FlammableBlockRegistry.getDefaultInstance().add(STAIRS, 5, 20);
        return new WoodSetBlocks(log, wood, strippedLog, strippedWood, PLANKS, BUTTON, DOOR, FENCE, FENCE_GATE, STANDING_SIGN, WALL_SIGN, HANGING_SIGN, WALL_HANGING_SIGN, SLAB, STAIRS, PRESSURE_PLATE, TRAPDOOR);
    }
    private static WoodSetBlocks registerWoodSetBlocks(String name, WoodType woodType, BlockSetType blockSetType, NoteBlockInstrument noteBlockInstrument, SoundType soundType, MapColor mapColor, MapColor mapColor2) {
        DeferredBlock<Block> LOG = registerBlock(name+"_log", properties -> new RotatedPillarBlock(
                properties
                        .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor2)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> WOOD = registerBlock(name+"_wood", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(mapColor2)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STRIPPED_LOG = registerBlock("stripped_"+name+"_log", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        DeferredBlock<Block> STRIPPED_WOOD = registerBlock("stripped_"+name+"_wood", properties -> new RotatedPillarBlock(
                properties
                        .mapColor(mapColor)
                        .instrument(noteBlockInstrument)
                        .strength(2.0F)
                        .sound(soundType)
                        .ignitedByLava()
        ));
        return registerWoodSetBlocks(LOG, WOOD, STRIPPED_LOG, STRIPPED_WOOD, name, woodType, blockSetType, noteBlockInstrument, soundType, mapColor, mapColor2);
    }

    public static final DeferredBlock<Block> CLOUDSHALE_GRASS = registerBlock("cloudshale_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final DeferredBlock<Block> CLOUDSHALE_CHERRY_GRASS = registerBlock("cloudshale_cherry_grass", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(1.5F, 3.0F)
                    .sound(SoundType.GRASS)
    ));
    public static final DeferredBlock<Block> POINTED_CLOUDSHALE = registerBlock("pointed_cloudshale", properties -> new PointedCloudshaleBlock(
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
    //public static final BlockFamily CLOUDSHALE_FAMILY = new BlockFamily.Builder(CLOUDSHALE.base.get())
    //        .button(CLOUDSHALE.button.get())
    //        .wall(CLOUDSHALE.wall.get())
    //        .slab(CLOUDSHALE.slab.get())
    //        .stairs(CLOUDSHALE.stairs.get())
    //        .pressurePlate(CLOUDSHALE.pressurePlate.get())
    //        .getFamily();
    public static final StoneSetBlocks COBBLED_CLOUDSHALE = registerStoneSetBlocks("cobbled_cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    //public static final BlockFamily COBBLED_CLOUDSHALE_FAMILY = new BlockFamily.Builder(COBBLED_CLOUDSHALE.base.get())
    //        .button(COBBLED_CLOUDSHALE.button.get())
    //        .wall(COBBLED_CLOUDSHALE.wall.get())
    //        .slab(COBBLED_CLOUDSHALE.slab.get())
    //        .stairs(COBBLED_CLOUDSHALE.stairs.get())
    //        .pressurePlate(COBBLED_CLOUDSHALE.pressurePlate.get())
    //        .getFamily();
    public static final StoneSetBlocks MOSSY_COBBLED_CLOUDSHALE = registerStoneSetBlocks("mossy_cobbled_cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    //public static final BlockFamily MOSSY_COBBLED_CLOUDSHALE_FAMILY = new BlockFamily.Builder(MOSSY_COBBLED_CLOUDSHALE.base.get())
    //        .button(MOSSY_COBBLED_CLOUDSHALE.button.get())
    //        .wall(MOSSY_COBBLED_CLOUDSHALE.wall.get())
    //        .slab(MOSSY_COBBLED_CLOUDSHALE.slab.get())
    //        .stairs(MOSSY_COBBLED_CLOUDSHALE.stairs.get())
    //        .pressurePlate(MOSSY_COBBLED_CLOUDSHALE.pressurePlate.get())
    //        .getFamily();
    public static final StoneSetBlocks CHERRY_COBBLED_CLOUDSHALE = registerStoneSetBlocks("cherry_cobbled_cloudshale", ModBlockSetTypes.CLOUDSHALE, NoteBlockInstrument.BASEDRUM, SoundType.TUFF_BRICKS, MapColor.COLOR_MAGENTA); //TODO: tweak MapColors
    //public static final BlockFamily CHERRY_COBBLED_CLOUDSHALE_FAMILY = new BlockFamily.Builder(CHERRY_COBBLED_CLOUDSHALE.base.get())
    //        .button(CHERRY_COBBLED_CLOUDSHALE.button.get())
    //        .wall(CHERRY_COBBLED_CLOUDSHALE.wall.get())
    //        .slab(CHERRY_COBBLED_CLOUDSHALE.slab.get())
    //        .stairs(CHERRY_COBBLED_CLOUDSHALE.stairs.get())
    //        .pressurePlate(CHERRY_COBBLED_CLOUDSHALE.pressurePlate.get())
    //        .getFamily();

    public static final DeferredBlock<Block> STONE_ALEXANDRITE_ORE = registerBlock("stone_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> DEEPSLATE_ALEXANDRITE_ORE = registerBlock("deepslate_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)
    ));
    public static final DeferredBlock<Block> CLOUDSHALE_ALEXANDRITE_ORE = registerBlock("cloudshale_alexandrite_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7),
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.TUFF_BRICKS)
    ));
    public static final DeferredBlock<Block> RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block", properties -> new Block(
            properties
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.HARP)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
    ));

    public static final WoodSetBlocks GOLDENLEAF_PLANKS = registerWoodSetBlocks("goldenleaf", ModWoodTypes.GOLDENLEAF, ModBlockSetTypes.GOLDENLEAF, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_YELLOW, MapColor.COLOR_GREEN); //TODO: tweak MapColors
    //public static final BlockFamily GOLDENLEAF_PLANKS_FAMILY = new BlockFamily.Builder(GOLDENLEAF_PLANKS.planks.get())
    //        .button(GOLDENLEAF_PLANKS.button.get())
    //        .door(GOLDENLEAF_PLANKS.door.get())
    //        .fence(GOLDENLEAF_PLANKS.fence.get())
    //        .fenceGate(GOLDENLEAF_PLANKS.fenceGate.get())
    //        .sign(GOLDENLEAF_PLANKS.standingSign.get(), GOLDENLEAF_PLANKS.wallSign.get())
    //        .slab(GOLDENLEAF_PLANKS.slab.get())
    //        .stairs(GOLDENLEAF_PLANKS.stairs.get())
    //        .pressurePlate(GOLDENLEAF_PLANKS.pressurePlate.get())
    //        .trapdoor(GOLDENLEAF_PLANKS.trapdoor.get())
    //        .recipeGroupPrefix("wooden")
    //        .recipeUnlockedBy("has_planks")
    //        .getFamily();
    public static final DeferredBlock<Block> GOLDENLEAF_LEAVES = registerBlock("goldenleaf_leaves", properties -> new LeavesBlock(
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
    public static final DeferredBlock<Block> GOLDENLEAF_SAPLING = registerBlock("goldenleaf_sapling", properties -> new SaplingBlock(ModTreeGrowers.GOLDENLEAF,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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

    public static final WoodSetBlocks SAKURA_PLANKS = registerWoodSetBlocks("sakura", ModWoodTypes.SAKURA, ModBlockSetTypes.SAKURA, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.TERRACOTTA_WHITE, MapColor.COLOR_GRAY); //TODO: tweak MapColors
    //public static final BlockFamily SAKURA_PLANKS_FAMILY = new BlockFamily.Builder(SAKURA_PLANKS.planks.get())
    //        .button(SAKURA_PLANKS.button.get())
    //        .door(SAKURA_PLANKS.door.get())
    //        .fence(SAKURA_PLANKS.fence.get())
    //        .fenceGate(SAKURA_PLANKS.fenceGate.get())
    //        .sign(SAKURA_PLANKS.standingSign.get(), SAKURA_PLANKS.wallSign.get())
    //        .slab(SAKURA_PLANKS.slab.get())
    //        .stairs(SAKURA_PLANKS.stairs.get())
    //        .pressurePlate(SAKURA_PLANKS.pressurePlate.get())
    //        .trapdoor(SAKURA_PLANKS.trapdoor.get())
    //        .recipeGroupPrefix("wooden")
    //        .recipeUnlockedBy("has_planks")
    //        .getFamily();
    public static final DeferredBlock<Block> SAKURA_LEAVES = registerBlock("sakura_leaves", properties -> new SakuraLeavesBlock(
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
    public static final DeferredBlock<Block> SAKURA_SAPLING = registerBlock("sakura_sapling", properties -> new SaplingBlock(ModTreeGrowers.SAKURA,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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

    public static final WoodSetBlocks FRIGID_PLANKS = registerWoodSetBlocks("frigid", ModWoodTypes.FRIGID, ModBlockSetTypes.FRIGID, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_LIGHT_BLUE, MapColor.COLOR_BLUE); //TODO: tweak MapColors
    //public static final BlockFamily FRIGID_PLANKS_FAMILY = new BlockFamily.Builder(FRIGID_PLANKS.planks.get())
    //        .button(FRIGID_PLANKS.button.get())
    //        .door(FRIGID_PLANKS.door.get())
    //        .fence(FRIGID_PLANKS.fence.get())
    //        .fenceGate(FRIGID_PLANKS.fenceGate.get())
    //        .sign(FRIGID_PLANKS.standingSign.get(), FRIGID_PLANKS.wallSign.get())
    //        .slab(FRIGID_PLANKS.slab.get())
    //        .stairs(FRIGID_PLANKS.stairs.get())
    //        .pressurePlate(FRIGID_PLANKS.pressurePlate.get())
    //        .trapdoor(FRIGID_PLANKS.trapdoor.get())
    //        .recipeGroupPrefix("wooden")
    //        .recipeUnlockedBy("has_planks")
    //        .getFamily();
    public static final DeferredBlock<Block> FRIGID_LEAVES = registerBlock("frigid_leaves", properties -> new LeavesBlock(
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
    public static final DeferredBlock<Block> FRIGID_SAPLING = registerBlock("frigid_sapling", properties -> new SaplingBlock(ModTreeGrowers.FRIGID,
            properties
                    .mapColor(MapColor.PLANT) //TODO: tweak MapColor
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

    /*private static final DeferredBlock<Block> ARBOREAL_CACTUS_STEM = registerBlock("arboreal_cactus_stem", properties -> new CactusLogBlock(
            properties
                    .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_BLACK : MapColor.COLOR_MAGENTA) //TODO: tweak MapColors
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .randomTicks()
                    .ignitedByLava()
    ));
    private static final DeferredBlock<Block> ARBOREAL_CACTUS_HYPHAE = registerBlock("arboreal_cactus_hyphae", properties -> new CactusLogBlock(
            properties
                    .mapColor(MapColor.COLOR_MAGENTA) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    private static final DeferredBlock<Block> STRIPPED_ARBOREAL_CACTUS_STEM = registerBlock("stripped_arboreal_cactus_stem", properties -> new CactusLogBlock(
            properties
                    .mapColor(MapColor.COLOR_BLACK) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));
    private static final DeferredBlock<Block> STRIPPED_ARBOREAL_CACTUS_HYPHAE = registerBlock("stripped_arboreal_cactus_hyphae", properties -> new CactusLogBlock(
            properties
                    .mapColor(MapColor.COLOR_BLACK) //TODO: tweak MapColor
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
    ));*/
    //public static final WoodSetBlocks ARBOREAL_CACTUS_PLANKS = registerWoodSetBlocks(ARBOREAL_CACTUS_STEM, ARBOREAL_CACTUS_HYPHAE, STRIPPED_ARBOREAL_CACTUS_STEM, STRIPPED_ARBOREAL_CACTUS_HYPHAE, "arboreal_cactus", ModWoodTypes.ARBOREAL_CACTUS, ModBlockSetTypes.ARBOREAL_CACTUS, NoteBlockInstrument.BASS, SoundType.WOOD, MapColor.COLOR_BLACK, MapColor.COLOR_LIGHT_GREEN); //TODO: tweak MapColors
    //public static final BlockFamily ARBOREAL_CACTUS_PLANKS_FAMILY = new BlockFamily.Builder(ARBOREAL_CACTUS_PLANKS.planks.get())
    //        .button(ARBOREAL_CACTUS_PLANKS.button.get())
    //        .door(ARBOREAL_CACTUS_PLANKS.door.get())
    //        .fence(ARBOREAL_CACTUS_PLANKS.fence.get())
    //        .fenceGate(ARBOREAL_CACTUS_PLANKS.fenceGate.get())
    //        .sign(ARBOREAL_CACTUS_PLANKS.standingSign.get(), ARBOREAL_CACTUS_PLANKS.wallSign.get())
    //        .slab(ARBOREAL_CACTUS_PLANKS.slab.get())
    //        .stairs(ARBOREAL_CACTUS_PLANKS.stairs.get())
    //        .pressurePlate(ARBOREAL_CACTUS_PLANKS.pressurePlate.get())
    //        .trapdoor(ARBOREAL_CACTUS_PLANKS.trapdoor.get())
    //        .recipeGroupPrefix("wooden")
    //        .recipeUnlockedBy("has_planks")
    //        .getFamily();
    /*public static final DeferredBlock<Block> ARBOREAL_CACTUS_FRUIT = registerBlock("arboreal_cactus_fruit", properties -> new CactusFruitBlock(ModTreeGrowers.ARBOREAL_CACTUS,
            properties
                    .mapColor(MapColor.CRIMSON_STEM)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(0.2F, 3.0F)
                    .sound(SoundType.WOOL)
                    .randomTicks()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> POTTED_ARBOREAL_CACTUS = registerBlock("potted_arboreal_cactus", properties -> new FlowerPotBlock(ARBOREAL_CACTUS_FRUIT,
            properties
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    ));*/

    public static class ModBlockSetTypes {
        public static final BlockSetType CLOUDSHALE = BlockSetType.register(new BlockSetType("cloudshale", true, true, false, BlockSetType.PressurePlateSensitivity.MOBS, SoundType.STONE, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));

        public static final BlockSetType GOLDENLEAF = BlockSetType.register(new BlockSetType("goldenleaf"));
        public static final BlockSetType SAKURA = BlockSetType.register(new BlockSetType("sakura", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON));
        public static final BlockSetType FRIGID = BlockSetType.register(new BlockSetType("frigid"));
        public static final BlockSetType ARBOREAL_CACTUS = BlockSetType.register(new BlockSetType("arboreal_cactus", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.NETHER_WOOD, SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN, SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));
    }
    public static class ModWoodTypes {
        public static final WoodType GOLDENLEAF = WoodType.register(new WoodType("goldenleaf", ModBlockSetTypes.GOLDENLEAF));
        public static final WoodType SAKURA = WoodType.register(new WoodType("sakura", ModBlockSetTypes.SAKURA, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN));
        public static final WoodType FRIGID = WoodType.register(new WoodType("frigid", ModBlockSetTypes.FRIGID));
        public static final WoodType ARBOREAL_CACTUS = WoodType.register(new WoodType("arboreal_cactus", ModBlockSetTypes.ARBOREAL_CACTUS, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));
    }
    public static class ModTreeGrowers {
        public static final TreeGrower GOLDENLEAF = TreeGrower.OAK;//new TreeGrower("goldenleaf", Optional.empty(), Optional.of(ModConfiguredFeatures.GOLDENLEAF), Optional.of(ModConfiguredFeatures.GOLDENLEAF_BEES_005));
        public static final TreeGrower SAKURA = TreeGrower.CHERRY;//new TreeGrower("sakura", Optional.empty(), Optional.of(ModConfiguredFeatures.SAKURA), Optional.of(ModConfiguredFeatures.SAKURA_BEES_005));
        public static final TreeGrower FRIGID = TreeGrower.OAK;//new TreeGrower("frigid", Optional.empty(), Optional.of(ModConfiguredFeatures.FRIGID), Optional.of(ModConfiguredFeatures.FRIGID_BEES_005));
        public static final TreeGrower ARBOREAL_CACTUS = TreeGrower.OAK;//new TreeGrower("arboreal_cactus", Optional.empty(), Optional.of(ModConfiguredFeatures.ARBOREAL_CACTUS), Optional.empty());
    }

    public static void registerBlocks(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Blocks for " + SkysSkyIslands.MOD_ID);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
