package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VillagerTradeHandler {

    @SubscribeEvent
    public static void onPlayerInteraction(PlayerInteractEvent.EntityInteract event){
        if (event.getTarget() instanceof Villager villager) {
            Player player = event.getEntity();
            var merchantAttr = player.getAttribute(ModAttributes.MERCHANT.get());
            var bartererAttr = player.getAttribute(ModAttributes.BARTERER.get());
            if ((merchantAttr == null) && (bartererAttr == null)) return;
            List<MerchantOffer> offers = villager.getOffers();
            for (MerchantOffer offer : offers) {
                if (bartererAttr.getValue() <=0 ) continue;
                ItemStack baseCostA = offer.getBaseCostA();
                int basePrice = baseCostA.getCount();
                // Calculate a straight 10% per level discount
                int discountedPrice = (int) Math.ceil(Math.min(basePrice * (1 - 0.1 * bartererAttr.getValue()), basePrice - 1));

                // Ensure discountedPrice is at least 1 emerald to avoid 0 cost
                discountedPrice = Math.max(discountedPrice, 1);

                int specialPriceDiff = discountedPrice - basePrice;
                offer.setSpecialPriceDiff(specialPriceDiff);
            }
            for (MerchantOffer offer : offers) {
                if (bartererAttr.getValue()+merchantAttr.getValue() <=0 ) continue;
                ItemStack baseCostA = offer.getBaseCostA();
                if (baseCostA.getItem() == Items.EMERALD) {
                    int basePrice = baseCostA.getCount();

                    // Calculate a straight 10% per level discount
                    int discountedPrice = (int) Math.ceil(Math.min(basePrice * (1 - 0.1 * (merchantAttr.getValue() + bartererAttr.getValue())), basePrice - 1));

                    // Ensure discountedPrice is at least 1 emerald to avoid 0 cost
                    discountedPrice = Math.max(discountedPrice, 1);

                    int specialPriceDiff = discountedPrice - basePrice;
                    offer.setSpecialPriceDiff(specialPriceDiff);
                }
            }
        }
    }

}
