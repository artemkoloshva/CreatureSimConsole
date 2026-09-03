package com.github.artemkoloshva.model.actions;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Eater;
import com.github.artemkoloshva.model.entity.Edible;
import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.creature.Creature;

import java.util.List;
import java.util.Map;

public class EatAction implements Action {
    private final World world;

    public EatAction(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        for (Map.Entry<Position, Entity> entry : world.getEntities().entrySet()) {
            Entity entity = entry.getValue();

            if (entity instanceof Eater eater && entity instanceof Creature creature) {
                List<Class<? extends Entity>> validTargets = creature.getTargets();

                List<Entity> neighbors = world.getNeighbors(entry.getKey());

                for (Entity neighbor : neighbors) {
                    boolean isTarget = false;

                    for (Class<? extends Entity> targetClass : validTargets) {
                        if (targetClass.isInstance(neighbor)) {
                            isTarget = true;
                            break;
                        }
                    }

                    if (isTarget && neighbor instanceof Edible edible && !edible.isEaten()) {
                        eater.eatIt(edible);
                        break;
                    }
                }
            }
        }
    }
}
