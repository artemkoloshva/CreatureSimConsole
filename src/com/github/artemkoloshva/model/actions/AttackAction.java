package com.github.artemkoloshva.model.actions;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Attackable;
import com.github.artemkoloshva.model.entity.Attacker;
import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.creature.Creature;

import java.util.List;
import java.util.Map;

public class AttackAction implements Action {
    private final World world;

    public AttackAction(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        for (Map.Entry<Position, Entity> entry : world.getEntities().entrySet()) {
            Entity entity = entry.getValue();

            if (entity instanceof Attacker attacker && entity instanceof Creature creature) {
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

                    if (isTarget && neighbor instanceof Attackable attackable) {
                        attacker.attack(attackable);
                        break;
                    }
                }
            }
        }
    }
}
