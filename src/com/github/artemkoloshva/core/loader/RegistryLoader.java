package com.github.artemkoloshva.core.loader;

import com.github.artemkoloshva.core.registry.Registry;

public abstract class RegistryLoader<K, V> {
    protected final Registry<K, V> registry;

    public RegistryLoader(Registry<K, V> registry) {
        this.registry = registry;
    }

    public abstract void load() throws Exception;
}
