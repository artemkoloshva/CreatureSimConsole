package com.github.artemkoloshva.model.entity;

import com.github.artemkoloshva.model.Position;

import java.util.Objects;

public abstract class Entity {
    protected Position position;
    private static int nextId = 1;
    private int id;

    protected Entity() {
        id = nextId;
        nextId++;
    }

    protected Entity(Position position) {
        this.position = position;
        id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entity entity = (Entity) o;

        return id == entity.id && Objects.equals(position, entity.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, id);
    }
}
