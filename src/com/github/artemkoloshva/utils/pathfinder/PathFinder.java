package com.github.artemkoloshva.utils.pathfinder;

import java.util.List;

public interface PathFinder<T> {
    List<T> findPath(T start, T end);
}
