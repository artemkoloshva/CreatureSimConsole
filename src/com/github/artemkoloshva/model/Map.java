package com.github.artemkoloshva.model;

import com.github.artemkoloshva.model.entity.Position;
import com.github.artemkoloshva.model.entity.Entity;

import java.util.HashMap;

public class Map {
    private final HashMap<Position, Entity> entities;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public Map(int width, int height, HashMap<Position, Entity> entities) {
        this.width = width;
        this.height = height;
        this.entities = entities;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
