package net.hubert.rupecs_emblems.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hubert.rupecs_emblems.recipe.ConditionType;
import net.hubert.rupecs_emblems.recipe.ModRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RealityManipulationRecipeBuilder {
    private final Item result;
    private final int resultCount;
    private final List<IngredientWithNbt> ingredientsWithNbt = new ArrayList<>();
    private float successChance = 1.0f;
    @Nullable
    private JsonObject resultNbt = null;

    // Constructor and static factory
    public RealityManipulationRecipeBuilder(Item result, int count) {
        this.result = result;
        this.resultCount = count;
    }

    public static RealityManipulationRecipeBuilder realityManipulation(Item result, int count) {
        return new RealityManipulationRecipeBuilder(result, count);
    }

    // Simple ingredient without NBT conditions (item)
    public RealityManipulationRecipeBuilder requires(Item item, int count) {
        ingredientsWithNbt.add(new IngredientWithNbt(Ingredient.of(item), count, null));
        return this;
    }

    public RealityManipulationRecipeBuilder requires(Item item) {
        return requires(item, 1);
    }

    // Simple ingredient without NBT conditions (tag)
    public RealityManipulationRecipeBuilder requires(TagKey<Item> tag, int count) {
        ingredientsWithNbt.add(new IngredientWithNbt(Ingredient.of(tag), count, null));
        return this;
    }

    public RealityManipulationRecipeBuilder requires(TagKey<Item> tag) {
        return requires(tag, 1);
    }

    // Simple ingredient without NBT conditions (custom ingredient)
    public RealityManipulationRecipeBuilder requires(Ingredient ingredient, int count) {
        ingredientsWithNbt.add(new IngredientWithNbt(ingredient, count, null));
        return this;
    }

    // Ingredient with NBT conditions (via lambda)
    public RealityManipulationRecipeBuilder requires(Item item, int count, Consumer<IngredientNbtBuilder> nbtBuilder) {
        IngredientNbtBuilder builder = new IngredientNbtBuilder();
        nbtBuilder.accept(builder);
        ingredientsWithNbt.add(new IngredientWithNbt(Ingredient.of(item), count, builder.build()));
        return this;
    }

    public RealityManipulationRecipeBuilder requires(Item item, Consumer<IngredientNbtBuilder> nbtBuilder) {
        return requires(item, 1, nbtBuilder);
    }

    // For tag with NBT conditions (if needed)
    public RealityManipulationRecipeBuilder requires(TagKey<Item> tag, int count, Consumer<IngredientNbtBuilder> nbtBuilder) {
        IngredientNbtBuilder builder = new IngredientNbtBuilder();
        nbtBuilder.accept(builder);
        ingredientsWithNbt.add(new IngredientWithNbt(Ingredient.of(tag), count, builder.build()));
        return this;
    }

    public RealityManipulationRecipeBuilder requires(TagKey<Item> tag, Consumer<IngredientNbtBuilder> nbtBuilder) {
        return requires(tag, 1, nbtBuilder);
    }

    // For custom Ingredient with NBT conditions
    public RealityManipulationRecipeBuilder requires(Ingredient ingredient, int count, Consumer<IngredientNbtBuilder> nbtBuilder) {
        IngredientNbtBuilder builder = new IngredientNbtBuilder();
        nbtBuilder.accept(builder);
        ingredientsWithNbt.add(new IngredientWithNbt(ingredient, count, builder.build()));
        return this;
    }

    // Existing success chance and output NBT methods
    public RealityManipulationRecipeBuilder withSuccessChance(float chance) {
        if (chance < 0.0f || chance > 1.0f) {
            throw new IllegalArgumentException("Success chance must be between 0.0 and 1.0");
        }
        this.successChance = chance;
        return this;
    }

    public RealityManipulationRecipeBuilder withNbt(JsonObject nbt) {
        this.resultNbt = nbt;
        return this;
    }

    public RealityManipulationRecipeBuilder withIntTag(String key, int value) {
        if (this.resultNbt == null) this.resultNbt = new JsonObject();
        this.resultNbt.addProperty(key, value);
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer, String id) {
        ResourceLocation resultId = ForgeRegistries.ITEMS.getKey(this.result);
        if (resultId == null) throw new IllegalStateException("Unknown item: " + this.result);
        consumer.accept(new Result(
                new ResourceLocation(id),
                this.result,
                this.resultCount,
                this.ingredientsWithNbt,
                this.successChance,
                this.resultNbt
        ));
    }

    // ----- Inner classes -----

    /**
     * Helper class to build a list of NBT conditions for an ingredient.
     */
    public static class IngredientNbtBuilder {
        private final List<NbtCondition> conditions = new ArrayList<>();

        public IngredientNbtBuilder hasIntValue(String key, int value, boolean exact) {
            conditions.add(new NbtCondition(key, value, exact, ConditionType.INT_EQUAL));
            return this;
        }

        public IngredientNbtBuilder hasIntLower(String key, int value) {
            conditions.add(new NbtCondition(key, value, ConditionType.INT_LOWER));
            return this;
        }

        public IngredientNbtBuilder hasIntHigher(String key, int value) {
            conditions.add(new NbtCondition(key, value, ConditionType.INT_HIGHER));
            return this;
        }

        public IngredientNbtBuilder hasTag(String key, boolean exists) {
            conditions.add(new NbtCondition(key, exists, ConditionType.TAG_EXISTS));
            return this;
        }

        // Add more types as needed (string, boolean, etc.)

        private List<NbtCondition> build() {
            return conditions;
        }
    }

    /**
     * Represents a single NBT condition.
     */
    public static class NbtCondition {
        private final String key;
        private final Object value; // Integer or Boolean
        private final boolean exact; // only used for INT_EQUAL (if you want inclusive/exclusive, ignore for now)
        private final ConditionType type;

        // Constructor for INT_EQUAL
        public NbtCondition(String key, int value, boolean exact, ConditionType type) {
            if (type != ConditionType.INT_EQUAL) {
                throw new IllegalArgumentException("Use other constructor for non-INT_EQUAL types");
            }
            this.key = key;
            this.value = value;
            this.exact = exact;
            this.type = type;
        }

        // Constructor for INT_LOWER, INT_HIGHER
        public NbtCondition(String key, int value, ConditionType type) {
            if (type != ConditionType.INT_LOWER && type != ConditionType.INT_HIGHER) {
                throw new IllegalArgumentException("Use other constructor for INT_EQUAL or TAG_EXISTS");
            }
            this.key = key;
            this.value = value;
            this.exact = false; // unused
            this.type = type;
        }

        // Constructor for TAG_EXISTS
        public NbtCondition(String key, boolean exists, ConditionType type) {
            this.key = key;
            this.value = exists;
            this.exact = false;
            this.type = type;
        }

        public boolean matches(CompoundTag tag) {
            if (tag == null) return false;
            switch (type) {
                case INT_EQUAL:
                    if (!tag.contains(key, Tag.TAG_INT)) return false;
                    int tagValue = tag.getInt(key);
                    if (exact) {
                        return tagValue == (Integer) value;
                    } else {
                        // For "greater or equal" or "less or equal"? Your builder only supports exact.
                        // If you need ranges, extend the condition types.
                        return tagValue == (Integer) value; // fallback to exact
                    }
                case INT_LOWER:
                    if (!tag.contains(key, Tag.TAG_INT)) return false;
                    return tag.getInt(key) < (Integer) value;
                case INT_HIGHER:
                    if (!tag.contains(key, Tag.TAG_INT)) return false;
                    return tag.getInt(key) > (Integer) value;
                case TAG_EXISTS:
                    boolean shouldExist = (Boolean) value;
                    return tag.contains(key) == shouldExist;
                default:
                    return false;
            }
        }

        // Getters for serialization
        public String getKey() { return key; }
        public ConditionType getType() { return type; }
        public int getIntValue() { return (Integer) value; }
        public boolean isExists() { return (Boolean) value; }
        public boolean isExact() { return exact; }
        public JsonObject toJson() {
            JsonObject obj = new JsonObject();
            obj.addProperty("key", key);
            switch (type) {
                case INT_EQUAL:
                    obj.addProperty("type", "int_equal");
                    obj.addProperty("value", (Integer) value);
                    obj.addProperty("exact", exact);
                    break;
                case INT_LOWER:
                    obj.addProperty("type", "int_lower");
                    obj.addProperty("value", (Integer) value);
                    break;
                case INT_HIGHER:
                    obj.addProperty("type", "int_higher");
                    obj.addProperty("value", (Integer) value);
                    break;
                case TAG_EXISTS:
                    obj.addProperty("type", "tag_exists");
                    obj.addProperty("exists", (Boolean) value);
                    break;
            }
            return obj;
        }
    }


    /**
     * Holds an ingredient together with its count and NBT conditions.
     */
    public static class IngredientWithNbt {
        public final Ingredient ingredient;
        public final int count;
        @Nullable
        public final List<NbtCondition> nbtConditions;

        public IngredientWithNbt(Ingredient ingredient, int count, @Nullable List<NbtCondition> nbtConditions) {
            this.ingredient = ingredient;
            this.count = count;
            this.nbtConditions = nbtConditions;
        }

        public JsonObject toJson() {
            JsonObject json = new JsonObject();
            // Add count (maybe not part of ingredient itself)
            json.addProperty("count", count);
            // Add the base ingredient (could be item or tag)
            JsonObject ingredientJson = ingredient.toJson().getAsJsonObject();
            // If ingredient is a simple item, its JSON looks like {"item":"minecraft:stick"}
            // If it's a tag, it's {"tag":"minecraft:planks"}
            // We'll merge it into our root
            ingredientJson.entrySet().forEach(e -> json.add(e.getKey(), e.getValue()));
            // Add NBT conditions if any
            if (nbtConditions != null && !nbtConditions.isEmpty()) {
                JsonArray conditionsArray = new JsonArray();
                for (NbtCondition cond : nbtConditions) {
                    conditionsArray.add(cond.toJson());
                }
                json.add("nbt_conditions", conditionsArray);
            }
            return json;
        }
    }

    // ----- Result class -----
    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final List<IngredientWithNbt> ingredientsWithNbt;
        private final float successChance;
        @Nullable
        private final JsonObject resultNbt;

        public Result(ResourceLocation id, Item result, int count,
                      List<IngredientWithNbt> ingredientsWithNbt,
                      float successChance, @Nullable JsonObject resultNbt) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.ingredientsWithNbt = ingredientsWithNbt;
            this.successChance = successChance;
            this.resultNbt = resultNbt;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray ingredientsArray = new JsonArray();
            for (IngredientWithNbt ing : ingredientsWithNbt) {
                ingredientsArray.add(ing.toJson());
            }
            json.add("ingredients", ingredientsArray);

            JsonObject outputJson = new JsonObject();
            outputJson.addProperty("item", ForgeRegistries.ITEMS.getKey(result).toString());
            outputJson.addProperty("count", count);
            if (resultNbt != null) {
                outputJson.add("nbt", resultNbt);
            }
            json.add("output", outputJson);

            if (successChance < 1.0f) {
                json.addProperty("success_chance", successChance);
            }
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.REALITY_MANIPULATION_SERIALIZER.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}