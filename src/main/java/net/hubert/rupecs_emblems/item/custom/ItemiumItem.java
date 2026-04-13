package net.hubert.rupecs_emblems.item.custom;

import net.hubert.rupecs_emblems.item.EmblemTypes;
import net.hubert.rupecs_emblems.item.ItemiumTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ItemiumItem extends Item {
    private final List<EmblemTypes> workingEmblemTypes;

    public ItemiumItem(Properties properties, List<EmblemTypes> workingEmblemTypes) {
        super(properties);
        this.workingEmblemTypes = workingEmblemTypes;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.RARE;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 24;
    }

    public List<EmblemTypes> getWorkingEmblemTypes() {
        return workingEmblemTypes;
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        ItemiumTiers itemiumTier = switch (getWorkingEmblemTypes().size()) {
            case 2 -> ItemiumTiers.II;
            case 3 -> ItemiumTiers.III;
            case 4 -> ItemiumTiers.IV;
            case 5 -> ItemiumTiers.V;
            default -> ItemiumTiers.I;
        };
        pTooltipComponents.add(Component.literal("Itemium tier " + itemiumTier.getName()).withStyle(itemiumTier.getColor()));
        if (pLevel == null)return;
        for (EmblemTypes emblemType : workingEmblemTypes) {
            pTooltipComponents.add(emblemType.getDisplay( workingEmblemTypes.get(workingEmblemTypes.indexOf(emblemType)).getName(), (int) pLevel.getGameTime()).withStyle(ChatFormatting.ITALIC));
        }

    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

}