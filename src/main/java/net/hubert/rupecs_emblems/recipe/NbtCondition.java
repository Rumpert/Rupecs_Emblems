package net.hubert.rupecs_emblems.recipe;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class NbtCondition {
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
}