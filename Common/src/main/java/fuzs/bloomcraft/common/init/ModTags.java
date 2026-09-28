package fuzs.bloomcraft.common.init;

import fuzs.bloomcraft.common.Bloomcraft;
import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {
    static final TagFactory TAGS = TagFactory.make(Bloomcraft.MOD_ID);

    public static class Biomes {
        public static final TagKey<Biome> HAS_PINK_DAISY_BIOME_TAG = register("has_pink_daisy");
        public static final TagKey<Biome> HAS_BUTTERCUP_BIOME_TAG = register("has_buttercup");

        private static TagKey<Biome> register(String name) {
            return TAGS.registerBiomeTag(name);
        }
    }
}
