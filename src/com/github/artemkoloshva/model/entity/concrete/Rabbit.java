package com.github.artemkoloshva.model.entity.concrete;

import com.github.artemkoloshva.model.entity.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit(double healthPoints, int speed) {
        super(healthPoints, speed);
    }

    @Override
    public void makeMove() {}
}
