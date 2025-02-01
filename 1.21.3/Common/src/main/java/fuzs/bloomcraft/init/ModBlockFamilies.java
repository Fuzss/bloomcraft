package fuzs.bloomcraft.init;

import net.minecraft.data.BlockFamily;

import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final BlockFamilyRegistrar STEMWOOD_FAMILY = BlockFamilyRegistrar.wooden(ModRegistry.REGISTRIES,
            ModBlocks.STEMWOOD_PLANKS,
            "stemwood").getFamily();

    public static void bootstrap() {
        // NO-OP
    }

    public static Stream<BlockFamilyRegistrar> getAllFamilyRegistrars() {
        return Stream.of(STEMWOOD_FAMILY);
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return getAllFamilyRegistrars().map(BlockFamilyRegistrar::getWoodenFamily);
    }
}
