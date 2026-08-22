package com.github.artemkoloshva.model.entity.concrete;

import com.github.artemkoloshva.model.entity.Predator;

public class Fox extends Predator {
    public Fox(double healthPoints, int speed, double damage) {
        super(healthPoints, speed, damage);
    }

    @Override
    public void makeMove() {}
}
