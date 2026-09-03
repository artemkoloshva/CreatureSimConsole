package com.github.artemkoloshva.utils.strategy;

import com.github.artemkoloshva.model.entity.Entity;

import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.net.URL;

public class SpriteMappingStrategy implements MappingStrategy<Class<? extends Entity>, String> {
    private final Map<String, Class<? extends Entity>> entityRegistry;

    public SpriteMappingStrategy() {
        this.entityRegistry = scanPackage("com.github.artemkoloshva.model.entity");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Class<? extends Entity>, String> map(Map<String, Object> rawData) {
        Map<Class<? extends Entity>, String> result = new HashMap<>();

        Map<String, Object> entities = (Map<String, Object>) rawData.get("entities");
        if (entities == null) return result;

        for (Map.Entry<String, Object> entry : entities.entrySet()) {
            String name = entry.getKey();
            Map<String, Object> config = (Map<String, Object>) entry.getValue();
            String sprite = (String) config.get("sprite");

            if (sprite != null) {
                Class<? extends Entity> clazz = entityRegistry.get(name);

                if (clazz != null) {
                    result.put(clazz, sprite);
                } else {
                    System.err.println("Warning: Entity class not found for name: " + name);
                }
            }
        }
        return result;
    }

    private Map<String, Class<? extends Entity>> scanPackage(String basePackage) {
        Map<String, Class<? extends Entity>> classes = new HashMap<>();
        String path = basePackage.replace('.', '/');

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource(path);

            if (url != null) {
                File directory = new File(url.getFile());
                if (directory.exists()) {
                    findClasses(directory, basePackage, classes);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to scan package: " + basePackage, e);
        }

        return classes;
    }

    private void findClasses(File directory, String packageName,
                             Map<String, Class<? extends Entity>> classes) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = file.getName().substring(0, file.getName().length() - 6);
                String fullClassName = packageName + "." + className;

                try {
                    Class<?> clazz = Class.forName(fullClassName);

                    if (Entity.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                        @SuppressWarnings("unchecked")
                        Class<? extends Entity> entityClass = (Class<? extends Entity>) clazz;

                        classes.put(className, entityClass);
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError ignored) {}
            }
        }
    }
}
