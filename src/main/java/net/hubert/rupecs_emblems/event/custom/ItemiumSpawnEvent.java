package net.hubert.rupecs_emblems.event.custom;

import net.hubert.rupecs_emblems.item.custom.ItemiumItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired for various states of custom elytra usage.
 * This event is fired on the MinecraftForge.EVENT_BUS.
 */
public class ItemiumSpawnEvent extends ItemiumEvent {
    private final ItemiumItem itemium;
    private final Vec3 position;
    private final Player player;
    private final Level level;
    private final ItemEntity itemiumEntity;

    public ItemiumSpawnEvent(ItemiumItem itemStack, Vec3 position, Player player, Level level, ItemEntity itemiumEntity) {
        super(itemStack);
        this.itemium = itemStack;
        this.position = position;
        this.player = player;
        this.level = level;
        this.itemiumEntity = itemiumEntity;
    }

    public ItemiumItem getItemium() {
        return itemium;
    }

    public Vec3i getPositioni() {
        return new Vec3i((int) position.x, (int) position.y, (int) position.z);
    }
    public Vec3 getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }

    public ItemEntity getItemiumEntity() {
        return itemiumEntity;
    }
}