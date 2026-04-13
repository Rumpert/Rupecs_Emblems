package net.hubert.rupecs_emblems.item;

import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum EmblemTiers {
    F("F", ChatFormatting.GREEN),
    XF("F+", ChatFormatting.GREEN),
    E("E", ChatFormatting.BLUE),
    XE("E+", ChatFormatting.BLUE),
    D("D", ChatFormatting.LIGHT_PURPLE),
    XD("D+", ChatFormatting.LIGHT_PURPLE),
    C("C", ChatFormatting.RED),
    XC("C+", ChatFormatting.RED),
    B("B", ChatFormatting.YELLOW),
    XB("B+", ChatFormatting.YELLOW),
    A("A", ChatFormatting.AQUA),
    XA("A+", ChatFormatting.AQUA),
    S("S", ChatFormatting.DARK_RED),
    XS("S+", ChatFormatting.DARK_RED),
    SS("SS", null), // <-- no static color, uses rainbow animation
    XSS("SS+", null), // <-- no static color, uses rainbow animation
    U("U", null); // <-- no static color, uses rainbow animation

    private final String name;
    private final ChatFormatting chatFormatting;

    EmblemTiers(String name, ChatFormatting displayColor){
        this.name = name;
        this.chatFormatting = displayColor;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getColor() {
        if (chatFormatting == null) return ChatFormatting.DARK_PURPLE;
        return chatFormatting;
    }

    public Component getDisplay(int tick) {
        if (chatFormatting != null) {
            return Component.literal(name).withStyle(chatFormatting);
        } else {
            // Animated rainbow for tiers without a fixed color
            return switch (name){
                case "U", "U+":
                    yield FancyText.colorSwitchAll(name, tick, new int[]{
                            0xFFFFFF,
                            0xAAAAFF,
                            0xBBBBFF
                    });
                default:
                    yield FancyText.rainbowWave(name, tick);

            };
        }
    }
}
