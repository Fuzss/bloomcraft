package fuzs.bloomcraft.world.entity.animal;

import fuzs.bloomcraft.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class FlowerMobVariantUtil {

    private FlowerMobVariantUtil() {
        // NO-OP
    }

    public static Moobloom moobloom(EntityType<Moobloom> entityType, Level level) {
        return new Moobloom(entityType,
                level,
                ModRegistry.MOOBLOOM_VARIANT_REGISTRY_KEY,
                ModRegistry.MOOBLOOM_VARIANT_DATA_TYPE.apply(Moobloom.class));
    }

    public static Cluckbloom cluckbloom(EntityType<Cluckbloom> entityType, Level level) {
        return new Cluckbloom(entityType,
                level,
                ModRegistry.CLUCKBLOOM_VARIANT_REGISTRY_KEY,
                ModRegistry.CLUCKBLOOM_VARIANT_DATA_TYPE.apply(Cluckbloom.class));
    }

    public static Holder<FlowerMobVariant> getSpawnVariant(Registry<FlowerMobVariant> registry, Holder<Biome> biome, RandomSource randomSource) {
        return getRandomSpawnVariant(registry, (Holder<FlowerMobVariant> holder) -> {
            return holder.value().biomes().contains(biome);
        }).or(() -> registry.getRandom(randomSource)).orElseThrow();
    }

    public static Optional<Holder<FlowerMobVariant>> getRandomSpawnVariant(Registry<FlowerMobVariant> registry, Predicate<Holder<FlowerMobVariant>> filter) {
        return Optional.ofNullable(registry.listElements()
                .filter(filter)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                        (List<Holder.Reference<FlowerMobVariant>> list) -> {
                            Collections.shuffle(list);
                            return !list.isEmpty() ? list.getFirst() : null;
                        })));
    }

    public static class VariantGroupData extends AgeableMob.AgeableMobGroupData {
        public final Holder<FlowerMobVariant> variant;

        public VariantGroupData(Holder<FlowerMobVariant> variant) {
            this(variant, true);
        }

        public VariantGroupData(Holder<FlowerMobVariant> variant, boolean shouldSpawnBaby) {
            super(shouldSpawnBaby);
            this.variant = variant;
        }
    }
}
