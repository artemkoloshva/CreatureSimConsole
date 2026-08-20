package com.github.artemkoloshva.model.entity;

public class Grass extends Entity implements Edible {
    private boolean isEaten;

    public Grass() {
        isEaten = false;
    }

    @Override
    public void beEaten() {
        isEaten = true;
    }

    @Override
    public boolean isEaten() {
        return isEaten;
    }
}
