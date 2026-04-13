package net.hubert.rupecs_emblems.screen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Rupecs_Emblems.MOD_ID);


    public static final RegistryObject<MenuType<RealityManipulatorMenu>> REALITY_MANIPULATOR_MENU =
            registerMenuTypes("reality_manipulator_menu", RealityManipulatorMenu::new);


    public static final RegistryObject<MenuType<EntherealSelectorMenu>> ENTHEREAL_SELECTOR_MENU =
            registerMenuTypes("enthereal_selector_menu", EntherealSelectorMenu::new);









    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuTypes(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
