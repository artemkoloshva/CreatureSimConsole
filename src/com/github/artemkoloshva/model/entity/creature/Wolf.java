package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.List;

public class Wolf extends Predator {
    public Wolf(double healthPoints,
                double damage,
                int speed,
                int detectionRange,
                Class<? extends Entity>... targets) {
        super(healthPoints, damage, speed, detectionRange, targets);
    }
}
