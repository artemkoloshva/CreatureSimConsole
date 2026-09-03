package com.github.artemkoloshva.utils.pathfinder;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;

public abstract class WorldPathFinder implements PathFinder<Position> {
    protected final World world;

    protected WorldPathFinder(World world) {
        this.world = world;
    }
}
