package net.hubert.rupecs_emblems.item;

import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum EmblemClasses {
    DEFENCE("DEFENCE", ChatFormatting.BLUE),
    OFFENCE("OFFENCE", ChatFormatting.RED),
    PASSIVE("PASSIVE", ChatFormatting.GREEN),
    ACTIVE("ACTIVE", ChatFormatting.YELLOW);


    private final String name;
    private final ChatFormatting chatFormatting;

    EmblemClasses(String name, ChatFormatting displayColor){
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
