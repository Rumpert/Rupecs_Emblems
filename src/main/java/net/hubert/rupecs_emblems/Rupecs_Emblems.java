package net.hubert.rupecs_emblems;

import com.mojang.logging.LogUtils;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.block.entity.ModBlockEntities;
import net.hubert.rupecs_emblems.command.gamerule.ModGameRules;
import net.hubert.rupecs_emblems.config.RupecsEmblemsClientConfig;
import net.hubert.rupecs_emblems.config.RupecsEmblemsCommonConfig;
import net.hubert.rupecs_emblems.datagen.ModLootModifiers;
import net.hubert.rupecs_emblems.effect.ModEffects;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.render.*;
import net.hubert.rupecs_emblems.item.ModCreativeModTabs;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.particle.ModParticles;
import net.hubert.rupecs_emblems.recipe.ModRecipes;
import net.hubert.rupecs_emblems.screen.EntherealSelectorScreen;
import net.hubert.rupecs_emblems.screen.ModMenuTypes;
import net.hubert.rupecs_emblems.screen.RealityManipulatorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BeeRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.PufferfishRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Rupecs_Emblems.MOD_ID)
public class Rupecs_Emblems
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "rupecs_emblems";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public Rupecs_Emblems()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Mixins.addConfiguration("mixin.rupecs_emblems.json");
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModGameRules.register();
        ModCreativeModTabs.register(modEventBus);
        ModEffects.register(modEventBus);
        ModAttributes.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModParticles.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RupecsEmblemsClientConfig.SPEC, "rupec_emblems-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RupecsEmblemsCommonConfig.SPEC, "rupec_emblems-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        PacketHandler.register();
}

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.MOON_ASTEROID.get(), MoonAsteroidProjectileRenderer::new);
            EntityRenderers.register(ModEntities.COMET_SHARD.get(), CometShardRender::new);
            EntityRenderers.register(ModEntities.ICICLE.get(), IcicleRender::new);
            EntityRenderers.register(ModEntities.HEALING_NOTE.get(), HealingNoteRender::new);
            EntityRenderers.register(ModEntities.DAMAGING_NOTE.get(), DamagingNoteRender::new);
            EntityRenderers.register(ModEntities.TAMEABLE_PUFFERFISH.get(), PufferfishRenderer::new);
            EntityRenderers.register(ModEntities.TAMEABLE_BEE.get(), BeeRenderer::new);
            EntityRenderers.register(ModEntities.OWNABLE_LIGHTNING_BOLT.get(), LightningBoltRenderer::new);

            MenuScreens.register(ModMenuTypes.REALITY_MANIPULATOR_MENU.get(), RealityManipulatorScreen::new);
            MenuScreens.register(ModMenuTypes.ENTHEREAL_SELECTOR_MENU.get(), EntherealSelectorScreen::new);

            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.REALITY_MANIPULATOR.get(),
                        RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.ENTHEREAL_SELECTOR.get(),
                        RenderType.translucent());
            });
        }
    }


}
