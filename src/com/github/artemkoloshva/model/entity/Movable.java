package com.github.artemkoloshva.model.entity;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;

public interface Movable {
    void makeMove(World world, Position currentPosition);
}
