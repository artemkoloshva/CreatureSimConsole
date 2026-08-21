package com.github.artemkoloshva.model.entity;

public class Mushroom extends Entity implements Edible {
    private boolean isEaten;

    public Mushroom() {
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
