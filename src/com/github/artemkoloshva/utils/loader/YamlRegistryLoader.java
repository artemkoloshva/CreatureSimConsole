package com.github.artemkoloshva.utils.loader;

import com.github.artemkoloshva.utils.registry.Registry;
import com.github.artemkoloshva.utils.strategy.MappingStrategy;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlRegistryLoader<K, V> extends RegistryLoader<K, V> {
    private final String resourcePath;
    private final MappingStrategy<K, V> strategy;

    public YamlRegistryLoader(Registry<K, V> registry, String resourcePath, MappingStrategy<K, V> strategy) {
        super(registry);
        this.resourcePath = resourcePath;
        this.strategy = strategy;
    }

    public void load() throws Exception {
        Yaml yaml = new Yaml();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new Exception("Resource not found: " + resourcePath);
            }

            Map<String, Object> rawData = yaml.load(input);

            if (rawData == null) {
                return;
            }

            Map<K, V> mappedData = strategy.map(rawData);

            for (Map.Entry<K, V> entry : mappedData.entrySet()) {
                registry.register(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            throw new Exception("Failed to load YAML: " + resourcePath, e);
        }
    }
}
