package com.github.artemkoloshva.view;

import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.*;

import java.util.HashMap;
import java.util.Map;

public class SpriteRegistry {
    private static final Map<Class<? extends Entity>, String> SYMBOLS = new HashMap<>();

    public static String getSymbol(Entity entity) {
        return SYMBOLS.getOrDefault(entity.getClass(), "?");
    }

    public static void register(Class<? extends Entity> clazz, String symbol) {
        SYMBOLS.put(clazz, symbol);
    }
}
