package fuzs.bloomcraft.common.init;

import fuzs.bloomcraft.common.Bloomcraft;
import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Bloomcraft.MOD_ID);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.BUTTERCUP);

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModEntityDataSerializers.bootstrap();
        ModEntityTypes.bootstrap();
        ModDataComponentTypes.bootstrap();
        ModItems.bootstrap();
    }
}
