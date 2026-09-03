package com.github.artemkoloshva.utils.strategy;

import java.util.Map;

@FunctionalInterface
public interface MappingStrategy<K, V> {
    Map<K, V> map(Map<String, Object> rawData);
}