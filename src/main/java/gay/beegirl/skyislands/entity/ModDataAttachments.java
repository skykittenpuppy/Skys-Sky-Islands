package gay.beegirl.skyislands.entity;

import com.mojang.serialization.Codec;
import gay.beegirl.skyislands.SkysSkyIslands;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModDataAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SkysSkyIslands.MOD_ID);

    public static final Supplier<AttachmentType<Boolean>> IS_FREEFALLING;
    public static final Supplier<AttachmentType<Boolean>> IS_DIVING;
    public static final Supplier<AttachmentType<Boolean>> IS_GLIDING;


    public static void registerDataAttachments(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Data Attachments for " + SkysSkyIslands.MOD_ID);

        ATTACHMENT_TYPES.register(modEventBus);
    }

    static {
        IS_FREEFALLING = ATTACHMENT_TYPES.register("is_freefalling", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL.fieldOf("is_freefalling").codec()).build());
        IS_DIVING = ATTACHMENT_TYPES.register("is_diving", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL.fieldOf("is_diving").codec()).build());
        IS_GLIDING = ATTACHMENT_TYPES.register("is_gliding", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL.fieldOf("is_gliding").codec()).build());
    }
}
