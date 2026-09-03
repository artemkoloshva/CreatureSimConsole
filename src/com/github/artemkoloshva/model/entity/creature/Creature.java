package com.github.artemkoloshva.model.entity.creature;

import com.github.artemkoloshva.model.*;
import com.github.artemkoloshva.model.entity.*;
import com.github.artemkoloshva.utils.detector.EntityDetector;
import com.github.artemkoloshva.utils.pathfinder.*;

import java.util.*;

public abstract class Creature extends Entity implements Attackable, Movable {
    protected final int speed;
    protected final int detectionRange;
    protected final double maxHealthPoints;
    protected final List<Class<? extends Entity>> targets;
    protected double currentHealthPoints;

    @SafeVarargs
    protected Creature(double healthPoints,
                       int speed,
                       int detectionRange,
                       Class<? extends Entity>... targets) {
        maxHealthPoints = healthPoints;
        currentHealthPoints = maxHealthPoints;
        this.speed = speed;
        this.detectionRange = detectionRange;
        this.targets = List.of(targets);
    }

    public double getHealthPoints() {
        return currentHealthPoints;
    }

    public List<Class<? extends Entity>> getTargets() {
        return new ArrayList<>(targets);
    }

    @Override
    public void makeMove(World world) {
        WorldPathFinder pathFinder = new BfsWorldPathFinder(world);
        EntityDetector entityDetector = new EntityDetector(world, position, detectionRange, targets);

        List<Entity> detectedTargets = entityDetector.detect();

        List<Position> path = new ArrayList<>();

        for (Entity entity : detectedTargets) {
            path = pathFinder.findPath(position, entity.getPosition());

            if (!path.isEmpty()) {
                break;
            }
        }

        if (path.isEmpty()) {
            makeRandomStep(world);
        }
        else {
            int step = speed >= path.size() ? path.size() - 1 : speed;

            world.removeEntity(position);
            world.addEntity(path.get(step), this);
        }
    }

    @Override
    public void takeDamage(double damageAmount) {
        currentHealthPoints -= damageAmount;

        if (currentHealthPoints < 0) {
            currentHealthPoints = 0;
        }
    }

    private void makeRandomStep(World world) {
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1},
        };

        Random random = new Random();
        Position currentPosition = position;

        for (int step = 0; step < speed; step++) {
            List<int[]> shuffledDirections = new ArrayList<>();
            for (int[] dir : directions) {
                shuffledDirections.add(dir);
            }
            Collections.shuffle(shuffledDirections, random);

            boolean moved = false;

            for (int[] dir : shuffledDirections) {
                Position newPosition = new Position(
                        currentPosition.x() + dir[0],
                        currentPosition.y() + dir[1]
                );

                if (world.isValid(newPosition) && !world.contains(newPosition)) {
                    currentPosition = newPosition;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                break;
            }
        }

        if (!currentPosition.equals(position)) {
            world.removeEntity(position);
            world.addEntity(currentPosition, this);
        }
    }
}
