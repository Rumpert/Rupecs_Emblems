package net.hubert.rupecs_emblems.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FancyText {

    public static Component rainbowWave(String text, int tick) {
        MutableComponent base = Component.empty();
        int len = text.length();

        // Smooth color transition using sine waves
        float time = tick * 1.5f; // Adjust speed here

        // Create RGB values using sine waves with phase offsets
        int r = (int)(127 * Math.sin(time * 0.1f) + 128);
        int g = (int)(127 * Math.sin(time * 0.1f + 2.0f) + 128);
        int b = (int)(127 * Math.sin(time * 0.1f + 4.0f) + 128);

        int color = (r << 16) | (g << 8) | b;

        for (int i = 0; i < len; i++) {
            base.append(Component.literal(String.valueOf(text.charAt(i)))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
        }

        return base;
    }

    public static Component rainbowWave(Component component, int tick){
        return rainbowWave(component.getString(), tick);
    }
    public static Component fallingStar(Component component, int tick, RandomSource random, int starR, int starG, int starB, int bgR, int bgG, int bgB) {
        return fallingStar(component.getString(), tick, random, starR, starG, starB, bgR, bgG, bgB);
    }

    public static Component fallingStar(String text, int tick, RandomSource random,
                                        int starR, int starG, int starB,
                                        int bgR, int bgG, int bgB) {
        MutableComponent base = Component.empty();
        int len = text.length();
        if (len == 0) return base;

        int cycleLength = 30; // ticks per star
        int currentCycle = tick / cycleLength;
        float cycleProgress = (tick % cycleLength) / (float)cycleLength;

        int starColor = (starR << 16) | (starG << 8) | starB;
        int bgColor = (bgR << 16) | (bgG << 8) | bgB;

        // Current star
        float starPos1 = cycleProgress * (len + 3) - 1.5f;
        // Previous star, still fading
        float starPos2 = (cycleProgress + 1f) * (len + 3) - 1.5f;

        for (int i = 0; i < len; i++) {
            int color = bgColor;

            // Add contribution from current star
            color = blendColors(color, computeStarColor(i, starPos1, starColor, bgColor));
            // Add contribution from trailing star (previous cycle)
            color = blendColors(color, computeStarColor(i, starPos2, starColor, bgColor));

            base.append(Component.literal(String.valueOf(text.charAt(i)))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
        }

        return base;
    }


    private static int computeStarColor(int index, float starPos, int starColor, int bgColor) {
        float distance = Math.abs(index - starPos);

        if (distance < 0.5f) {
            return starColor; // star core
        } else if (distance < 5f) {
            float normalized = (distance - 0.5f) / 4.5f;
            float intensity = (float) Math.exp(-normalized * 3f);
            return lerpColor(bgColor, starColor, intensity);
        }
        return bgColor;
    }

    private static int lerpColor(int c1, int c2, float t) {
        int r1 = (c1 >> 16) & 0xFF, g1 = (c1 >> 8) & 0xFF, b1 = c1 & 0xFF;
        int r2 = (c2 >> 16) & 0xFF, g2 = (c2 >> 8) & 0xFF, b2 = c2 & 0xFF;
        int r = (int)(r1 + (r2 - r1) * t);
        int g = (int)(g1 + (g2 - g1) * t);
        int b = (int)(b1 + (b2 - b1) * t);
        return (r << 16) | (g << 8) | b;
    }

    private static int blendColors(int c1, int c2) {
        int r = Math.max((c1 >> 16) & 0xFF, (c2 >> 16) & 0xFF);
        int g = Math.max((c1 >> 8) & 0xFF, (c2 >> 8) & 0xFF);
        int b = Math.max(c1 & 0xFF, c2 & 0xFF);
        return (r << 16) | (g << 8) | b;
    }
    private static int dimColor(int color, float factor) {
        int r = (int)(((color >> 16) & 0xFF) * factor);
        int g = (int)(((color >> 8) & 0xFF) * factor);
        int b = (int)((color & 0xFF) * factor);
        return (r << 16) | (g << 8) | b;
    }

    private static int brightenColor(int color, float factor) {
        int r = Math.min(255, (int)(((color >> 16) & 0xFF) * factor));
        int g = Math.min(255, (int)(((color >> 8) & 0xFF) * factor));
        int b = Math.min(255, (int)((color & 0xFF) * factor));
        return (r << 16) | (g << 8) | b;
    }
    public static Component prismSwitch(String text, int tick) {
        MutableComponent base = Component.empty();
        int len = text.length();

        // Define a palette of colors to switch between
        int[] colors = new int[] {
                0xFF0000, // Red
                0xFF7F00, // Orange
                0xFFFF00, // Yellow
                0x00FF00, // Green
                0x0000FF, // Blue
                0x4B0082, // Indigo
                0x8F00FF  // Violet
        };

        // Decide which color to use for each character based on tick
        for (int i = 0; i < len; i++) {
            // Shift each letter by its index for variation
            int colorIndex = (tick / 5 + i) % colors.length; // changes every 5 ticks
            int color = colors[colorIndex];

            base.append(Component.literal(String.valueOf(text.charAt(i)))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
        }

        return base;
    }

    public static Component prismSwitch(Component component, int tick){
        return prismSwitch(component.getString(), tick);
    }
    public static Component prismFill(String text, int tick) {
        MutableComponent base = Component.empty();
        int len = text.length();

        // Define a palette of colors
        int[] colors = new int[] {
                0xFF0000, // Red
                0xFF7F00, // Orange
                0xFFFF00, // Yellow
                0x00FF00, // Green
                0x0000FF, // Blue
                0x4B0082, // Indigo
                0x8F00FF  // Violet
        };
        int paletteLength = colors.length;

        // Pick current color based on tick (changes every full text cycle)
        int colorIndex = (tick / len) % paletteLength;
        int color = colors[colorIndex];

        // Fill text progressively
        int filled = tick % len; // number of letters filled with color

        for (int i = 0; i < len; i++) {
            if (i <= filled) {
                // Filled part: colored
                base.append(Component.literal(String.valueOf(text.charAt(i)))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
            } else {
                // Remaining part: default color (white)
                base.append(Component.literal(String.valueOf(text.charAt(i)))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(colorIndex > 0 ? colors[colorIndex - 1] : colors[colors.length-1]))));
            }
        }

        return base;
    }

    public static Component prismFill(Component component, int tick) {
        return prismFill(component.getString(), tick);
    }
    public static Component elementalFill(String text, int tick) {
        MutableComponent base = Component.empty();
        int len = text.length();
        if (len == 0) return base;

        // Define a palette of colors
        int[] colors = new int[]{
                0xFF0000, // Red
                0xFFFF00, // Yellow
                0x00FF00, // Green
                0x0000FF  // Blue
        };
        int paletteLength = colors.length;

        // Overall progress through the palette
        float paletteProgress = (tick % (len * paletteLength)) / (float)len;
        int baseIndex = (int)paletteProgress % paletteLength;
        int nextIndex = (baseIndex + 1) % paletteLength;

        // Interpolation factor (0 → base color, 1 → next color)
        float t = (tick % len) / (float)len;

        // Interpolated "active" color
        int activeColor = lerpColor(colors[baseIndex], colors[nextIndex], t);

        // Progressive fill
        int filled = tick % len;

        for (int i = 0; i < len; i++) {
            if (i <= filled) {
                base.append(Component.literal(String.valueOf(text.charAt(i)))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(activeColor))));
            } else {
                // Background text uses the previous palette color (not interpolated)
                base.append(Component.literal(String.valueOf(text.charAt(i)))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(colors[baseIndex]))));
            }
        }

        return base;
    }

    public static Component elementalFill(Component component, int tick) {
        return elementalFill(component.getString(), tick);
    }
    public static Component colorSwitch(String text, int tick, int[] colors) {
        MutableComponent base = Component.empty();
        int len = text.length();
        if (len == 0 || colors == null || colors.length == 0) return base;

        int paletteLength = colors.length;

        // Overall progress through the palette
        float paletteProgress = (tick % (len * paletteLength)) / (float)len;
        int baseIndex = (int)paletteProgress % paletteLength;
        int nextIndex = (baseIndex + 1) % paletteLength;

        // Interpolation factor (0 → base color, 1 → next color)
        float t = (tick % len) / (float)len;

        // Interpolated "active" color
        int activeColor = lerpColor(colors[baseIndex], colors[nextIndex], t);

        // Progressive fill
        int filled = tick % len;

        for (int i = 0; i < len; i++) {
            if (i <= filled) {
                base.append(Component.literal(String.valueOf(text.charAt(i)))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(activeColor))));
            } else {
                // Background text uses the current base color
                base.append(Component.literal(String.valueOf(text.charAt(i)))
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(colors[baseIndex]))));
            }
        }

        return base;
    }
    public static Component colorSwitchAll(String text, int tick, int[] colors) {
        if (text.isEmpty() || colors == null || colors.length == 0) {
            return Component.literal(text);
        }

        int paletteLength = colors.length;

        // cycle through palette
        float progress = (tick % (paletteLength * 20)) / 20.0f; // 20 ticks per color
        int baseIndex = (int) progress % paletteLength;
        int nextIndex = (baseIndex + 1) % paletteLength;

        float t = progress - (int) progress;

        int color = lerpColor(colors[baseIndex], colors[nextIndex], t);

        return Component.literal(text)
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color)));
    }
    public static Component colorSwitch(Component component, int tick, int[] colors) {
        return colorSwitch(component.getString(), tick, colors);
    }

    public static Component randomDots(Component component, int tick, RandomSource random, int bgR, int bgG, int bgB, int dotR, int dotG, int dotB) {
        return randomDots(component.getString(), tick, random, bgR, bgG, bgB, dotR, dotG, dotB);
    }

    public static Component randomDots(String text, int tick, RandomSource random, int bgR, int bgG, int bgB, int dotR, int dotG, int dotB) {
        MutableComponent base = Component.empty();
        int len = text.length();

        if (len == 0) return base;

        int bgColor = (bgR << 16) | (bgG << 8) | bgB;
        int dotColor = (dotR << 16) | (dotG << 8) | dotB;

        int lifetime = 20; // ticks a sparkle lasts

        for (int i = 0; i < len; i++) {
            int charColor = bgColor;

            // Use hash to decide when this char "sparks"
            int seed = i * 9973; // unique per char
            int cycle = (tick + seed) / lifetime;

            // Every few cycles, trigger a sparkle
            if (((cycle * 31 + seed) & 0xFF) < 15) { // 15/256 chance
                int age = (tick + seed) % lifetime;

                // Fade in/out curve (0 → 1 → 0)
                float fade = (float)Math.sin((age / (float)lifetime) * Math.PI);

                charColor = lerpColor(bgColor, dotColor, fade);
            }

            base.append(Component.literal(String.valueOf(text.charAt(i)))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(charColor))));
        }

        return base;
    }





}
