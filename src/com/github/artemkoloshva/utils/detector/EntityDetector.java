package com.github.artemkoloshva.utils.detector;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityDetector implements Detector<List<Entity>> {
    private final World world;
    private final Position centerPosition;
    private final int detectionRange;
    protected final List<Class<? extends Entity>> filter;

    public EntityDetector(World world, Position centerPosition, int detectionRange, List<Class<? extends Entity>> filter) {
        this.world = world;
        this.centerPosition = centerPosition;
        this.detectionRange = detectionRange;
        this.filter = filter;
    }

    @Override
    public List<Entity> detect() {
        List<Entity> detectedTargets = new ArrayList<>();
        int rangeSquared = detectionRange * detectionRange;

        for (Map.Entry<Position, Entity> entry : world.getEntities().entrySet()) {
            Entity entity = entry.getValue();

            if (centerPosition.equals(entity.getPosition())) {
                continue;
            }

            int dx = entity.getPosition().x() - centerPosition.x();
            int dy = entity.getPosition().y() - centerPosition.y();
            int distanceSquared = dx * dx + dy * dy;

            if (distanceSquared > rangeSquared) {
                continue;
            }

            if (filter.stream().anyMatch(targetClass
                    -> targetClass.isAssignableFrom(entity.getClass()))) {
                detectedTargets.add(entity);
            }
        }

        return detectedTargets;
    }
}
