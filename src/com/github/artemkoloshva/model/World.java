package com.github.artemkoloshva.model;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.HashMap;

public class World {
    private final HashMap<Position, Entity> entities = new HashMap<>();
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

    public boolean contains(Position position) {
        return entities.containsKey(position);
    }

    public Entity getEntity(Position position) {
        return entities.get(position);
    }

    public void addEntity(Position position, Entity entity) {
        entities.put(position, entity);
    }

    public void removeEntity(Position position) {
        entities.remove(position);
    }
}
