package net.hubert.rupecs_emblems.event;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.entity.layer.ModModelLayers;
import net.hubert.rupecs_emblems.entity.model.MoonAsteroidModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.MOON_ASTEROID_LAYER, MoonAsteroidModel::createBodyLayer);


    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event){
        event.register(Keybinds.INSTANCE.primaryEmblemAbility);
        event.register(Keybinds.INSTANCE.secondaryEmblemAbility);
    }

}
