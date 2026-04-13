package net.hubert.rupecs_emblems.item.custom;

import net.hubert.rupecs_emblems.item.EmblemClasses;
import net.hubert.rupecs_emblems.item.EmblemTiers;
import net.hubert.rupecs_emblems.item.EmblemTypes;
import net.hubert.rupecs_emblems.providers.EmblemEffectProvider;
import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.*;

public class ModEmblemItem extends Item implements ICurioItem {
    private final UUID uniqueID;
    private final String toolTip;
    private final String ascendedTooltip;
    private String ID;
    private final EmblemTiers emblemTier;
    private final List<EmblemTypes> emblemType;
    private final Map<EmblemClasses, Integer> classLevels;
    private boolean ascended = false;


    public ModEmblemItem(Properties properties, EmblemTiers tier, List<EmblemTypes> types, Map<EmblemClasses, Integer> classLevels, String tooltip, String ascendedTooltip) {
        super(properties);
        this.toolTip = tooltip;
        this.uniqueID = UUID.randomUUID();
        this.emblemTier = tier;
        this.emblemType = types;
        this.ascendedTooltip = ascendedTooltip;

        // Create a new map instead of modifying the input
        Map<EmblemClasses, Integer> finalMap = new HashMap<>(classLevels);

        // Fill in missing classes with level 0
        for (EmblemClasses emblemClass : EmblemClasses.values()) {
            if (!finalMap.containsKey(emblemClass)) {
                finalMap.put(emblemClass, 0);
            }
        }
        this.classLevels = finalMap;
    }
    public ModEmblemItem(Properties properties, EmblemTiers tier, List<EmblemTypes> types, Map<EmblemClasses, Integer> classLevels, String tooltip) {
        super(properties);
        this.toolTip = tooltip;
        this.uniqueID = UUID.randomUUID();
        this.emblemTier = tier;
        this.emblemType = types;
        this.ascendedTooltip = tooltip;

        // Create a new map instead of modifying the input
        Map<EmblemClasses, Integer> finalMap = new HashMap<>(classLevels);

        // Fill in missing classes with level 0
        for (EmblemClasses emblemClass : EmblemClasses.values()) {
            if (!finalMap.containsKey(emblemClass)) {
                finalMap.put(emblemClass, 0);
            }
        }
        this.classLevels = finalMap;
    }
    public ModEmblemItem(Properties properties, EmblemTiers tier, List<EmblemTypes> types, String tooltip) {
        super(properties);
        this.toolTip = tooltip;
        uniqueID = UUID.randomUUID(); // Assign a unique ID
        emblemTier = tier;
        emblemType = types;
        this.classLevels = new HashMap<>();
        this.ascendedTooltip = tooltip;

    }

    public ModEmblemItem(Properties properties, EmblemTiers tier, EmblemTypes type, String tooltip) {
        super(properties);
        this.toolTip = tooltip;
        this.classLevels = new HashMap<>();
        uniqueID = UUID.randomUUID(); // Assign a unique ID
        emblemTier = tier;
        emblemType = List.of(type);
        this.ascendedTooltip = tooltip;

    }

    public ModEmblemItem(Properties properties, String tooltip) {
        super(properties);
        this.toolTip = tooltip;
        this.classLevels = new HashMap<>();
        uniqueID = UUID.randomUUID(); // Assign a unique ID
        emblemTier = EmblemTiers.F;
        emblemType = List.of(EmblemTypes.NATURAL);
        this.ascendedTooltip = tooltip;

    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        ensureSlotsTag(stack);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        ensureSlotsTag(stack);
        return stack;
    }

