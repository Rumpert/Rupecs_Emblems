package net.hubert.rupecs_emblems.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntherealSelectorRecipe implements Recipe<SimpleContainer> {
    private final List<IngredientWithNbt> ingredientsWithNbt;
    private final ItemStack output;
    private final ResourceLocation id;

    public EntherealSelectorRecipe(List<IngredientWithNbt> ingredientsWithNbt, ItemStack output, ResourceLocation id) {
        this.ingredientsWithNbt = ingredientsWithNbt;
        this.output = output;
        this.id = id;
    }

    public List<IngredientWithNbt> getIngredientsWithNbt() {
        return ingredientsWithNbt;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        // For JEI or other mods that expect plain ingredients
        NonNullList<Ingredient> list = NonNullList.create();
        for (IngredientWithNbt iwn : ingredientsWithNbt) {
            list.add(iwn.getIngredient());
        }
        return list;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide) return false;

        // Collect non-empty stacks from input slots (0-2)
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ItemStack stack = pContainer.getItem(i);
            if (!stack.isEmpty()) {
                stacks.add(stack);
            }
        }

        // Quick size check
        if (stacks.size() != ingredientsWithNbt.size()) {
            return false;
        }

        // Copy of ingredients to match against
        List<IngredientWithNbt> remaining = new ArrayList<>(ingredientsWithNbt);

        for (ItemStack stack : stacks) {
            boolean matched = false;
            Iterator<IngredientWithNbt> it = remaining.iterator();
            while (it.hasNext()) {
                IngredientWithNbt iwn = it.next();
                // Check item/tag match
                if (iwn.getIngredient().test(stack)) {
                    // Check stack size >= required count
                    if (stack.getCount() >= iwn.getCount()) {
                        // Check NBT conditions
                        if (matchesNbtConditions(stack, iwn.getNbtConditions())) {
                            it.remove();
                            matched = true;
                            break;
                        }
                    }
                }
            }
            if (!matched) {
                return false;
            }
        }

        return remaining.isEmpty();
    }

    private boolean matchesNbtConditions(ItemStack stack, @Nullable List<NbtCondition> conditions) {
        if (conditions == null || conditions.isEmpty()) return true;
        CompoundTag tag = stack.getTag();
        for (NbtCondition cond : conditions) {
            if (!cond.matches(tag)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }


    public static class Type implements RecipeType<EntherealSelectorRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "enthereal_selection";
    }

    public static class Serializer implements RecipeSerializer<EntherealSelectorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Rupecs_Emblems.MOD_ID, "enthereal_selection");

        @Override
        public EntherealSelectorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredientsArray = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            List<IngredientWithNbt> ingredients = new ArrayList<>();

            for (JsonElement element : ingredientsArray) {
                JsonObject obj = element.getAsJsonObject();
                int count = GsonHelper.getAsInt(obj, "count", 1);

                Ingredient ingredient;
                if (obj.has("item")) {
                    ResourceLocation itemId = new ResourceLocation(GsonHelper.getAsString(obj, "item"));
                    Item item = ForgeRegistries.ITEMS.getValue(itemId);
                    if (item == null) throw new JsonSyntaxException("Unknown item: " + itemId);
                    ingredient = Ingredient.of(item);
                } else if (obj.has("tag")) {
                    ResourceLocation tagId = new ResourceLocation(GsonHelper.getAsString(obj, "tag"));
                    TagKey<Item> tag = TagKey.create(Registries.ITEM, tagId);
                    ingredient = Ingredient.of(tag);
                } else {
                    throw new JsonSyntaxException("Ingredient must have either 'item' or 'tag' field");
                }

                List<NbtCondition> nbtConditions = null;
                if (obj.has("nbt_conditions")) {
                    JsonArray condArray = obj.getAsJsonArray("nbt_conditions");
                    nbtConditions = new ArrayList<>();
                    for (JsonElement condElem : condArray) {
                        JsonObject condObj = condElem.getAsJsonObject();
                        String type = GsonHelper.getAsString(condObj, "type");
                        String key = GsonHelper.getAsString(condObj, "key");
                        switch (type) {
                            case "int_equal":
                                int value = GsonHelper.getAsInt(condObj, "value");
                                boolean exact = GsonHelper.getAsBoolean(condObj, "exact", false);
                                nbtConditions.add(new NbtCondition(key, value, exact, ConditionType.INT_EQUAL));
                                break;
                            case "int_lower":
                                int lowerVal = GsonHelper.getAsInt(condObj, "value");
                                nbtConditions.add(new NbtCondition(key, lowerVal, ConditionType.INT_LOWER));
                                break;
                            case "int_higher":
                                int higherVal = GsonHelper.getAsInt(condObj, "value");
                                nbtConditions.add(new NbtCondition(key, higherVal, ConditionType.INT_HIGHER));
                                break;
                            case "tag_exists":
                                boolean exists = GsonHelper.getAsBoolean(condObj, "exists");
                                nbtConditions.add(new NbtCondition(key, exists, ConditionType.TAG_EXISTS));
                                break;
                        }
                    }
                }

                ingredients.add(new IngredientWithNbt(ingredient, count, nbtConditions));
            }

            return new EntherealSelectorRecipe(ingredients, output, pRecipeId);
        }

        @Override
        public @Nullable EntherealSelectorRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int size = pBuffer.readInt();
            List<IngredientWithNbt> ingredients = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
                int count = pBuffer.readInt();
                int condCount = pBuffer.readInt();
                List<NbtCondition> conditions = new ArrayList<>();
                for (int j = 0; j < condCount; j++) {
                    ConditionType type = pBuffer.readEnum(ConditionType.class);
                    String key = pBuffer.readUtf();
                    switch (type) {
                        case INT_EQUAL:
                            int value = pBuffer.readInt();
                            boolean exact = pBuffer.readBoolean();
                            conditions.add(new NbtCondition(key, value, exact, type));
                            break;
                        case INT_LOWER:
                        case INT_HIGHER:
                            int threshold = pBuffer.readInt();
                            conditions.add(new NbtCondition(key, threshold, type));
                            break;
                        case TAG_EXISTS:
                            boolean exists = pBuffer.readBoolean();
                            conditions.add(new NbtCondition(key, exists, type));
                            break;
                    }
                }
                ingredients.add(new IngredientWithNbt(ingredient, count, conditions.isEmpty() ? null : conditions));
            }
            ItemStack output = pBuffer.readItem();
            return new EntherealSelectorRecipe(ingredients, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, EntherealSelectorRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.ingredientsWithNbt.size());
            for (IngredientWithNbt iwn : pRecipe.ingredientsWithNbt) {
                iwn.getIngredient().toNetwork(pBuffer);
                pBuffer.writeInt(iwn.getCount());
                List<NbtCondition> conditions = iwn.getNbtConditions();
                pBuffer.writeInt(conditions == null ? 0 : conditions.size());
                if (conditions != null) {
                    for (NbtCondition cond : conditions) {
                        pBuffer.writeEnum(cond.getType());
                        pBuffer.writeUtf(cond.getKey());
                        switch (cond.getType()) {
                            case INT_EQUAL:
                                pBuffer.writeInt(cond.getIntValue());
                                pBuffer.writeBoolean(cond.isExact());
                                break;
                            case INT_LOWER:
                            case INT_HIGHER:
                                pBuffer.writeInt(cond.getIntValue());
                                break;
                            case TAG_EXISTS:
                                pBuffer.writeBoolean(cond.isExists());
                                break;
                        }
                    }
                }
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}