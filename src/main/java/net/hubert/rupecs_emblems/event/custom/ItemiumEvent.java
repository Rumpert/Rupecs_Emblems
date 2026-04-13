package net.hubert.rupecs_emblems.event.custom;

import net.hubert.rupecs_emblems.item.custom.ItemiumItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired for various states of custom elytra usage.
 * This event is fired on the MinecraftForge.EVENT_BUS.
 */
public class ItemiumEvent extends Event {
    private final ItemiumItem itemium;

    public ItemiumEvent(ItemiumItem itemStack) {
        this.itemium = itemStack;
    }

    public ItemiumItem getItemium() {
        return itemium;
    }

}