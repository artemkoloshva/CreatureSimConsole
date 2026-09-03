package com.github.artemkoloshva.model.actions;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.Movable;

import java.util.Map;

public class MoveAction implements Action {
    private final World world;

    public MoveAction(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        for (Map.Entry<Position, Entity> entry : world.getEntities().entrySet()) {
            if (entry.getValue() instanceof Movable entity) {
                entity.makeMove(world);
            }
        }
    }
}
