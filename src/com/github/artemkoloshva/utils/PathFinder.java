package com.github.artemkoloshva.utils;

import com.github.artemkoloshva.model.Position;

import java.util.List;

public interface PathFinder {
    List<Position> findPath(Position start, Position end);
}
