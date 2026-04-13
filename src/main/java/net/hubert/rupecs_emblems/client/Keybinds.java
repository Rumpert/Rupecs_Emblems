package net.hubert.rupecs_emblems.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

import javax.swing.text.JTextComponent;

public class Keybinds {
    public static final Keybinds INSTANCE = new Keybinds();

    private Keybinds() {}

    private static final String CATEGORY = "key.categories." + Rupecs_Emblems.MOD_ID;

    public final KeyMapping primaryEmblemAbility = new KeyMapping(
            "key." + Rupecs_Emblems.MOD_ID +"."+ "primary_emblem_ability",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_C, -1),
            CATEGORY
    );
    public final KeyMapping secondaryEmblemAbility = new KeyMapping(
            "key." + Rupecs_Emblems.MOD_ID +"."+ "secondary_emblem_ability",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_X, -1),
            CATEGORY
    );
    public final KeyMapping thirdEmblemAbility = new KeyMapping(
            "key." + Rupecs_Emblems.MOD_ID +"."+ "third_emblem_ability",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_Z, -1),
            CATEGORY
    );
}
