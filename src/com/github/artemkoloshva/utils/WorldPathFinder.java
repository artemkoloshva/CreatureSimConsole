package com.github.artemkoloshva.utils;

import com.github.artemkoloshva.model.World;

public abstract class WorldPathFinder implements PathFinder {
    protected final World world;

    protected WorldPathFinder(World world) {
        this.world = world;
    }
}
