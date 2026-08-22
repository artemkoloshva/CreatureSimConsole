package com.github.artemkoloshva.model.entity.concrete;

import com.github.artemkoloshva.model.entity.Predator;

public class Wolf extends Predator {
    public Wolf(double healthPoints, int speed, double damage) {
        super(healthPoints, speed, damage);
    }

    @Override
    public void makeMove() {}
}
