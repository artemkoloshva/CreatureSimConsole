package com.github.artemkoloshva.view;

import com.github.artemkoloshva.core.registry.Registry;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.entity.Entity;

public class ConsoleWorldRenderer extends WorldRenderer {
    public static final String BG_GREEN = "\u001B[42m";
    private static final String BG_DEFAULT = "\u001B[49m";
    private static final String EMPTY = " ";

    public final Registry<Class<? extends Entity>, String> registry;

    public ConsoleWorldRenderer(World world, Registry<Class<? extends Entity>, String> registry) {
        super(world);
        this.registry = registry;
    }

    @Override
    public void render() {
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                Position position = new Position(x, y);

                if (world.contains(position)) {
                    Entity entity = world.getEntity(position);
                    String sprite = registry.get(entity.getClass());

                    printColor(sprite);
                } else {
                    printColor(EMPTY);
                }
            }

            System.out.println();
        }
    }

    private void printColor(String text) {
        System.out.printf("%s%3s%s",
                BG_GREEN, text, BG_DEFAULT);
    }
}