package net.hubert.rupecs_emblems.command.gamerule;

import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static GameRules.Key<GameRules.BooleanValue> SPAWN_MOON_ASTEROIDS;
    public static GameRules.Key<GameRules.IntegerValue> MOON_ASTEROID_COUNT;
    public static GameRules.Key<GameRules.BooleanValue> ALLOW_EMBLEM_SPEED_BONUS;

    public static void register() {
        SPAWN_MOON_ASTEROIDS = GameRules.register("spawnMoonAsteroids", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
        MOON_ASTEROID_COUNT = GameRules.register("moonAsteroidCount", GameRules.Category.MISC, GameRules.IntegerValue.create(5));
        ALLOW_EMBLEM_SPEED_BONUS = GameRules.register("allowEmblemSpeedBonus", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
    }


}