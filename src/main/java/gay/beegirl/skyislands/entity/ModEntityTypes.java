package gay.beegirl.skyislands.entity;

import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.item.ModItems;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class ModEntityTypes {
    //public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, SkysSkyIslands.MOD_ID);

    public static final EnumProxy<Boat.Type> GOLDENLEAF_ENUM_PROXY = new EnumProxy<>(Boat.Type.class,
            ModBlocks.GOLDENLEAF_PLANKS.planks(),
            "goldenleaf",
            ModItems.GOLDENLEAF_BOAT,
            ModItems.GOLDENLEAF_CHEST_BOAT,
            Items.STICK,
            false
    );
    public static final EnumProxy<Boat.Type> SAKURA_ENUM_PROXY = new EnumProxy<>(Boat.Type.class,
            ModBlocks.SAKURA_PLANKS.planks(),
            "sakura",
            ModItems.SAKURA_BOAT,
            ModItems.SAKURA_CHEST_BOAT,
            Items.STICK,
            false
    );
    public static final EnumProxy<Boat.Type> FRIGID_ENUM_PROXY = new EnumProxy<>(Boat.Type.class,
            ModBlocks.FRIGID_PLANKS.planks(),
            "frigid",
            ModItems.FRIGID_BOAT,
            ModItems.FRIGID_CHEST_BOAT,
            Items.STICK,
            false
    );
    public static final EnumProxy<Boat.Type> ARBOREAL_CACTUS_ENUM_PROXY = new EnumProxy<>(Boat.Type.class,
            ModBlocks.ARBOREAL_CACTUS_PLANKS.planks(),
            "arboreal_cactus",
            ModItems.ARBOREAL_CACTUS_BOAT,
            ModItems.ARBOREAL_CACTUS_CHEST_BOAT,
            Items.STICK,
            false
    );
}
