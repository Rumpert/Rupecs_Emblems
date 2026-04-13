// net/yourmainmod/item/custom/ProxyEmblemItem.java
package net.hubert.rupecs_emblems.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProxyEmblemItem extends Item {
    public ProxyEmblemItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, 
                               List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Requires Side Mod to be installed"));
    }
    
    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Locked Emblem");
    }
}