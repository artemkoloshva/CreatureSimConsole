package com.github.artemkoloshva.core.strategy;

import java.util.Map;

@FunctionalInterface
public interface MappingStrategy<K, V> {
    Map<K, V> map(Map<String, Object> rawData);
}