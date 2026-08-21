package com.github.artemkoloshva.core.registry;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class SpriteRegistry implements Registry<Class<? extends Entity>, String> {
    private static final String DEFAULT_SPRITE = "?";
    private static final Map<Class<? extends Entity>, String> SPRITES = new HashMap<>();

    @Override
    public void register(Class<? extends Entity> clazz, String sprite) {
        SPRITES.put(clazz, sprite);
    }

    @Override
    public String get(Class<? extends Entity> key) {
        return SPRITES.getOrDefault(key, DEFAULT_SPRITE);
    }
}
