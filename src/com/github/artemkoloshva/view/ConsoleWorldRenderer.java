package com.github.artemkoloshva.view;

import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.entity.Entity;

public class ConsoleWorldRenderer extends WorldRenderer {
    private static final int CELL_WIDTH = 3;
    public static final String BG_GREEN = "\u001B[42m";
    private static final String BG_DEFAULT = "\u001B[49m";
    public static final String RESET = "\u001B[0m";

    public ConsoleWorldRenderer(World world) {
        super(world);
    }

    @Override
    public void render() {
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                Position position = new Position(x, y);

                if (world.contains(position)) {
                    printEntity(world.getEntity(position));
                } else {
                    printColor(formatToCellWidth(" ", CELL_WIDTH));
                }
            }

            System.out.println();
        }
    }

    private void printEntity(Entity entity) {
        String text = SpriteRegistry.getSymbol(entity);

        printColor(formatToCellWidth(text, CELL_WIDTH));
    }

    private String formatToCellWidth(String text, int cellWidth) {
        if (cellWidth <= 0) {
            return text;
        }

        if (text.length() >= cellWidth) {
            return text.substring(0, cellWidth);
        }

        return String.format("%-" + cellWidth + "s", text);
    }

    private void printColor(String text) {
        System.out.printf("%s%s%s",BG_GREEN, text, BG_DEFAULT);
    }
}