    private void ensureSlotsTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("ascended")) {
            tag.putInt("ascended", 0);
        }
    }

    public int getSlots(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getInt("ascended") < 0) {
            tag.putInt("ascended", 0);
        }
        return tag.getInt("ascended");
    }
    public boolean isAscended(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getInt("ascended") < 0) {
            tag.putInt("ascended", 0);
        }
        ascended = tag.getInt("ascended") > 0;
        return tag.getInt("ascended") > 0;
    }
    public boolean isAscended() {
        return ascended;
    }

    public String getToolTip() {
        return toolTip;
    }

    public UUID getUniqueID() {
        try {
            return UUID.nameUUIDFromBytes((uniqueID.toString() + "-" + ID).getBytes());
        } catch (Exception e) {
            System.err.println("Failed to generate UUID: " + e);
            e.printStackTrace();
            return uniqueID; // fallback
        }
    }

    public EmblemTiers getEmblemTier() {
        return emblemTier;
    }

    public List<EmblemTypes> getEmblemTypes() {
        return emblemType;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }


    @Override
    public Component getName(ItemStack pStack) {
        return super.getName(pStack).copy().withStyle(emblemTier.getColor());
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ID = slotContext.index() + slotContext.identifier();if (isAscended(stack)){
            EmblemEffectProvider.provideConstantAscendedEmblemEffect(this, slotContext.entity());
        }else {
            EmblemEffectProvider.provideConstantEmblemEffect(this, slotContext.entity());
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    public String getID() {
        return ID;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.RARE;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ID = slotContext.index() + slotContext.identifier();
        if (isAscended(stack)){
            EmblemEffectProvider.provideOnEquipAscendedEmblemEffect(this, slotContext.entity());
        }else {
            EmblemEffectProvider.provideOnEquipEmblemEffect(this, slotContext.entity());
        }


        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ID = slotContext.index() + slotContext.identifier();
        EmblemEffectProvider.provideOnUnequipEmblemEffect(this, slotContext.entity());

        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        // Create emblem types line

        MutableComponent typesComponent = Component.literal("");
        boolean firstType = true;

        if (pLevel == null) return;
        if (isAscended(pStack)){
            MutableComponent ascendComponent = FancyText.colorSwitchAll("ASCENDED", (int) pLevel.getGameTime(), new int[]{
                    0xFFFF00,
                    0xAAAAFF,
                    0xBBBBFF}).copy();
            pTooltipComponents.add(ascendComponent);
        }
        for (EmblemTypes type : emblemType) {
            if (!firstType) {
                typesComponent.append(Component.literal(", ").withStyle(ChatFormatting.WHITE));
            }
            typesComponent.append(type.getDisplay((int) pLevel.getGameTime()));
            firstType = false;
        }

        // Insert types after the first line (usually display name)
        if (pTooltipComponents.size() > 1) {
            pTooltipComponents.add(1, typesComponent);
        } else {
            pTooltipComponents.add(typesComponent);
        }

        // Add tier information
        if (pLevel != null) {
            int tick = (int) pLevel.getGameTime();
            MutableComponent tierComponent = Component.literal("Tier: ");

            if (emblemTier == EmblemTiers.SS || emblemTier == EmblemTiers.XSS) {
                tierComponent = FancyText.rainbowWave("Tier: ", tick).copy()
                        .append(emblemTier.getDisplay(tick));
            } else if (emblemTier == EmblemTiers.U) {
                tierComponent = FancyText.colorSwitchAll("Tier: ", tick, new int[]{
                        0xFFFFFF,
                        0xAAAAFF,
                        0xBBBBFF}).copy().append(emblemTier.getDisplay(tick));
            } else {
                tierComponent.withStyle(emblemTier.getColor())
                        .append(emblemTier.getDisplay(tick));
            }
            pTooltipComponents.add(tierComponent);
        }

        if (Screen.hasShiftDown()) {
            if (isAscended(pStack)){
                pTooltipComponents.add(Component.literal(ascendedTooltip));
            }else {
                pTooltipComponents.add(Component.literal(toolTip));
            }
        }else {
            pTooltipComponents.add(Component.literal("[Shift] for description").withStyle(ChatFormatting.DARK_GRAY));
        }

        if (Screen.hasAltDown()) {
            for (EmblemClasses emblemClass : classLevels.keySet()) {

                pTooltipComponents.add(Component.literal(emblemClass.getName() + ": " + "★".repeat(classLevels.get(emblemClass)) + "☆".repeat(3 - classLevels.get(emblemClass))).withStyle(emblemClass.getColor()));
            }
        } else {
            pTooltipComponents.add(Component.literal("[Alt] for stats").withStyle(ChatFormatting.DARK_GRAY));
        }



    }

}
