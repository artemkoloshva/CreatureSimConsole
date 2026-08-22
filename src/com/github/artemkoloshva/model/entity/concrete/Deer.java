package com.github.artemkoloshva.model.entity.concrete;

import com.github.artemkoloshva.model.entity.Herbivore;

public class Deer extends Herbivore {
    public Deer(double healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public void makeMove() {}
}
