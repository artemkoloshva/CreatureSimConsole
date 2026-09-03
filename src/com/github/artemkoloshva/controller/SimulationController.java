package com.github.artemkoloshva.controller;

import com.github.artemkoloshva.model.actions.Action;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.view.Renderer;

import java.util.List;

public class SimulationController {
    private final World world;
    private final Renderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final int maxMoveCounter;
    private int moveCounter;
    private boolean isRunning;

    public SimulationController(World world, Renderer renderer, int maxMoveCounter, List<Action> initActions, List<Action> turnActions) {
        this.world = world;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.maxMoveCounter = maxMoveCounter;
        moveCounter = 0;
        isRunning = false;
    }

    public void nextTurn() {
        executeActions(turnActions);
    }

    public void startSimulation() {
        isRunning = true;

        executeActions(initActions);

        System.out.println("Начало симуляции");
        renderSimulation();

        while (isRunning) {
            executeActions(turnActions);

            moveCounter++;

            System.out.println("Шаг симуляции: " + moveCounter);
            renderSimulation();

            if (moveCounter == maxMoveCounter) {
                pauseSimulation();
            }
        }
    }

    public void pauseSimulation() {
        isRunning = false;
    }

    private void renderSimulation() {
        renderer.render();
    }

    private void executeActions(List<Action> actions) {
        for (Action action : actions) {
            action.execute();
        }
    }
}
