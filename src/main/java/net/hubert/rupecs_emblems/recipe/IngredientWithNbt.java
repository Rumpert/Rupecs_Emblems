package net.hubert.rupecs_emblems.recipe;

import com.google.gson.JsonObject;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IngredientWithNbt {
    private final Ingredient ingredient;
    private final int count;
    @Nullable
    private final List<NbtCondition> nbtConditions;

    public IngredientWithNbt(Ingredient ingredient, int count, @Nullable List<NbtCondition> nbtConditions) {
        this.ingredient = ingredient;
        this.count = count;
        this.nbtConditions = nbtConditions;
    }

    public Ingredient getIngredient() { return ingredient; }
    public int getCount() { return count; }
    @Nullable
    public List<NbtCondition> getNbtConditions() { return nbtConditions; }

    // For JSON serialization (used by builder)
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("count", count);
        // Add ingredient representation (item or tag)
        // ... same as your existing method
        return json;
    }
}