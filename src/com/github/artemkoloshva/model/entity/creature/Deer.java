package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.List;

public class Deer extends Herbivore {
    public Deer(double healthPoints,
                int speed,
                int detectionRange,
                List<Class<? extends Entity>> targets) {
        super(healthPoints, speed, detectionRange, targets);
    }
}
