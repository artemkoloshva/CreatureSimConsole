package com.github.artemkoloshva.model;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    private final Map<Position, Entity> entities = new HashMap<>();
    private final int width;
    private final int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Entity getEntity(Position position) {
        return entities.get(position);
    }

    public Map<Position, Entity> getEntities() {
        return new HashMap<>(entities);
    }

    public void addEntity(Position position, Entity entity) {
        entities.put(position, entity);

        entity.setPosition(position);
    }

    public void removeEntity(Position position) {
        entities.remove(position);
    }

    public boolean contains(Position position) {
        return entities.containsKey(position);
    }

    public boolean isValid(Position position) {
        if (position == null) {
            return false;
        }

        int x = position.x();
        int y = position.y();

        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public List<Entity> getNeighbors(Position center) {
        List<Entity> neighbors = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                Position neighborPos = new Position(center.x() + dx, center.y() + dy);

                if (isValid(neighborPos)) {
                    Entity entity = entities.get(neighborPos);
                    if (entity != null) {
                        neighbors.add(entity);
                    }
                }
            }
        }

        return neighbors;
    }
}
