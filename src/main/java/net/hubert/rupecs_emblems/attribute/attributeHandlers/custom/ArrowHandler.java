package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID)
public class ArrowHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!(event.getSource().getDirectEntity() instanceof Arrow arrow)) return;

        double chance = player.getAttributeValue(ModAttributes.ARROW_RETRIEVE.get());
        if (chance <= 0) return;

        // Find the bow/crossbow the player used
        ItemStack weapon = player.getMainHandItem();
        if (!(weapon.getItem() instanceof BowItem || weapon.getItem() instanceof CrossbowItem)) {
            weapon = player.getOffhandItem();
        }
        if (!(weapon.getItem() instanceof BowItem || weapon.getItem() instanceof CrossbowItem)) return;

        // Skip if weapon has Infinity
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, weapon) > 0) return;

        // Roll chance
        if (player.getRandom().nextFloat() < chance) {
            // Refund the actual arrow type that was fired
            if (weapon.getItem() instanceof ProjectileWeaponItem weaponItem){
                if (player.getProjectile(weaponItem.getDefaultInstance()) != ItemStack.EMPTY && !player.getProjectile(weaponItem.getDefaultInstance()).is(Items.AIR)) {
                    player.addItem(new ItemStack(player.getProjectile(weaponItem.getDefaultInstance()).getItem(), 1));
                } else {
                    player.addItem(new ItemStack(Items.ARROW, 1));
                }
            }

        }
    }


}
