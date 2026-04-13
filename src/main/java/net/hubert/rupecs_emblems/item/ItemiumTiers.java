package net.hubert.rupecs_emblems.item;

import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum ItemiumTiers {
    I("I", ChatFormatting.GREEN),
    II("II", ChatFormatting.BLUE),
    III("III", ChatFormatting.GOLD),
    IV("IV", ChatFormatting.RED),
    V("V", ChatFormatting.DARK_PURPLE);

    private final String name;
    private final ChatFormatting chatFormatting;

    ItemiumTiers(String name, ChatFormatting displayColor){
        this.name = name;
        this.chatFormatting = displayColor;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getColor() {
        return chatFormatting;
    }

}
