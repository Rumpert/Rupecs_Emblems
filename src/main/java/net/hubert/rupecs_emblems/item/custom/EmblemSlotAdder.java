package net.hubert.rupecs_emblems.item.custom;

import net.hubert.rupecs_emblems.util.FancyText;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmblemSlotAdder extends Item {
    private final int DEFAULT_SLOTS;
    public final String EMBLEM_SLOT_ID;

    public EmblemSlotAdder(Properties properties, int defaultLimit, String slotId) {
        super(properties);
        DEFAULT_SLOTS = defaultLimit;
        EMBLEM_SLOT_ID = slotId;
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
        if (!tag.contains("slots")) {
            tag.putInt("slots", DEFAULT_SLOTS);
        }
    }

    public int getSlots(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getInt("slots") < DEFAULT_SLOTS) {
            tag.putInt("slots", DEFAULT_SLOTS);
        }
        return tag.getInt("slots");
    }

    public void setSlots(ItemStack stack, int value) {
        stack.getOrCreateTag().putInt("slots", value);
    }
    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.EPIC;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.sidedSuccess(stack, true);
        }

        AtomicBoolean success = new AtomicBoolean(false);

        player.getCapability(CuriosCapability.INVENTORY).ifPresent(curios ->
                curios.getStacksHandler(EMBLEM_SLOT_ID).ifPresent(stacksHandler -> {
                    int currentSlots = stacksHandler.getSlots();

                    int slotsToAdd = getSlots(stack);
                    if (slotsToAdd <= 0) slotsToAdd = 1;

                    if (slotsToAdd > currentSlots) {

                        stacksHandler.grow(1);
                        success.set(true);
                        stack.shrink(1);
                        level.playSound(null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.EXPERIENCE_ORB_PICKUP,
                                SoundSource.PLAYERS,
                                1.0F,
                                1.0F
                        );

                    }

                })
        );


        return success.get() ?
                InteractionResultHolder.consume(stack) :
                InteractionResultHolder.fail(stack);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pLevel == null) return;
        String tier = switch (EMBLEM_SLOT_ID){
            case "f_tier_emblem": yield " F";
            case "e_tier_emblem": yield " E";
            case "d_tier_emblem": yield " D";
            case "c_tier_emblem": yield " C";
            case "b_tier_emblem": yield " B";
            case "a_tier_emblem": yield " A";
            case "s_tier_emblem": yield " S";
            case "ss_tier_emblem": yield " SS";
            default: yield "";
        };
        pTooltipComponents.add(Component.literal("Increase"+tier+" Emblem slots by 1 up to: " + (getSlots(pStack)) + " slots total").withStyle(ChatFormatting.GREEN));

    }
}