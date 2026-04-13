package net.hubert.rupecs_emblems.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityTravelMixin {

    @Redirect(
            method = "travel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getFriction(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)F"
            )
    )
    private float redirectGetFriction(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        float original = state.getFriction(level, pos, entity);

        if ((entity instanceof Player player
                && player.getTags().contains("rink_active"))
                || entity instanceof Boat boat && boat.getFirstPassenger() != null
                && boat.getFirstPassenger() instanceof Player playerBoat
                && playerBoat.getTags().contains("rink_active")) {
            System.out.println("bout");
            return 1.0F;
        }

        return original;
    }
}