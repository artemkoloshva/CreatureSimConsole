package com.github.artemkoloshva.core.strategy;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class SpriteMappingStrategy implements MappingStrategy<Class<? extends Entity>, String> {
    private static final String PATH_TO_ENTITY_PACKAGE = "com.github.artemkoloshva.model.entity.";

    @Override
    @SuppressWarnings("unchecked")
    public Map<Class<? extends Entity>, String> map(Map<String, Object> rawData) {
        Map<Class<? extends Entity>, String> result = new HashMap<>();

        Map<String, Object> entities = (Map<String, Object>) rawData.get("entities");

        if (entities == null) {
            return result;
        };

        for (Map.Entry<String, Object> entry : entities.entrySet()) {
            String name = entry.getKey();
            Map<String, Object> config = (Map<String, Object>) entry.getValue();
            String sprite = (String) config.get("sprite");

            if (sprite != null) {
                Class<? extends Entity> clazz = findClass(name);

                if (clazz != null) {
                    result.put(clazz, sprite);
                }
            }
        }

        return result;
    }

    private Class<? extends Entity> findClass(String name) {
        try {
            return (Class<? extends Entity>) Class.forName(PATH_TO_ENTITY_PACKAGE + name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
