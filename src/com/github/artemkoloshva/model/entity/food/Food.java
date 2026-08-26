package com.github.artemkoloshva.model.entity.food;

import com.github.artemkoloshva.model.entity.Edible;
import com.github.artemkoloshva.model.entity.Entity;

public abstract class Food extends Entity implements Edible {
    private boolean isEaten;

    public Food() {
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
