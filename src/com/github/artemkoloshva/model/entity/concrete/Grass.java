package com.github.artemkoloshva.model.entity.concrete;

import com.github.artemkoloshva.model.entity.Edible;
import com.github.artemkoloshva.model.entity.Entity;

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
