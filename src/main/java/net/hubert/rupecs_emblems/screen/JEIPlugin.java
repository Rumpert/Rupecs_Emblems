package net.hubert.rupecs_emblems.screen;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Rupecs_Emblems.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(
                new ItemStack(ModItems.ENTHEREAL_MASS.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Has a chance to spawn alongside an itemium.")

        );
        registration.addIngredientInfo(
                new ItemStack(ModItems.BULBULIUM.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is standing in a bubble column."),
                Component.literal("\"Tiny bubbles glisten and shine\n" +
                        "Rising towards the ray of sun.\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Dignity\"").withStyle(ChatFormatting.DARK_GRAY)

        );
        registration.addIngredientInfo(
                new ItemStack(ModItems.BURNING_CLOTH.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is burning."),
                Component.literal("\"Trash goes in the trash can\n" +
                        "Junk belongs to the junk bin, please.\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"RuLe\"").withStyle(ChatFormatting.DARK_GRAY)

        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SUNLIGHT_SHARD.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is suffocating on dessert during day."),
                Component.literal("\"Even if the sun shines a little too much\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Himawari\"").withStyle(ChatFormatting.DARK_GRAY)

        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SHIELDED_FOSSIL.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is mining dirt or stone."),
                Component.literal("\"To remember something\n" +
                        "It must have all been a lie.\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Value\"").withStyle(ChatFormatting.DARK_GRAY)


        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.VERDANT_CORE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is using Bone Meal on land."),
                Component.literal("\"Let's celebrate this day of courage,\n" +
                        "A bouquet for me.\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Bouquet for me\"").withStyle(ChatFormatting.DARK_GRAY)

        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BONSAI.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is stripping or cutting logs."),
                Component.literal("\"All right, \"Do your best!\" but I already am.\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Bouquet for me\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.NEPTUNES_GIFT.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Exposured to rain, dripping-wet naive, cunning isn't it?\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Missing\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.PROMETHEUS_GIFT.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"We didn't start the fire, It was always burning since the world's been turning\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Billy Joel - \"We Didn't Start the Fire\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.WINE_CUP.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Then he took a cup, and when he had given thanks, he gave it to them, saying, \"Drink of it, all of you, for this is my blood of the covenant, which is poured out for many for the forgiveness of sins.\".\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Jesus - \"Matthew 26:27-28\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.DISCAPTURED_ENERGY.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Make a noise!!\nLate night parade\nLet's all dance as we like").withStyle(ChatFormatting.ITALIC),
                Component.literal("9Lana - \"Midnight Parade\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.LIVING_MASS.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"I DO WHAT I DO, I DO WHAT I DO\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("9Lana - \"Midnight Parade\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.EDENS_VINES.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"My icon of an unread lost child, The story remains static\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("9Lana - \"Migi Poketto\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.TANGLED_TAILS.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"No one else, only you, Even if the world has not yet forgotten\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Natori - \"Serenade\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.EVES_APPLE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Ever on and on, I continue circling, With nothing but my hate in a carousel of agony\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Cristina Vee - \"Bad Apple\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BLACK_PEARL.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Let's see it through, just like we promised, Within this cold, dark, and brokеn world\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Vicke Blanka - \"Black Catcher\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.GRAND_ANCHOR.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"All is sinking into destiny, There is nothing left to do\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Dignity\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.ROOTED_ROCK.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"I can't see, I can't breathe, no more will we be, And nothing's going to change the way we live\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Jamiroquai - \"Virtual Insanity\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SERPENT_SEED.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"It’s my fault I can’t let go, Because of this smoldering resolve\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Tatsuya Kitani - \"Chained\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SEED_POUCH.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Staring right in the eyes of the things that make you feel again\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Eve - \"Literaly Nonsense\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.COSMOS_ESSENCE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"You would not believe your eyes\n" +
                        "If ten million fireflies\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Owl City - \"Fireflies\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.EARF.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Earf, earf\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("A dog - \"Barking\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BONE_CROWN.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"For some reason, I can't explain, Once you'd gone, there was never\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Coldplay - \"Viva La Vida\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.OWSHUN.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"I take off to a world unknown, Carrying my hope\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Kasane Teto, Hatsune Miku, Akita Neru - \"Triple Baka\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SAPPHIRE_NECKLACE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Mother mother will you forgive me, Even with great love, I can't erase the cross\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Motherland\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.REEFS_GLORY.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"A sad clown who cleans up his youthful mistakes, Cry Lonely Lonely\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Motherland\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.JADE_EARRING.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Raised in a noodle shop, never seeking glory or fame, He climbed a mountain top and earned the Dragon Warrior name\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("CeeLo Green and Jack Black - \"Kung Fu Panda Legends of Awesomeness\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BRITTLE_FOSSIL.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"If we all come to an end eventually, If I could at least leave a mark\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Sakuzyo - \"I Want To Become a Monster\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.STAR_SAPLING.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Miss, I'm such a star, we're serving as support to her grace, Cannot tell me everything was because of her, no, it's not right\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Yoasobi - \"Idol\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SUNRAY_BEAM.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Let's hear it for now T.M. Opera O!\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("T.M. Opera O - \"T.M. Opera O's Song\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.MOONGLOW.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"As if it changed someone's life, I feel like I can live with that\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Tatsuya Kitani - \"Moonthief\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.PAW_FOSSIL.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Move forward, feel the passion of youth, Come on, push past your limits, and fly!\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Suzie - \"Speed Of Spice\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.STINGER.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"RACING THROUGH, I'm like thunder, no wonder, no one stands a chance! There's no room for a mistake\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Suzie - \"Speed Of Spice\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BUBBLIUM.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Hide it, smash it, what’s the call? Can I stay true, just as I am?\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Angelseek\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SALT_ORE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"The mirror reflects an ideal image, miles away, And still today, I set these clumsy fingertips to work again\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Vivarium\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.CORAL_CROWN.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Just a puppet on a lonely string, Aw, who would ever wanna be king?\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Coldplay - \"Viva La Vida\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.WISTERIA_FLOWER.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"I've found a reason to become strong, Take me with you, and let's march forward\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("LiSA - \"Gurenge\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.PHOENIX_FEATHER.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Forget even the things you've forgotten\n" +
                        "Let sorrow and warmth vanish\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Elf\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.ANCIENT_REMAINS.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Grey clouds roll over the hills bringing darkness from above\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Bastille - \"Pompeii\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.TESLA_BAR.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"I take things back when noted insufficient\n" +
                        "I learn to tell you no if running inefficient\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Kasane Teto - \"Mashine Love\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.RADIANT_GEODE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"If it really is divisible, I wonder which one is better.\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Motherland\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.SHATTERED_VOID.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"I need you to fill the void\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("The Neighbourhood - \"Void\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.EAGLE_BEAK.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"Say, can you see\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Francis Scott Key - \"US Anthem\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.TINY_BUSH.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("Doesn't spawn naturally."),
                Component.literal("\"The reason I think it's trivial\n" +
                        "Is because I know it's in my dreams\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Value\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.REFERENCIUM.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is eating meat during thunderstorm or on top of a cactus."),
                Component.literal("\"The thorns of the words I've gulped down stab me\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Eve - \"How to eat life\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.IRON_PLATING.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is taking damage while wearing iron armor."),
                Component.literal("\"I swore I'd never sin again, but my patience's running thin\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Sawtowne - \"Confessions of a Rotten Girl\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.GLASS_LUNG.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is taking damage from drowning."),
                Component.literal("\"Now, just listening to your breathing\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Dignity\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.FRACTURED_ICICLE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player is taking damage from freezing."),
                Component.literal("\"With resolve so fragile\n" +
                        "That I couldn't even say out loud\n" +
                        "I couldn't save anything\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"Episode X\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BATTERY.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when plcing a lightning rod on copper block while standing on a block of coal."),
                Component.literal("\"Thunder, feel the thunder\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Imagine Dragons - \"Thunder\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.THE_EARTH.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when mining quartz."),
                Component.literal("\"Who do you think you are?\n" +
                        "Dreaming 'bout being a big star\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Imagine Dragons - \"Thunder\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.THE_TORCH.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when placing a torch on polished diorite or bedrock."),
                Component.literal("\"They say, \"You're basic,\" they say, \"You're easy\"\n" +
                        "You're always riding in the backseat\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Imagine Dragons - \"Thunder\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.JACK_OF_SPADES.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when attacking a squid with paper."),
                Component.literal("\"Can't hide what lies behind\n" +
                        "What we hide, and what we fight\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Eve - \"How to eat life\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.COIN_NECKLACE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when trading."),
                Component.literal("\"I'll wear your granddad's clothes\n" +
                        "I look incredible\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Macklemore & Ryan Lewis - \"Thrift Shop\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BREAD_PIECE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when eating bread in a boat."),
                Component.literal("\"And my boat I do leave on the shore now,\n" +
                        "Since today l’be fishing with you. \n\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Cesáreo Gabaráin - \"Pescador de hombres\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.HALF_NOTE.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when player plays a goat horn."),
                Component.literal("\"So I sing an old familiar song, I hope you know the one\n" +
                        "I will sing to call you back to me.\n\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Ado - \"The World's Continuation\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.BENT_FIN.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when killing animals which are in water."),
                Component.literal("\"What you gon' do when there's blood in the water?\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("Grandson - \"Blood // Water\"").withStyle(ChatFormatting.DARK_GRAY)
        );

        registration.addIngredientInfo(
                new ItemStack(ModItems.MOON_FRAGMENT.get()),
                VanillaTypes.ITEM_STACK,
                Component.literal("May appear when eating while being at Y128 and above."),
                Component.literal("\"And if she didn't fly, nor would you and I\n" +
                        "A forever hero for humankind\"").withStyle(ChatFormatting.ITALIC),
                Component.literal("EMMY - \"Laika Party\"").withStyle(ChatFormatting.DARK_GRAY)
        );






        registration.addIngredientInfo(new ItemStack(ModItems.MOON_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Moon Emblem Effects:\n" +
                        "-25% gravity\n" +
                        "+1 Damage\n" +
                        "+0.09 Moon Favor\n" +
                        "No fall damage\n" +
                        "Night boosts:\n" +
                        "• Speed +0.005 or +0.015\n" +
                        "• Damage +0.1 or +0.25\n" +
                        "Full moon affects power of night boosts"));

        registration.addIngredientInfo(new ItemStack(ModItems.FULL_MOON_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Full Moon Emblem Effects:\n" +
                        "-40% gravity\n" +
                        "+2 Damage\n" +
                        "+0.09 Moon Favor\n" +
                        "+2 Moon Wrath\n" +
                        "No fall damage\n" +
                        "Night boosts:\n" +
                        "• Speed +0.01 to +0.03\n" +
                        "• Damage +0.2 to +0.5\n" +
                        "Full moon affects power of night boosts\n" +
                        "Secondary Ability: Spawn Moon Asteroids; 5 per Moon Wrath level"));

        registration.addIngredientInfo(new ItemStack(ModItems.BLOOD_MOON_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Blood Moon Emblem Effects:\n" +
                        "-40% gravity\n" +
                        "+0.1 Moon Favor\n" +
                        "+2 Moon Wrath\n" +
                        "No fall damage\n" +
                        "Night boosts:\n" +
                        "• Speed +0.03\n" +
                        "• Damage +0.5\n" +
                        "Full moon affects power of night boosts\n" +
                        "Shoots 5 comet shards periodically\n" +
                        "Primary Ability: Moon Jump\n" +
                        "Secondary Ability: Spawn Moon Asteroids; 5 per Moon Wrath level"));

        registration.addIngredientInfo(new ItemStack(ModItems.BLOODSTAR_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Bloodstar Emblem Effects:\n" +
                        "-40% gravity\n" +
                        "+3 Photosynthesis\n" +
                        "+0.05 Speed\n" +
                        "+0.65 Damage\n" +
                        "+3 Moon Wrath\n" +
                        "+0.1 Moon Favor\n" +
                        "No fall damage\n" +
                        "Fire Resistance\n" +
                        "Fire Fist level 9\n" +
                        "Fire Ring level 3\n" +
                        "Shoots 9 comet shards periodically\n" +
                        "Primary Ability: Moon Jump\n" +
                        "Secondary Ability: Spawn Moon Asteroids; 5 per Moon Wrath level"));

        registration.addIngredientInfo(new ItemStack(ModItems.SAND_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sand Emblem Effects:\n" +
                        "+0.01 Speed\n" +
                        "+0.03 Speed if on sand\n"));

        registration.addIngredientInfo(new ItemStack(ModItems.FOSSIL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Fossil Emblem Effects:\n" +
                        "+0.01 Speed\n" +
                        "+0.03 Speed if on sand\n"+
                        "+4 Armor\n"+
                        "+0.5 Mining Speed\n"));

        registration.addIngredientInfo(new ItemStack(ModItems.REMAINS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Remains Emblem Effects:\n" +
                        "+0.02 Speed\n" +
                        "+0.04 Speed if on sand\n"+
                        "+6 Armor\n"+
                        "+1 Mining Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.SUN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sun Emblem Effects:\n" +
                        "+2 Photosynthesis\n" +
                        "Daytime boosts:\n" +
                        "• Speed +0.01\n" +
                        "• Damage +0.2"));

        registration.addIngredientInfo(new ItemStack(ModItems.SUPERNOVA_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Supernova Emblem Effects:\n" +
                        "+3 Photosynthesis\n" +
                        "Fire Resistance\n" +
                        "Fire Fist level 9\n" +
                        "Fire Ring level 3\n" +
                        "Daytime boosts:\n" +
                        "• Speed +0.02\n" +
                        "• Damage +0.4"));

        // NATURE & PLANT EMBLEMS
        registration.addIngredientInfo(new ItemStack(ModItems.WOOD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Wood Emblem Effects:\n" +
                        "+2 Max Health"));
        registration.addIngredientInfo(new ItemStack(ModItems.WOOD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Wood Emblem Effects:\n" +
                        "+10 Max Health"));

        registration.addIngredientInfo(new ItemStack(ModItems.PLANT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Plant Emblem Effects:\n" +
                        "+1 Nature Regen"));
        registration.addIngredientInfo(new ItemStack(ModItems.PLANT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Plant Emblem Effects:\n" +
                        "+6 Nature Regen"));

        registration.addIngredientInfo(new ItemStack(ModItems.STONE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Stone Emblem Effects:\n" +
                        "+0.5 Damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.GROUND_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Stone Emblem Effects:\n" +
                        "+0.5 Attack Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.CRYSTAL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Crystal Emblem Effects:\n" +
                        "+1 Fortune Fist"));

        registration.addIngredientInfo(new ItemStack(ModItems.SKY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sky Emblem Effects:\n" +
                        "-20% gravity\n"+
                        "+0.01 Speed\n"+
                        "-20% fall damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.VEGE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Vege Emblem Effects:\n" +
                        "+1 Animal Temptation\n"+
                        "Can't eat meat\n"+
                        "Hunger drains faster"));

        registration.addIngredientInfo(new ItemStack(ModItems.FLOWER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Flower Emblem Effects:\n" +
                        "+1 Photosynthesis"));

        registration.addIngredientInfo(new ItemStack(ModItems.BONE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Bone Emblem Effects:\n" +
                        "+4 Armor"));

        registration.addIngredientInfo(new ItemStack(ModItems.INFERNO_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Inferno Emblem Effects:\n" +
                        "Fire Ring level 2"));
        registration.addIngredientInfo(new ItemStack(ModItems.INFERNO_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Inferno Emblem Effects:\n" +
                        "Fire Ring level 2 with increased range"));

        registration.addIngredientInfo(new ItemStack(ModItems.MOON_JUMPER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Moon Jumper Emblem Effects:\n" +
                        "Primary Ability: Moon Jump"));

        registration.addIngredientInfo(new ItemStack(ModItems.TREE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Tree Emblem Effects:\n" +
                        "+3 Max Health\n" +
                        "+1 Photosynthesis\n" +
                        "+1 Nature Regen\n" +
                        "+0.5 Attack Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Cave Emblem Effects:\n" +
                        "+2 Fortune Fist\n" +
                        "+5 Armor\n" +
                        "+0.01 Speed\n" +
                        "+0.75 Damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.LUSH_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Lush Cave Emblem Effects:\n" +
                        "+2 Fortune Fist\n" +
                        "+5 Armor\n" +
                        "+0.01 Speed\n" +
                        "+0.75 Damage\n" +
                        "+2 Entity Reach\n" +
                        "+20% Sneaky Defence"));

        registration.addIngredientInfo(new ItemStack(ModItems.DESERT_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Desert Cave Emblem Effects:\n" +
                        "+2 Fortune Fist\n" +
                        "+11 Armor\n" +
                        "+0.03 Speed\n" +
                        "+0.75 Damage\n"+
                        "+0.05 Speed if on sand\n"+
                        "+1 Mining Speed"));


        registration.addIngredientInfo(new ItemStack(ModItems.LIFELESS_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Lifeless Cave Emblem Effects:\n" +
                        "+3 Fortune Fist\n" +
                        "+20 Armor\n" +
                        "+0.03 Speed\n" +
                        "+0.05 Speed if on sand\n"+
                        "+1.4 Mining Speed\n" +
                        "+1.5 Damage\n"+
                        "Near Ceiling boosts:\n" +
                        "• +5 Attack Damage\n" +
                        "Underground boosts:\n" +
                        "• +6 Armor"));


        registration.addIngredientInfo(new ItemStack(ModItems.ATOMIC_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Atomic Cave Emblem Effects:\n" +
                        "+5 Fortune Fist\n" +
                        "+30 Armor\n" +
                        "+1 Armor per 3 blocks in height or per 3 in depth\n" +
                        "+0.04 Speed\n" +
                        "+0.08 Speed if on sand or snow\n" +
                        "+0.005 Speed per lost hunger point\n"+
                        "+2 Mining Speed\n" +
                        "+2.5 Damage\n"+
                        "Near Ceiling boosts:\n" +
                        "• +5 Attack Damage\n" +
                        "Underground boosts:\n" +
                        "• +6 Armor\n" +
                        "+4 Entity Reach\n" +
                        "+40% Sneaky Defence"));

        registration.addIngredientInfo(new ItemStack(ModItems.STALACTITE_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Stalactite Cave Emblem Effects:\n" +
                        "+2 Fortune Fist\n" +
                        "+9 Armor\n" +
                        "+0.01 Speed\n" +
                        "+0.75 Damage\n"+
                        "Near Ceiling boosts:\n" +
                        "• +5 Attack Damage\n" +
                        "Underground boosts:\n" +
                        "• +6 Armor"));

        registration.addIngredientInfo(new ItemStack(ModItems.SPADE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Spade Emblem Effects:\n" +
                        "+1 Damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.CLOVER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Clover Emblem Effects:\n" +
                        "+0.02 Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.BLIZZARD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Blizzard Emblem Effects:\n" +
                        "Frost Ring level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.FROST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Frost Emblem Effects:\n" +
                        "+1 Frost Walker"));

        registration.addIngredientInfo(new ItemStack(ModItems.SNOWSTORM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Frost Emblem Effects:\n" +
                        "Frost Ring level 4\n" +
                        "Frost Fist level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.SPARK_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Spark Emblem Effects:\n" +
                        "+2 Static"));

        registration.addIngredientInfo(new ItemStack(ModItems.CONDUCTOR_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Conductor Emblem Effects:\n" +
                        "+7 Conductor range"));

        registration.addIngredientInfo(new ItemStack(ModItems.COILED_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Coiled Emblem Effects:\n" +
                        "Zap Ring level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.HEAVEN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Heaven Emblem Effects:\n" +
                        "-20% gravity\n"+
                        "+0.01 Speed\n"+
                        "-20% fall damage\n" +
                        "Daytime boosts:\n" +
                        "• Mining Speed +0.4\n" +
                        "Night boosts:\n" +
                        "• Speed +0.01 or +0.03\n" +
                        "• Damage +0.2 or +0.5\n" +
                        "Full moon affects power of night boosts"));

        registration.addIngredientInfo(new ItemStack(ModItems.RETURN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Return Emblem Effects:\n" +
                        "Shift + Primary ability: Return to spawn, but only works in overworld"));

        registration.addIngredientInfo(new ItemStack(ModItems.WARRIOR_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Warrior Emblem Effects:\n" +
                        "+2 Armor"));

        registration.addIngredientInfo(new ItemStack(ModItems.ROTTEN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rotten Emblem Effects:\n" +
                        "Can eat bad food without getting negative effects"));

        registration.addIngredientInfo(new ItemStack(ModItems.GOLDEN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Golden Emblem Effects:\n" +
                        "Piglins are passive\n" +
                        "Piglins guide you to the second nearest bastion"));

        registration.addIngredientInfo(new ItemStack(ModItems.ELECTRO_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Electro Emblem Effects:\n" +
                        "Electro Fist level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.GAMBLER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Gambler Emblem Effects:\n" +
                        "Shift + Secondary ability: Gamble items in hand\n" +
                        "Bonus luck with gambling per Gambler Emblem equipped"));

        registration.addIngredientInfo(new ItemStack(ModItems.SMOKE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Smoke Emblem Effects:\n" +
                        "Primary ability: Gain speed and invisibility effects"));

        registration.addIngredientInfo(new ItemStack(ModItems.EASTER_EGGBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Easter Eggblem Effects:\n" +
                        "+1% every 1t to spawn egg"));

        registration.addIngredientInfo(new ItemStack(ModItems.STEAM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Steam emblem Effects:\n" +
                        "Steam Ring level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.AURIC_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Auric emblem Effects:\n" +
                        "Steam Ring level 4\n" +
                        "Fire Ring level 3\n" +
                        "Frost Ring level 4\n" +
                        "Zap Ring level 2"));

        registration.addIngredientInfo(new ItemStack(ModItems.INDUCTION_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Induction emblem Effects:\n" +
                        "+2 Static\n" +
                        "+7 Conductor range"));

        registration.addIngredientInfo(new ItemStack(ModItems.OVERCHARGED_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Overcharged emblem Effects:\n" +
                        "Electro Fist level 4\n" +
                        "Zap Ring level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.BUBBLE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Bubble emblem Effects:\n" +
                        "Bubble Fist level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.HOURGLASS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Hourglass emblem Effects:\n" +
                        "Primary ability: Fast forward time"));

        registration.addIngredientInfo(new ItemStack(ModItems.TANK_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Tank emblem Effects:\n" +
                        "+6 Max Health\n"+
                        "+6 Armor"));

        registration.addIngredientInfo(new ItemStack(ModItems.RUTHLESS_GUARDIAN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ruthless Guardian emblem Effects:\n" +
                        "+14 Max Health\n"+
                        "+10 Armor"));

        registration.addIngredientInfo(new ItemStack(ModItems.PYRO_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Pyro emblem Effects:\n" +
                        "+1 Magma Walker"));

        registration.addIngredientInfo(new ItemStack(ModItems.SOUL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Soul emblem Effects:\n" +
                        "+1 Soul Connector\n"+
                        "Primary ability: Connect souls with target to share 50% of incoming damage; 1 target per Soul Connector level"));

        registration.addIngredientInfo(new ItemStack(ModItems.MOON_WRATH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Moon Wrath Emblem Effects:\n" +
                        "+1 Moon Wrath\n" +
                        "Secondary Ability: Spawn Moon Asteroids; 5 per Moon Wrath level"));

        registration.addIngredientInfo(new ItemStack(ModItems.ORCHID_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Orchid Emblem Effects:\n" +
                        "+2 Photosynthesis\n" +
                        "+2 Farmer Blessing"));
        registration.addIngredientInfo(new ItemStack(ModItems.ORCHID_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Orchid Emblem Effects:\n" +
                        "+4 Photosynthesis\n" +
                        "+6 Farmer Blessing"));

        registration.addIngredientInfo(new ItemStack(ModItems.CACTUS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Cactus Emblem Effects:\n" +
                        "+1 Thorns"));

        registration.addIngredientInfo(new ItemStack(ModItems.HERMES_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Hermes Emblem Effects:\n" +
                        "+30 Lung Capacity\n"+
                        "(30 lung capacity = 1 air bubble)"));
        registration.addIngredientInfo(new ItemStack(ModItems.HERMES_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Hermes Emblem Effects:\n" +
                        "+210 Lung Capacity"));

        registration.addIngredientInfo(new ItemStack(ModItems.BAMBOO_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Bamboo Emblem Effects:\n" +
                        "+1 Entity Reach"));

        registration.addIngredientInfo(new ItemStack(ModItems.IVY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ivy Emblem Effects:\n" +
                        "+10% Sneaky Defence"));

        registration.addIngredientInfo(new ItemStack(ModItems.ROSE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rose Emblem Effects:\n" +
                        "Bleeding Fist level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.IBARA_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ibara Emblem Effects:\n" +
                        "+1 Thorns\n" +
                        "Bleeding Fist level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.BLACK_ROSE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Black Rose Emblem Effects:\n" +
                        "Wither Fist level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.DRIED_ROSE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Dried Rose Emblem Effects:\n" +
                        "Wither Ring level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.WITHER_ROSE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Wither Rose Emblem Effects:\n" +
                        "Wither Fist level 1\n" +
                        "Wither Ring level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.BOUQUET_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Bouquet Emblem Effects:\n" +
                        "+2 Farmer Blessing\n" +
                        "+3 Photosynthesis\n" +
                        "Bleeding Fist level 2"));

        registration.addIngredientInfo(new ItemStack(ModItems.VINE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Vine Emblem Effects:\n" +
                        "+2 Entity Reach\n" +
                        "+20% Sneaky Defence"));

        registration.addIngredientInfo(new ItemStack(ModItems.PRICKLE_VINE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Prickle Vine Emblem Effects:\n" +
                        "+3 Entity Reach\n" +
                        "+2 Thorns\n" +
                        "+20% Sneaky Defence\n" +
                        "Bleeding Fist level 2"));

        registration.addIngredientInfo(new ItemStack(ModItems.FLOWERFIELD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Flowerfield Emblem Effects:\n" +
                        "+4 Entity Reach\n" +
                        "+2 Thorns\n" +
                        "+3 Farmer Blessing\n" +
                        "+3 Photosynthesis\n" +
                        "+30% Sneaky Defence\n" +
                        "Bleeding Fist level 2"));

        registration.addIngredientInfo(new ItemStack(ModItems.RAINFOREST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rainforest Emblem Effects:\n" +
                        "+1 Attack Speed\n" +
                        "+6 Max Health\n" +
                        "+3 Nature Healing\n" +
                        "+3 Photosynthesis"));

        registration.addIngredientInfo(new ItemStack(ModItems.DEADFOREST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Deadforest Emblem Effects:\n" +
                        "+1 Attack Speed\n" +
                        "+6 Max Health\n" +
                        "+3 Nature Healing\n" +
                        "+3 Nature Healing\n" +
                        "+3 Photosynthesis\n" +
                        "Wither Fist level 1\n" +
                        "Wither Ring level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.FLOWERFOREST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Flower Forest Emblem Effects:\n" +
                        "+5 Entity Reach\n" +
                        "+40% Sneaky Defence\n" +
                        "+2 Thorns\n" +
                        "+4 Photosynthesis\n" +
                        "+4 Farmer Blessing\n" +
                        "+10 Max Health\n" +
                        "+4 Nature Regen\n" +
                        "+1.5 Attack Speed\n" +
                        "Bleeding Fist level 2\n" +
                        "Wither Fist level 2\n" +
                        "Wither Ring level 2\n" +
                        "Makes Emblems that require night, to work during day too\n" +
                        "Makes Emblems that require day, to work at night too"));

        // ELEMENTAL EMBLEMS
        registration.addIngredientInfo(new ItemStack(ModItems.FIRE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Fire Emblem Effects:\n" +
                        "Fire Fist level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.ICE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ice Emblem Effects:\n" +
                        "Frost Fist level 2"));

        registration.addIngredientInfo(new ItemStack(ModItems.WATER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Water Emblem Effects:\n" +
                        "+1 Respiration\n" +
                        "+1 Swim Speed\n" +
                        "+1 Underwater Mining Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.THUNDER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Thunder Emblem Effects:\n" +
                        "+3 Static\n" +
                        "+10 Conductor range\n" +
                        "Zap Ring level 2\n" +
                        "Electro Fist level 5\n" +
                        "Primary Ability: Lightning Strike"));

        // PROFESSION EMBLEMS
        registration.addIngredientInfo(new ItemStack(ModItems.FARMER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Farmer Emblem Effects:\n" +
                        "+1 Farmer Blessing"));
        registration.addIngredientInfo(new ItemStack(ModItems.FARMER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Farmer Emblem Effects:\n" +
                        "+3 Farmer Blessing"));

        registration.addIngredientInfo(new ItemStack(ModItems.BUILDER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Builder Emblem Effects:\n" +
                        "+2 Block place range"));

        registration.addIngredientInfo(new ItemStack(ModItems.MERCHANT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Merchant Emblem Effects:\n" +
                        "10% Cheaper emerald prices"));

        registration.addIngredientInfo(new ItemStack(ModItems.BARTERER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Barterer Emblem Effects:\n" +
                        "10% Cheaper prices"));

        // SPECIAL & OTHER EMBLEMS
        registration.addIngredientInfo(new ItemStack(ModItems.HEART_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Heart Emblem Effects:\n" +
                        "+4 Max Health\n" +
                        "Low health boosts:\n" +
                        "• <100% HP: Speed +0.01\n" +
                        "• <75% HP: Damage +1.0\n" +
                        "• <50% HP: Attack Speed +1.0\n" +
                        "• <25% HP: +3 Nature Regen"));

        registration.addIngredientInfo(new ItemStack(ModItems.POOP_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Poop Emblem Effects:\n" +
                        "Secondary Ability: Poop"));

        registration.addIngredientInfo(new ItemStack(ModItems.MIRROR_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mirror Emblem Effects:\n" +
                        "+0.6 Damage Reflection\n" +
                        "Primary Ability: Swap places with target"));

        registration.addIngredientInfo(new ItemStack(ModItems.DANDELION_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Dandelion Emblem Effects:\n" +
                        "Primary Ability: Gain slow falling"));

        registration.addIngredientInfo(new ItemStack(ModItems.MIST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mist Emblem Effects:\n" +
                        "Drowning Ring level 3"));

        registration.addIngredientInfo(new ItemStack(ModItems.TIDE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Tide Emblem Effects:\n" +
                        "Tidal Fist level 1; Pulls mobs towards target, Moon Favor empowers it"));

        registration.addIngredientInfo(new ItemStack(ModItems.SPRINKLER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sprinkler Emblem Effects:\n" +
                        "Shorter burn time\n"+
                        "Makes Emblems that require rain, to work without it\n"+
                        "+5 Farmer Blessing's range"));

        registration.addIngredientInfo(new ItemStack(ModItems.TEAR_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Tear Emblem Effects:\n" +
                        "+1 Schadenfreude; Gain speed and damage boost while constantly killing enemies"));

        registration.addIngredientInfo(new ItemStack(ModItems.PUDDLE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Puddle Emblem Effects:\n" +
                        "Secondary ability: Spawn 2 Pufferfishes"));

        registration.addIngredientInfo(new ItemStack(ModItems.NIGHT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Night Emblem Effects:\n" +
                        "Night boosts:\n" +
                        "• Speed +0.01 or +0.03\n" +
                        "• Damage +0.2 or +0.5\n" +
                        "Full moon affects power of night boosts"));

        registration.addIngredientInfo(new ItemStack(ModItems.FLUORESCENT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Florescent Emblem Effects:\n" +
                        "Night vision"));

        registration.addIngredientInfo(new ItemStack(ModItems.DUST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Dust Emblem Effects:\n" +
                        "+0.005 Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.COMET_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Comet Emblem Effects:\n" +
                        "Shoots 3 comet shards periodically"));
        registration.addIngredientInfo(new ItemStack(ModItems.COMET_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Comet Emblem Effects:\n" +
                        "Shoots 8 comet shards rapidly"));

        registration.addIngredientInfo(new ItemStack(ModItems.MAGMA_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Magma Emblem Effects:\n" +
                        "Fire Resistance"));

        registration.addIngredientInfo(new ItemStack(ModItems.PETRIFIED_DANDELION_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Petrified Dandelion Emblem Effects:\n" +
                        "Slow Falling"));

        registration.addIngredientInfo(new ItemStack(ModItems.PETRIFIED_VEGE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Petrified Vege Emblem Effects:\n" +
                        "Saturation"));

        registration.addIngredientInfo(new ItemStack(ModItems.DIAMONDS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Diamonds Emblem Effects:\n" +
                        "Jump Boost"));

        registration.addIngredientInfo(new ItemStack(ModItems.DAY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Day Emblem Effects:\n" +
                        "Daytime boosts:\n" +
                        "• Mining Speed +0.4"));

        registration.addIngredientInfo(new ItemStack(ModItems.SUNFLOWER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sunflower Emblem Effects:\n" +
                        "Makes Emblems that require day, to work at night too"));

        registration.addIngredientInfo(new ItemStack(ModItems.NIGHTBLOOM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Nightbloom Emblem Effects:\n" +
                        "Makes Emblems that require night, to work during day too"));

        registration.addIngredientInfo(new ItemStack(ModItems.RED_SPIDER_LILY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Red Spider Lily Emblem Effects:\n" +
                        "+10 Damage at night\n"+
                        "You burn during day"));

        registration.addIngredientInfo(new ItemStack(ModItems.BLUE_SPIDER_LILY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Blue Spider Lily Emblem Effects:\n" +
                        "+10 Damage during day\n"+
                        "You freeze at night"));

        registration.addIngredientInfo(new ItemStack(ModItems.RAIN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rain Emblem Effects:\n" +
                        "+0.03 Speed during Rain"));

        registration.addIngredientInfo(new ItemStack(ModItems.MUD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mud Emblem Effects:\n" +
                        "Muddy Fist level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.CLAY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Clay Emblem Effects:\n" +
                        "Muddy Fist level 1\n" +
                        "+0.5 Attack Damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.MARSH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Marsh Emblem Effects:\n" +
                        "Muddy Fist level 2\n"+
                        "Steam Ring level 4"));

        registration.addIngredientInfo(new ItemStack(ModItems.BOG_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Bog Emblem Effects:\n" +
                        "Drowning Ring level 3\n"+
                        "Secondary ability: Spawn 3 Pufferfishes"));

        registration.addIngredientInfo(new ItemStack(ModItems.WAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Wave Emblem Effects:\n" +
                        "+0.03 Speed during Rain\n"+
                        "+1 Respiration\n" +
                        "+1.2 Swim Speed\n" +
                        "+1 Underwater Mining Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.WHIRL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Whirl Emblem Effects:\n" +
                        "Bubble Fist level 3\n"+
                        "Tidal Fist level 3; Pulls mobs towards target, Moon Favor empowers it"));

        registration.addIngredientInfo(new ItemStack(ModItems.VORTEX_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Vortex Emblem Effects:\n" +
                        "+1 Schadenfreude; Gain speed and damage boost while constantly killing enemies\n"+
                        "+1 Damage\n" +
                        "+0.04 Moon Favor\n" +
                        "Night boosts:\n" +
                        "• Speed +0.005 or +0.015\n" +
                        "• Damage +0.1 or +0.25\n" +
                        "Full moon affects power of night boosts\n" +
                        "Bubble Fist level 3\n"+
                        "Tidal Fist level 3; Pulls mobs towards target, Moon Favor empowers it"));

        registration.addIngredientInfo(new ItemStack(ModItems.SWAMP_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Swamp Emblem Effects:\n" +
                        "Drowning Ring level 4\n"+
                        "Muddy Fist level 4\n"+
                        "Steam Ring level 4\n"+
                        "Secondary ability: Spawn 4 Pufferfishes"));

        registration.addIngredientInfo(new ItemStack(ModItems.CURRENT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Current Emblem Effects:\n" +
                        "+0.035 Speed\n"+
                        "+1 Respiration\n" +
                        "+1.3 Swim Speed\n" +
                        "+1.2 Underwater Mining Speed\n"+
                        "+5 Farmer Blessing's range"));

        registration.addIngredientInfo(new ItemStack(ModItems.MUCKMIRE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Muckmire Emblem Effects:\n" +
                        "+0.035 Speed\n"+
                        "+2 Respiration\n" +
                        "+1.4 Swim Speed\n" +
                        "+1.5 Underwater Mining Speed\n"+
                        "+7 Farmer Blessing's range\n"+
                        "Drowning Ring level 5\n"+
                        "Muddy Fist level 4\n"+
                        "Steam Ring level 5\n"+
                        "Secondary ability: Spawn 5 Pufferfishes"));

        registration.addIngredientInfo(new ItemStack(ModItems.MAELSTROM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Maelstrom Emblem Effects:\n" +
                        "+1 Schadenfreude; Gain speed and damage boost while constantly killing enemies\n"+
                        "+2 Damage\n" +
                        "+0.07 Moon Favor\n" +
                        "Night boosts:\n" +
                        "• Speed +0.01 or +0.03\n" +
                        "• Damage +0.2 or +0.5\n" +
                        "Full moon affects power of night boosts\n" +
                        "Bubble Fist level 5\n"+
                        "Tidal Fist level 5; Pulls mobs towards target, Moon Favor empowers it"));

        registration.addIngredientInfo(new ItemStack(ModItems.FORBIDDEN_OASIS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Forbidden Oasis Emblem Effects:\n" +
                        "+2 Schadenfreude; Gain speed and damage boost while constantly killing enemies\n"+
                        "+2 Damage\n" +
                        "+0.09 Moon Favor\n" +
                        "+0.04 Speed\n"+
                        "+3 Respiration\n" +
                        "+1.6 Swim Speed\n" +
                        "+2 Underwater Mining Speed\n"+
                        "+10 Farmer Blessing's range\n"+
                        "Night boosts:\n" +
                        "• Speed +0.01 or +0.03\n" +
                        "• Damage +0.2 or +0.5\n" +
                        "Full moon affects power of night boosts\n" +
                        "Shorter burn time\n"+
                        "Makes Emblems that require rain, to work without it\n"+
                        "Bubble Fist level 10\n"+
                        "Tidal Fist level 10; Pulls mobs towards target, Moon Favor empowers it\n"+
                        "Drowning Ring level 7\n"+
                        "Muddy Fist level 5\n"+
                        "Steam Ring level 7\n"+
                        "Secondary ability: Spawn 10 Pufferfishes"));

        registration.addIngredientInfo(new ItemStack(ModItems.FLORIST_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Florist Emblem Effects:\n" +
                        "Florist level 1\n" +
                        "Primary ability: Spawn 5 Flowers per florist level"));

        registration.addIngredientInfo(new ItemStack(ModItems.KNIGHT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Knight Emblem Effects:\n" +
                        "+0.5 Attack Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.GLADIATOR_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Gladiator Emblem Effects:\n" +
                        "+0.5 Attack Damage\n"+
                        "+1 Attack Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.BRANCH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Branch Emblem Effects:\n" +
                        "+3 Max Health\n"+
                        "+1 Nature Regen"));

        registration.addIngredientInfo(new ItemStack(ModItems.FERTILIZED_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Branch Emblem Effects:\n" +
                        "+0.5 Attack Speed\n"+
                        "+1 Photosynthesis"));

        registration.addIngredientInfo(new ItemStack(ModItems.BUTCHER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Butcher Emblem Effects:\n" +
                        "+1 Butcher Blessing"));

        registration.addIngredientInfo(new ItemStack(ModItems.SKIN_TRANSPLANT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Skin Transplant Emblem Effects:\n" +
                        "Swaps loot tables of a cow and rabbit"));

        registration.addIngredientInfo(new ItemStack(ModItems.SMELTER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Smelter Emblem Effects:\n" +
                        "Smelts mined ores\n" +
                        "Has a chance to set user on fire"));

        registration.addIngredientInfo(new ItemStack(ModItems.BURNER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Burner Emblem Effects:\n" +
                        "Smelts mined blocks\n" +
                        "Has a chance to set user on fire"));

        registration.addIngredientInfo(new ItemStack(ModItems.SMOKING_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Smoking Emblem Effects:\n" +
                        "Smelts killed animals"));

        registration.addIngredientInfo(new ItemStack(ModItems.ARCHER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Archer Emblem Effects:\n" +
                        "+0.15 Arrow Retrieve"));

        registration.addIngredientInfo(new ItemStack(ModItems.BOULDER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Boulder Emblem Effects:\n" +
                        "+1.5 Attack Damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.DOWNHILL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Downhill Emblem Effects:\n" +
                        "+0.002 Speed per lost hunger point"));

        registration.addIngredientInfo(new ItemStack(ModItems.DUNE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Dune Emblem Effects:\n" +
                        "+0.01 Speed\n" +
                        "+0.03 Speed if on sand\n" +
                        "+0.005 Speed per 2 lost hunger points"));

        registration.addIngredientInfo(new ItemStack(ModItems.HILL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Hill Emblem Effects:\n" +
                        "+0.01 Speed\n" +
                        "+0.03 Speed if on sand\n" +
                        "+0.003 Speed per lost hunger point\n"+
                        "+1 Armor per 20 blocks in height"));

        registration.addIngredientInfo(new ItemStack(ModItems.MOUNTAIN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mountain Emblem Effects:\n" +
                        "+0.01 Speed\n" +
                        "+0.03 Speed if on sand\n" +
                        "+0.004 Speed per lost hunger point\n"+
                        "+1 Armor per 10 blocks in height"));

        registration.addIngredientInfo(new ItemStack(ModItems.MOUNTAIN_RANGE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mountain Range Emblem Effects:\n" +
                        "+0.04 Speed\n" +
                        "+0.08 Speed if on sand or snow\n" +
                        "+0.005 Speed per lost hunger point\n"+
                        "+1 Armor per 5 blocks in height"));

        registration.addIngredientInfo(new ItemStack(ModItems.MOUNTAIN_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mountain Cave Emblem Effects:\n" +
                        "+2 Fortune Fist\n" +
                        "+5 Armor\n" +
                        "+0.75 Damage\n" +
                        "+0.02 Speed\n" +
                        "+0.04 Speed if on sand\n" +
                        "+0.003 Speed per lost hunger point\n"+
                        "+1 Armor per 10 blocks in height or per 5 in depth"));

        registration.addIngredientInfo(new ItemStack(ModItems.RADIANT_CAVE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Radiant Cave Emblem Effects:\n" +
                        "+3 Fortune Fist\n" +
                        "+10 Armor\n" +
                        "+1.5 Damage\n" +
                        "+0.02 Speed\n" +
                        "+0.04 Speed if on sand\n" +
                        "+0.003 Speed per lost hunger point\n"+
                        "+1 Armor per 10 blocks in height or per 5 in depth\n" +
                        "+3 Entity Reach\n" +
                        "+30% Sneaky Defence"));

        registration.addIngredientInfo(new ItemStack(ModItems.SISYPHUS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sisyphus Emblem Effects:\n" +
                        "+0.05 Attack Damage per 5 blocks in height\n"+
                        "-1 Max Health per 20 blocks in height"));

        registration.addIngredientInfo(new ItemStack(ModItems.STALAGMITE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Stalagmite Emblem Effects:\n" +
                        "+4 Armor\n" +
                        "Underground boosts:\n" +
                        "• +6 Armor\n"));

        registration.addIngredientInfo(new ItemStack(ModItems.STALAGNATE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Stalagmite Emblem Effects:\n" +
                        "Near Ceiling boosts:\n" +
                        "• +5 Attack Damage\n" +
                        "+4 Armor\n" +
                        "Underground boosts:\n" +
                        "• +6 Armor\n"));

        registration.addIngredientInfo(new ItemStack(ModItems.STALACTITE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Stalactite Emblem Effects:\n" +
                        "Near Ceiling boosts:\n" +
                        "• +5 Attack Damage\n"));

        registration.addIngredientInfo(new ItemStack(ModItems.ASSASSIN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Assassin Emblem Effects:\n" +
                        "+2 Attack Damage\n"+
                        "+2 Attack Speed"));

        registration.addIngredientInfo(new ItemStack(ModItems.NOTE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Note Emblem Effects:\n" +
                        "Every 3s spawns a note healing 1 hp"));

        registration.addIngredientInfo(new ItemStack(ModItems.CHORD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Chord Emblem Effects:\n" +
                        "Every 5s spawns 3 notes healing 1 hp"));

        registration.addIngredientInfo(new ItemStack(ModItems.STAFF_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Staff Emblem Effects:\n" +
                        "Every 1.5s spawns a stationary note dealing 2 damage"));

        registration.addIngredientInfo(new ItemStack(ModItems.LULLABY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Lullaby Emblem Effects:\n" +
                        "+5 Lullaby\n" +
                        "Primary ability: Heal yourself"));

        registration.addIngredientInfo(new ItemStack(ModItems.UNTUNED_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Untuned Emblem Effects:\n" +
                        "+1 Untuned\n" +
                        "(When attacking has a chance to spawn a damaging note)"));

        registration.addIngredientInfo(new ItemStack(ModItems.RHYTHM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rhythm Emblem Effects:\n" +
                        "+1 Tuned\n" +
                        "(When attacking spawns a healing note every third attack)"));

        registration.addIngredientInfo(new ItemStack(ModItems.SONG_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Song Emblem Effects:\n" +
                        "+1 Song\n" +
                        "(When attacking spawns a healing note every second critical attack)"));

        registration.addIngredientInfo(new ItemStack(ModItems.ANTHEM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Anthem Emblem Effects:\n" +
                        "+2 Anthem\n" +
                        "(Spawns 3 healing notes every second damage)"));

        registration.addIngredientInfo(new ItemStack(ModItems.INDEPENDENT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Independent Emblem Effects:\n" +
                        "+2 I-Anthem\n" +
                        "(Spawns 0-10 healing notes every second damage)"));

        registration.addIngredientInfo(new ItemStack(ModItems.PAPER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Paper Emblem Effects:\n" +
                        "Chance for projectiles to not hit you"));

        registration.addIngredientInfo(new ItemStack(ModItems.CLOTH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Cloth Emblem Effects:\n" +
                        "+0.1 Knockback Resistance"));

        registration.addIngredientInfo(new ItemStack(ModItems.SOIL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Soil Emblem Effects:\n" +
                        "+1 Rich Soil\n"+
                        "(Fortune for harvesting crops)"));

        registration.addIngredientInfo(new ItemStack(ModItems.RICH_SOIL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rich Soil Emblem Effects:\n" +
                        "+2 Rich Soil"));

        registration.addIngredientInfo(new ItemStack(ModItems.NUTRITIOUS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Nutritious Emblem Effects:\n" +
                        "+1 Nutritious Crops\n"+
                        "(+5% compost chance)"));

        registration.addIngredientInfo(new ItemStack(ModItems.PRAYERS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Prayers Emblem Effects:\n" +
                        "+50% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.ITEMIUM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Itemium Emblem Effects:\n" +
                        "+5% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.FINDERS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Finders Emblem Effects:\n" +
                        "+15% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.COIN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Coin Emblem Effects:\n" +
                        "+25% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.HAPPY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Happy Emblem Effects:\n" +
                        "+33% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.LUCKY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Lucky Emblem Effects:\n" +
                        "+66% itemium spawn chance\n"+
                        "(66% of alredy set chance eg. 10% -> 16.6% etc.)\n"+
                        "(Also works for Reality Manipulators in 10 block radius)\n(66% for Reality Manipulator is 6,6% boost)\n(Caps at 35% boost)"));

        registration.addIngredientInfo(new ItemStack(ModItems.LEPRECHAUN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Leprechauns Emblem Effects:\n" +
                        "+75% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.LOTTERY_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Lottery Emblem Effects:\n" +
                        "+90% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.TRAINED_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Trained Emblem Effects:\n" +
                        "+110% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.EXPERT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Expert Emblem Effects:\n" +
                        "+140% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.MASTER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Master Emblem Effects:\n" +
                        "+170% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.CHAMPION_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Champion Emblem Effects:\n" +
                        "+200% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.WINNER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Winner Emblem Effects:\n" +
                        "+230% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.FORESEEN_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Emblem of The Foreseen Effects:\n" +
                        "+300% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.RULE_MASTER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rule Master Emblem Effects:\n" +
                        "+260% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.THE_TRUTH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Emblem of The Truth Effects:\n" +
                        "+370% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.CREATOR_BLESSED_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Emblem of The Creator's Blessing Effects:\n" +
                        "+450% itemium spawn chance"));

        registration.addIngredientInfo(new ItemStack(ModItems.ANVIL_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Anvil Emblem Effects:\n" +
                        "+5% crafted items durability"));

        registration.addIngredientInfo(new ItemStack(ModItems.BLACKSMITH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Blacksmith Emblem Effects:\n" +
                        "+15% crafted items durability"));

        registration.addIngredientInfo(new ItemStack(ModItems.FORGER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Forger Emblem Effects:\n" +
                        "+30% crafted items durability"));

        registration.addIngredientInfo(new ItemStack(ModItems.GOD_FORGE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("God Forge Emblem Effects:\n" +
                        "+55% crafted items durability"));

        registration.addIngredientInfo(new ItemStack(ModItems.HEPHAESTUS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Hephaestus Emblem Effects:\n" +
                        "+85% crafted items durability"));

        registration.addIngredientInfo(new ItemStack(ModItems.SNOW_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Snow Emblem Effects:\n" +
                        "Can walk on powder snow"));

        registration.addIngredientInfo(new ItemStack(ModItems.APPLE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Apple Emblem Effects:\n" +
                        "+1 Leaf Luck\n"+
                        "(Rolls leaves loot table more times)"));
        registration.addIngredientInfo(new ItemStack(ModItems.APPLE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Ascended Apple Emblem Effects:\n" +
                        "+5 Leaf Luck\n"+
                        "(Rolls leaves loot table more times)"));

        registration.addIngredientInfo(new ItemStack(ModItems.MOONDEW_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Moondew Emblem Effects:\n" +
                        "+3 Farmer Blessing\n"+
                        "+1 Night Crops\n"+
                        "(Crops are affected by Farmer Blessing at night)"));

        registration.addIngredientInfo(new ItemStack(ModItems.ECLIPSE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Eclipse Emblem Effects:\n" +
                        "+3 Photosynthesis\n"+
                        "+1 Lunarsynthesis\n"+
                        "(Can photosynthesize at night)"));

        registration.addIngredientInfo(new ItemStack(ModItems.LUAU_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Luau Emblem Effects:\n" +
                        "+1 Luau\n"+
                        "(Gives random positive effect every 5 seconds)"));

        registration.addIngredientInfo(new ItemStack(ModItems.ICICLE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Icicle Emblem Effects:\n" +
                        "Secondary ability: Shoot icicles around yourself damaging everything in their path"));

        registration.addIngredientInfo(new ItemStack(ModItems.RINK_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Rink Emblem Effects:\n" +
                        "Secondary ability: Toggle ice scates whick allow you to slide on every block like on ice"));

        registration.addIngredientInfo(new ItemStack(ModItems.YUKI_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Yuki Emblem Effects:\n" +
                        "Freeze Ring level 1"));

        registration.addIngredientInfo(new ItemStack(ModItems.MAGNETITE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Magnetite Emblem Effects:\n" +
                        "+1 Magnet \n"+
                        "(Attract items in range of magnet level + 1 blocks. Cancel when crouching)"));

        registration.addIngredientInfo(new ItemStack(ModItems.ELECTROMAGNET_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Electromagnet Emblem Effects:\n" +
                        "+2 Magnet"));

        registration.addIngredientInfo(new ItemStack(ModItems.ELECTROMAGNET_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Electromagnet Emblem Effects:\n" +
                        "+1 Pulse \n"+
                        "(Primary ability: Push away mobs around you also zapping them and 1 additional mob)"));

        registration.addIngredientInfo(new ItemStack(ModItems.LIGHTNING_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Lightning Emblem Effects:\n" +
                        "+1 Lightning aspect"));

        registration.addIngredientInfo(new ItemStack(ModItems.BEAT_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Beat Emblem Effects:\n" +
                        "+1 Beat\n" +
                        "(When attacking has a chance to crit)"));

        registration.addIngredientInfo(new ItemStack(ModItems.FALSE_NOTE_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("False Note Emblem Effects:\n" +
                        "+2 Untuned"));

        registration.addIngredientInfo(new ItemStack(ModItems.RANDOM_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Random Emblem Effects:\n" +
                        "Third Emblem Ability: copy random emblem power"));

        registration.addIngredientInfo(new ItemStack(ModItems.GLIDER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Feather Emblem Effects:\n" +
                        "+20% Flight Time"));

        registration.addIngredientInfo(new ItemStack(ModItems.FEATHER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Feather Emblem Effects:\n" +
                        "+10% Flight Time"));

        registration.addIngredientInfo(new ItemStack(ModItems.WIND_CATCHER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Feather Emblem Effects:\n" +
                        "+30% Flight Time"));

        registration.addIngredientInfo(new ItemStack(ModItems.DASHER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Dasher Emblem Effects:\n" +
                        "Primary Ability: Dash during elytra flight"));

        registration.addIngredientInfo(new ItemStack(ModItems.SKY_SHREDDING_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Sky Shredding Emblem Effects:\n" +
                        "Primary Ability: Dash during elytra flight"));

        registration.addIngredientInfo(new ItemStack(ModItems.PHOENIX_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Phoenix Emblem Effects:\n" +
                        "Set enemies in range on fire on elytra takeoff"));

        registration.addIngredientInfo(new ItemStack(ModItems.PHOENIX_WING_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Phoenix Wing Emblem Effects:\n" +
                        "Set enemies in range on fire while elytra flight"));

        registration.addIngredientInfo(new ItemStack(ModItems.TRUE_PHOENIX_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("True Phoenix Emblem Effects:\n" +
                        "Set enemies in range on fire while elytra flight and on takeoff\n" +
                        "Fire Ring level 2"));

        registration.addIngredientInfo(new ItemStack(ModItems.HUMMING_BIRD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Humming Bird Emblem Effects:\n" +
                        "+50% Flight Time"));


        registration.addIngredientInfo(new ItemStack(ModItems.MOUNTAIN_WIND_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Mountain Wind Emblem Effects:\n" +
                        "+70% Flight Time"));


        registration.addIngredientInfo(new ItemStack(ModItems.WEIGHTLESS_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Weightless Emblem Effects:\n" +
                        "+100% Flight Time"));


        registration.addIngredientInfo(new ItemStack(ModItems.WING_POWER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Wing Power Emblem Effects:\n" +
                        "+200% Flight Time"));


        registration.addIngredientInfo(new ItemStack(ModItems.SKY_WANDERER_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Emblem of The Sky Wanderer Effects:\n" +
                        "Endless Flight Time"));


        registration.addIngredientInfo(new ItemStack(ModItems.MOTHER_EARTH_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Emblem of the Mother Earth Effects:\n" +
                        "+1 Mother Nature Blessing \n"+
                        "• Crops and Animals grow near you realy fast\n" +
                        "• Everything that can be composted has a 100% compost chance\n" +
                        "• Bonemeal block below you every 10 seconds, (toggleable with Third Emblem Ability)\n" +
                        "• Turn Dirt into Grass by walking on it\n"+
                        "(Range is unaffected by \"Sprinklers\")"));

        registration.addIngredientInfo(new ItemStack(ModItems.UNDERWORLD_EMBLEM.get()), VanillaTypes.ITEM_STACK,
                Component.literal("Emblem of the Unferworld Effects:\n" +
                        "+1 Fortune Fever \n"+
                        "• Every ore has guaranteed 4x drop and chance for further multiplication\n"+
                        "• Can multiply ancient debris"));

        // Add all remaining emblems following the same pattern...
        // Continue with all other emblems not listed above...
















    }
}
