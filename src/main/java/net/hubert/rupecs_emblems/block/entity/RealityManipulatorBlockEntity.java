package net.hubert.rupecs_emblems.block.entity;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.recipe.RealityManipulatorRecipe;
import net.hubert.rupecs_emblems.screen.RealityManipulatorMenu;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RealityManipulatorBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(4);
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int OUTPUT_SLOT = 3;


    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 40;
    private int craftChance;
    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();
    public RealityManipulatorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.REALITY_MANIPULATOR.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> RealityManipulatorBlockEntity.this.progress;
                    case 1 -> RealityManipulatorBlockEntity.this.maxProgress;
                    case 2 -> RealityManipulatorBlockEntity.this.craftChance;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> RealityManipulatorBlockEntity.this.progress = pValue;
                    case 1 -> RealityManipulatorBlockEntity.this.maxProgress = pValue;
                    case 2 -> RealityManipulatorBlockEntity.this.craftChance = pValue;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }
    private void updateCraftChance() {
        Optional<RealityManipulatorRecipe> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            float chance = recipe.get().getSuccessChance();
            this.craftChance = (int) (chance * 100); // Convert to percentage (0-100)
        } else {
            this.craftChance = 0; // No recipe = 0% chance
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.rupecs_emblems.reality_manipulator");
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return itemHandlerLazyOptional.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazyOptional.invalidate();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new RealityManipulatorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        pTag.putInt("reality_manipulator_progress", progress);
        pTag.putInt("craft_chance", craftChance); // Save craft chance
        super.saveAdditional(pTag);
    }


    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("reality_manipulator_progress");
        craftChance = pTag.getInt("craft_chance"); // Load craft chance
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        updateCraftChance();


        if (hasRecipe()){
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);
            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<RealityManipulatorRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        RealityManipulatorRecipe actualRecipe = recipe.get();
        ItemStack result = actualRecipe.getResultItem(null);

        // Use the recipe's success chance instead of hardcoded 0.17
        float bonusLuck = 0;

        if (level != null){
            Player player = level.getNearestPlayer(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 10, false);
            if (player != null) {
                bonusLuck = Math.min(((float) player.getAttributeValue(ModAttributes.LUCKY.get()) / 10), 0.35f);
            }
        }
        if (level.random.nextFloat() < actualRecipe.getSuccessChance() * (1+bonusLuck)) {
            // Success - add the result item
            ItemStack currentOutput = this.itemStackHandler.getStackInSlot(OUTPUT_SLOT);

            if (currentOutput.isEmpty()) {
                this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, result.copy());
            } else if (currentOutput.getItem() == result.getItem()) {
                currentOutput.grow(result.getCount());
            }
            if (level != null) {
                level.playSound(null, worldPosition,
                        SoundEvents.EXPERIENCE_ORB_PICKUP,
                        SoundSource.BLOCKS,
                        0.5f, 1.0f);
            }

            // Only consume ingredients on success
            this.itemStackHandler.extractItem(INPUT_SLOT_1, 1, false);
            this.itemStackHandler.extractItem(INPUT_SLOT_2, 1, false);
            this.itemStackHandler.extractItem(INPUT_SLOT_3, 1, false);
        } else {
            // Failure - consume ingredients but don't produce output
            this.itemStackHandler.extractItem(INPUT_SLOT_1, 1, false);
            this.itemStackHandler.extractItem(INPUT_SLOT_2, 1, false);
            this.itemStackHandler.extractItem(INPUT_SLOT_3, 1, false);

            // Optional: Add failure effects here (sound, particles, etc.)
            if (level != null) {
                level.playSound(null, worldPosition,
                        SoundEvents.ITEM_BREAK,
                        SoundSource.BLOCKS,
                        0.5f, 1.0f);
            }
        }
        updateCraftChance();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RealityManipulatorRecipe> recipe = getCurrentRecipe();


        if (recipe.isEmpty()) return false;
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<RealityManipulatorRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.setItem(i, this.itemStackHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(RealityManipulatorRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
