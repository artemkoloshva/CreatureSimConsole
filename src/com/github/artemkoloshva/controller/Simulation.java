package com.github.artemkoloshva.controller;

import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.view.Renderer;

import java.util.List;

public class Simulation {
    private final World world;
    private final Renderer renderer;
    private final List<Runnable> initActions;
    private final List<Runnable> turnActions;
    private int moveCounter;

    public Simulation(World world, Renderer renderer, List<Runnable> initActions, List<Runnable> turnActions) {
        this.world = world;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
        moveCounter = 0;
    }

    public void nextTurn() {
        for (Runnable action : turnActions) {
            action.run();
        }

        moveCounter++;
    }

    public void startSimulation() {
        for (Runnable action : initActions) {
            action.run();
        }
    }

    public void pauseSimulation() {

    }
}
