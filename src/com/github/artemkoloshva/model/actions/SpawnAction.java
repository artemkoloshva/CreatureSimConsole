package com.github.artemkoloshva.model.actions;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Entity;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnAction<T extends Entity> implements Action {
    private final int count;
    private final Supplier<T> entityFactory;
    private final World world;

    public SpawnAction(World world, int count, Supplier<T> entityFactory) {
        this.count = count;
        this.entityFactory = entityFactory;
        this.world = world;
    }

    @Override
    public void execute() {
        Random random = new Random();
        int spawned = 0;
        int attempts = 0;

        int maxAttempts = count * 50;

        while (spawned < count && attempts < maxAttempts) {
            int x = random.nextInt(world.getWidth());
            int y = random.nextInt(world.getHeight());
            Position position = new Position(x, y);

            if (!world.contains(position)) {
                T entity = entityFactory.get();
                world.addEntity(position, entity);
                spawned++;
            }

            attempts++;
        }
    }
}
