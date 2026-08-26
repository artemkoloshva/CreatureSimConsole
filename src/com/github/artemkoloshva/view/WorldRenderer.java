package com.github.artemkoloshva.view;

import com.github.artemkoloshva.model.World;

public abstract class WorldRenderer implements Renderer{
    protected World world;

    public WorldRenderer(World world) {
        this.world = world;
    }
}
