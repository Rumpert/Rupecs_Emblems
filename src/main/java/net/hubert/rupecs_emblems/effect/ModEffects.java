package net.hubert.rupecs_emblems.effect;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.effect.custom.BleedingEffect;
import net.hubert.rupecs_emblems.effect.custom.FarmersBlessingEffect;
import net.hubert.rupecs_emblems.effect.custom.ImmortalityEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<MobEffect> BLEEDING = EFFECTS.register("bleeding", BleedingEffect::new);
    public static final RegistryObject<MobEffect> IMMORTALITY = EFFECTS.register("immortality", ImmortalityEffect::new);
    public static final RegistryObject<MobEffect> FARMERS_BLESSING = EFFECTS.register("farmers_blessing", FarmersBlessingEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
