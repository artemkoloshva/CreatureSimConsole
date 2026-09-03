package com.github.artemkoloshva.model.actions;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Edible;
import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.creature.Creature;

import java.util.Map;

public class DeathAction implements Action{
    private final World world;

    public DeathAction(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        for (Map.Entry<Position, Entity> entry : world.getEntities().entrySet()) {
            if (entry.getValue() instanceof Creature creature) {
                if (creature.getHealthPoints() <= 0) {
                    world.removeEntity(creature.getPosition());
                }
            }

            if (entry.getValue() instanceof Edible food) {
                if (food.isEaten()) {
                    world.removeEntity(entry.getValue().getPosition());
                }
            }
        }
    }
}
