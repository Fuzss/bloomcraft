package fuzs.bloomcraft.data.tags;

import fuzs.bloomcraft.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class ModBiomeTagProvider extends AbstractTagProvider<Biome> {

    public ModBiomeTagProvider(DataProviderContext context) {
        super(Registries.BIOME, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.add(ModRegistry.HAS_BUTTERCUP_BIOME_TAG).add(Biomes.PLAINS, Biomes.MEADOW, Biomes.FLOWER_FOREST);
        this.add(ModRegistry.HAS_PINK_DAISY_BIOME_TAG).add(Biomes.PLAINS, Biomes.MEADOW, Biomes.FLOWER_FOREST);
        this.add(ModRegistry.HAS_ROSE_BIOME_TAG).add(Biomes.PLAINS, Biomes.MEADOW, Biomes.FLOWER_FOREST);
        this.add(BiomeTags.IS_OVERWORLD).add(ModRegistry.FLOWERING_GARDEN_BIOME);
        this.add(BiomeTags.HAS_VILLAGE_PLAINS).add(ModRegistry.FLOWERING_GARDEN_BIOME);
        this.add(BiomeTags.HAS_MINESHAFT).add(ModRegistry.FLOWERING_GARDEN_BIOME);
        this.add(BiomeTags.HAS_TRIAL_CHAMBERS).add(ModRegistry.FLOWERING_GARDEN_BIOME);
        this.add(BiomeTags.STRONGHOLD_BIASED_TO).add(ModRegistry.FLOWERING_GARDEN_BIOME);
        this.add(BiomeTags.WITHOUT_ZOMBIE_SIEGES).add(ModRegistry.FLOWERING_GARDEN_BIOME);
        this.add(BiomeTags.WITHOUT_PATROL_SPAWNS).add(ModRegistry.FLOWERING_GARDEN_BIOME);
    }
}
