package fuzs.bloomcraft.world.entity.animal;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
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

    public static <T extends FlowerMobVariant> Holder<T> getSpawnVariant(Registry<T> registry, Holder<Biome> biome, RandomSource randomSource) {
        return getRandomSpawnVariant(registry, (Holder<T> holder) -> {
            return holder.value().biomes().contains(biome);
        }).or(() -> registry.getRandom(randomSource)).orElseThrow();
    }

    public static <T extends FlowerMobVariant> Optional<Holder<T>> getRandomSpawnVariant(Registry<T> registry, Predicate<Holder<T>> filter) {
        return Optional.ofNullable(registry.listElements()
                .filter(filter)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                        (List<Holder.Reference<T>> list) -> {
                            Collections.shuffle(list);
                            return !list.isEmpty() ? list.getFirst() : null;
                        })));
    }

    public static class VariantGroupData<T extends FlowerMobVariant> extends AgeableMob.AgeableMobGroupData {
        public final Holder<T> variant;

        public VariantGroupData(Holder<T> variant) {
            this(variant, true);
        }

        public VariantGroupData(Holder<T> variant, boolean shouldSpawnBaby) {
            super(shouldSpawnBaby);
            this.variant = variant;
        }
    }
}
