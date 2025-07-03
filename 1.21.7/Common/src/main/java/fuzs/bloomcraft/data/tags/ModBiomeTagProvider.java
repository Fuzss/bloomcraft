package fuzs.bloomcraft.data.tags;

import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class ModBiomeTagProvider extends AbstractTagProvider<Biome> {

    public ModBiomeTagProvider(DataProviderContext context) {
        super(Registries.BIOME, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ModRegistry.HAS_BUTTERCUP_BIOME_TAG).add(Biomes.PLAINS, Biomes.MEADOW, Biomes.FLOWER_FOREST);
        this.tag(ModRegistry.HAS_PINK_DAISY_BIOME_TAG).add(Biomes.PLAINS, Biomes.MEADOW, Biomes.FLOWER_FOREST);
    }
}
