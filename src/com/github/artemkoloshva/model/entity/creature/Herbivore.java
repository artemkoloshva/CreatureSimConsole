package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.entity.Eater;
import com.github.artemkoloshva.model.entity.Edible;
import com.github.artemkoloshva.model.entity.Entity;

import java.util.List;

public abstract class Herbivore extends Creature implements Eater {
    protected Herbivore(double healthPoints,
                        int speed,
                        int detectionRange,
                        Class<? extends Entity>... targets) {
        super(healthPoints, speed, detectionRange, targets);
    }

    @Override
    public void eatIt(Edible target) {
        target.beEaten();
    };
}
