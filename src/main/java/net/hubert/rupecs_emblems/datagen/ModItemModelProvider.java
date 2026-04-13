package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Rupecs_Emblems.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.OVERWORLD_EMBLEMIUM);
        simpleItem(ModItems.NETHER_EMBLEMIUM);
        simpleItem(ModItems.END_EMBLEMIUM);
        simpleItem(ModItems.STAR_EMBLEMIUM);
        simpleItem(ModItems.WOODEN_EMBLEMIUM);
        simpleItem(ModItems.CLOUD_EMBLEMIUM);
        simpleItem(ModItems.BLESSED_EMBLEMIUM);
        simpleItem(ModItems.PROFANED_EMBLEMIUM);
        simpleItem(ModItems.ANCIENT_EMBLEMIUM);
        simpleItem(ModItems.CURSED_EMBLEMIUM);
        simpleItem(ModItems.GLOBAL_EMBLEMIUM);
        simpleItem(ModItems.COSMIC_EMBLEMIUM);

        simpleItem(ModItems.PLANT_SIGIL_1);
        simpleItem(ModItems.PLANT_SIGIL_2);
        simpleItem(ModItems.EMBLEMIUM_SIGIL);
        simpleItem(ModItems.EMBLEMIUM_INSIGNIA);

        simpleItem(ModItems.EMBLEMENTAL_ENTHERA);

        simpleItem(ModItems.ENTHEREAL_MASS);
        simpleItem(ModItems.ENTHEREAL_CONJUGATION);
        simpleItem(ModItems.ENTHEREAL_VOID);

        simpleItem(ModItems.MANIPULATION_CORE);

        simpleItem(ModItems.BULBULIUM);
        simpleItem(ModItems.BURNING_CLOTH);
        simpleItem(ModItems.SUNLIGHT_SHARD);
        simpleItem(ModItems.SHIELDED_FOSSIL);
        simpleItem(ModItems.VERDANT_CORE);
        simpleItem(ModItems.BONSAI);
        simpleItem(ModItems.REFERENCIUM);
        simpleItem(ModItems.FRACTURED_ICICLE);
        simpleItem(ModItems.GLASS_LUNG);
        simpleItem(ModItems.BATTERY);
        simpleItem(ModItems.THE_EARTH);
        simpleItem(ModItems.THE_TORCH);
        simpleItem(ModItems.JACK_OF_SPADES);
        simpleItem(ModItems.BENT_FIN);
        simpleItem(ModItems.MOON_FRAGMENT);
        simpleItem(ModItems.IRON_PLATING);
        simpleItem(ModItems.COIN_NECKLACE);
        simpleItem(ModItems.BREAD_PIECE);
        simpleItem(ModItems.HALF_NOTE);


        simpleItem(ModItems.NEPTUNES_GIFT);
        simpleItem(ModItems.SEED_POUCH);
        simpleItem(ModItems.COSMOS_ESSENCE);
        simpleItem(ModItems.PHOENIX_FEATHER);
        simpleItem(ModItems.DOVE_WING);
        simpleItem(ModItems.ANCIENT_REMAINS);
        simpleItem(ModItems.TESLA_BAR);
        simpleItem(ModItems.RADIANT_GEODE);
        simpleItem(ModItems.PROMETHEUS_GIFT);
        simpleItem(ModItems.WINE_CUP);
        simpleItem(ModItems.DISCAPTURED_ENERGY);
        simpleItem(ModItems.WISTERIA_FLOWER);
        simpleItem(ModItems.CORAL_CROWN);
        simpleItem(ModItems.SALT_ORE);
        simpleItem(ModItems.BUBBLIUM);


        simpleItem(ModItems.EAGLE_BEAK);
        simpleItem(ModItems.TINY_BUSH);
        simpleItem(ModItems.SHATTERED_VOID);
        simpleItem(ModItems.PAW_FOSSIL);
        simpleItem(ModItems.STINGER);
        simpleItem(ModItems.MOONGLOW);
        simpleItem(ModItems.SUNRAY_BEAM);
        simpleItem(ModItems.STAR_SAPLING);
        simpleItem(ModItems.REEFS_GLORY);
        simpleItem(ModItems.JADE_EARRING);
        simpleItem(ModItems.BRITTLE_FOSSIL);
        simpleItem(ModItems.SAPPHIRE_NECKLACE);
        simpleItem(ModItems.OWSHUN);
        simpleItem(ModItems.BONE_CROWN);
        simpleItem(ModItems.EARF);


        simpleItem(ModItems.LIVING_MASS);
        simpleItem(ModItems.EDENS_VINES);
        simpleItem(ModItems.TANGLED_TAILS);
        simpleItem(ModItems.SERPENT_SEED);
        simpleItem(ModItems.ROOTED_ROCK);
        simpleItem(ModItems.GRAND_ANCHOR);
        simpleItem(ModItems.BLACK_PEARL);


        simpleItem(ModItems.EVES_APPLE);



        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            if (item.get() instanceof ModEmblemItem){
                simpleItem(item);
            }
        }




    }

    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Rupecs_Emblems.MOD_ID, "item/" + item.getId().getPath()));
    }
}
