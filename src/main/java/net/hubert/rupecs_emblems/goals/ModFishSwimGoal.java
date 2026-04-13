package net.hubert.rupecs_emblems.goals;

import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.AbstractFish;

public class ModFishSwimGoal extends RandomSwimmingGoal {

        public ModFishSwimGoal(AbstractFish pFish) {
            super(pFish, (double)1.0F, 40);
        }

        public boolean canUse() {
            return super.canUse();
        }
    }