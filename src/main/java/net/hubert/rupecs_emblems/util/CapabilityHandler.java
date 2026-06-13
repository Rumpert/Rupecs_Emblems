package net.hubert.rupecs_emblems.util;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.util.ICooldownCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityHandler {
    public static final Capability<ICooldownCapability> COOLDOWN_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});


}