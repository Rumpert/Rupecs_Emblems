package net.hubert.rupecs_emblems.item;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

public enum EmblemTypes {
    ASTRAL("ASTRAL", null),
    NATURAL("NATURAL", null),
    PLANT("PLANT", null),
    WATER("WATER", null),
    ICE("ICE", null),
    FIRE("FIRE", null),
    AIR("AIR", null),
    GROUND("GROUND", null),
    ELECTRICITY("ELECTRICITY", null),
    ELEMENTAL("ELEMENTAL", null),
    SEASONAL("SEASONAL", null),
    SPECIAL("SPECIAL", null),
    CARD("CARD", null),
    ANIMAL("ANIMAL", null),
    CELESTIAL("CELESTIAL", null),
    TECH("TECH", null),
    PROFESSION("PROFESSION", null),
    DIVINE("DIVINE", null),
    MUSIC("MUSIC", null);


    private final String name;
    private final ChatFormatting chatFormatting;

    EmblemTypes(String name, ChatFormatting displayColor){
        this.name = name;
        this.chatFormatting = displayColor;
    }

    public String getName() {
        return name;
    }


    public ChatFormatting getColor() {
        return chatFormatting;
    }

    public MutableComponent getDisplay(int tick){
        return getDisplay(name, tick);
    }
    public MutableComponent getDisplay(String text, int tick){
        MutableComponent base = Component.empty();
        if (chatFormatting != null){
            base.append(text).withStyle(chatFormatting);
        }else {
            switch (name) {
                case "ASTRAL":
                    return FancyText.colorSwitch(text, tick, new int[]{0xFFFFFF, 0xE0E0FF, 0xA0C0FF, 0x9090FF, 0x606060}).copy();
                case "NATURAL":
                    return FancyText.colorSwitch(text, tick, new int[]{0x556B2F, 0x228B22, 0x8B4513, 0x87CEEB, 0x2E8B57}).copy();
                case "PLANT":
                    return FancyText.colorSwitch(text, tick, new int[]{0x228B22, 0x32CD32, 0x6B8E23, 0xADFF2F, 0x006400}).copy();
                case "WATER":
                    return FancyText.colorSwitch(text, tick, new int[]{0x1E90FF, 0x00BFFF, 0x00FFFF}).copy();
                case "ICE":
                    return FancyText.colorSwitch(text, tick, new int[]{0xB0E0E6, 0x87CEFA, 0x00CED1, 0x1E90FF, 0xF0FFFF}).copy();
                case "FIRE":
                    return FancyText.colorSwitch(text, tick, new int[]{0xFF4500, 0xFF8C00, 0xFFD700}).copy();
                case "AIR":
                    return FancyText.colorSwitch(text, tick, new int[]{0xD3D3D3, 0xC0C0C0, 0xA9A9A9, 0xE8E8E8, 0xB0C4DE}).copy();
                case "GROUND":
                    return FancyText.colorSwitch(text, tick, new int[]{0x8B4513, 0xA0522D, 0xD2B48C, 0x6B8E23, 0x5C4033}).copy();
                case "ELECTRICITY":
                    return FancyText.colorSwitch(text, tick, new int[]{0xFFD700, 0xFFFF00, 0xFFA500, 0x00FFFF, 0xFFFFFF}).copy();
                case "SEASONAL":
                    return FancyText.colorSwitch(text, tick, new int[]{0xFF4500, 0xFF8C00, 0xFFD700, 0xADFF2F, 0x8B0000}).copy();
                case "SPECIAL":
                    return FancyText.colorSwitch(text, tick, new int[]{0xDA70D6, 0xBA55D3, 0x9370DB, 0xE6E6FA, 0xD8BFD8}).copy();
                case "CARD":
                    return FancyText.colorSwitch(text, tick, new int[]{0x2F4F4F, 0x708090, 0xC0C0C0, 0x000000, 0x4682B4}).copy();
                case "ANIMAL":
                    return FancyText.colorSwitch(text, tick, new int[]{0x228B22, 0x32CD32, 0x6B8E23, 0x8B4513, 0xA0522D}).copy();
                case "CELESTIAL":
                    return FancyText.colorSwitch(text, tick, new int[]{0x191970, 0x000080, 0x2F4F4F, 0x20B2AA, 0x7FFFD4}).copy();
                case "TECH":
                    return FancyText.colorSwitch(text, tick, new int[]{0xFFD700, 0xC0C0C0, 0x808080, 0xFFFF00, 0x1E90FF}).copy();
                case "PROFESSION":
                    return FancyText.colorSwitch(text, tick, new int[]{0x1E90FF, 0x4169E1, 0x4682B4, 0x708090, 0xB0C4DE}).copy();
                case "DIVINE":
                    return FancyText.colorSwitch(text, tick, new int[]{0x00FFFF, 0x7FFFD4, 0x40E0D0, 0xE0FFFF, 0xB0E0E6}).copy();
                case "MUSIC":
                    return FancyText.colorSwitch(text, tick, new int[]{0xFFC0CB, 0xFFB6C1, 0xFF69B4, 0xDA70D6, 0xBA55D3}).copy();
                case "ELEMENTAL":
                    return FancyText.elementalFill(text, tick).copy();
            }

        }
        return base;
    }

}
