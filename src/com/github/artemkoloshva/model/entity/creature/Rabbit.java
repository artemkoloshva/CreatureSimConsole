package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.List;

public class Rabbit extends Herbivore {
    public Rabbit(double healthPoints,
                  int speed,
                  int detectionRange,
                  List<Class<? extends Entity>> targets) {
        super(healthPoints, speed, detectionRange, targets);
    }
}
