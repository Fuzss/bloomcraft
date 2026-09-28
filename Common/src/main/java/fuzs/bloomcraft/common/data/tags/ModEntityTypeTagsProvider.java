package fuzs.bloomcraft.common.data.tags;

import fuzs.bloomcraft.common.init.ModEntityTypes;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTagsProvider extends AbstractTagsProvider<EntityType<?>> {

    public ModEntityTypeTagsProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE);
        this.tag(EntityTypeTags.FOLLOWABLE_FRIENDLY_MOBS).add(ModEntityTypes.CLUCKBLOOM_ENTITY_TYPE);
    }
}
