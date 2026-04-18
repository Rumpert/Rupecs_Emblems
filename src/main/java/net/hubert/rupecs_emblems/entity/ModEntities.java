package net.hubert.rupecs_emblems.entity;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<EntityType<MoonAsteroidEntity>> MOON_ASTEROID =
            ENTITY_TYPES.register("moon_asteroid", () -> EntityType.Builder.of(MoonAsteroidEntity::new, MobCategory.MISC)
                    .sized(1.5F, 1.5F).build("moon_asteroid"));
    public static final RegistryObject<EntityType<CometShardProjectile>> COMET_SHARD =
            ENTITY_TYPES.register("comet_shard", () -> EntityType.Builder.<CometShardProjectile>of(CometShardProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("comet_shard"));
    public static final RegistryObject<EntityType<VolcanicShardProjectile>> VOLCANIC_SHARD =
            ENTITY_TYPES.register("volcanic_shard", () -> EntityType.Builder.<VolcanicShardProjectile>of(VolcanicShardProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("volcanic_shard"));
    public static final RegistryObject<EntityType<IcicleProjectile>> ICICLE =
            ENTITY_TYPES.register("icicle", () -> EntityType.Builder.<IcicleProjectile>of(IcicleProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("icicle"));
    public static final RegistryObject<EntityType<HealingNoteProjectile>> HEALING_NOTE =
            ENTITY_TYPES.register("healing_note", () -> EntityType.Builder.<HealingNoteProjectile>of(HealingNoteProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).build("healing_note"));
    public static final RegistryObject<EntityType<DamagingNoteProjectile>> DAMAGING_NOTE =
            ENTITY_TYPES.register("damaging_note", () -> EntityType.Builder.<DamagingNoteProjectile>of(DamagingNoteProjectile::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F).build("damaging_note"));
    public static final RegistryObject<EntityType<OwnableLightningBolt>> OWNABLE_LIGHTNING_BOLT =
            ENTITY_TYPES.register("ownable_lightning_bolt", () -> EntityType.Builder.<OwnableLightningBolt>of(OwnableLightningBolt::new, MobCategory.MISC)
                    .sized(0F, 0F).build("ownable_lightning_bolt"));




    public static final RegistryObject<EntityType<TameablePufferfish>> TAMEABLE_PUFFERFISH =
            ENTITY_TYPES.register("tameable_pufferfish", () -> EntityType.Builder.of(TameablePufferfish::new, MobCategory.MISC)
                    .build("tameable_pufferfish"));
    public static final RegistryObject<EntityType<TameableBee>> TAMEABLE_BEE =
            ENTITY_TYPES.register("tameable_bee", () -> EntityType.Builder.of(TameableBee::new, MobCategory.MISC)
                    .build("tameable_bee"));



    public static void register(IEventBus eventBus) {ENTITY_TYPES.register(eventBus);}
}
