package gay.beegirl.entity;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {
    private static Holder<Attribute> registerAttribute(String string, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, string), attribute);
    }

    public static void registerAttributes() {
        SkysSkyIslands.LOGGER.info("Registering Attributes for " + SkysSkyIslands.MOD_ID);
    }

    public static final Holder<Attribute> STAMINA_BONUS = registerAttribute("stamina_bonus", (new RangedAttribute("attribute.sky-islands.stamina_bonus", 0.0F, 0.0F, 6.0E7F)).setSyncable(true));
}
