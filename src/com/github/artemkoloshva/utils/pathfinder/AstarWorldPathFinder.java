package com.github.artemkoloshva.utils.pathfinder;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;

import java.util.*;

public class AstarWorldPathFinder extends WorldPathFinder {
    public AstarWorldPathFinder(World world) {
        super(world);
    }

    @Override
    public List<Position> findPath(Position start, Position end) {
        if (!world.isValid(start) || !world.isValid(end)) {
            return Collections.emptyList();
        }

        if (start.equals(end)) {
            return Collections.singletonList(start);
        }

        PriorityQueue<AStarNode> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.fScore));
        Map<Position, Double> gScores = new HashMap<>();
        Map<Position, Position> cameFrom = new HashMap<>();
        Set<Position> closedSet = new HashSet<>();

        double startH = heuristic(start, end);
        AStarNode startNode = new AStarNode(start, 0, startH);

        openSet.add(startNode);
        gScores.put(start, 0.0);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!openSet.isEmpty()) {
            AStarNode current = openSet.poll();
            Position currentPosition = current.position;

            if (closedSet.contains(currentPosition)) {
                continue;
            }

            closedSet.add(currentPosition);

            if (currentPosition.equals(end)) {
                return reconstructPath(cameFrom, end);
            }

            for (int i = 0; i < 4; i++) {
                int newX = currentPosition.x() + dx[i];
                int newY = currentPosition.y() + dy[i];
                Position neighborPosition = new Position(newX, newY);

                if (!world.isValid(neighborPosition)
                        || (world.contains(neighborPosition) && !neighborPosition.equals(end))
                        || closedSet.contains(neighborPosition)) {
                    continue;
                }

                double tentativeG = gScores.getOrDefault(currentPosition, Double.MAX_VALUE) + 1;
                double currentNeighborG = gScores.getOrDefault(neighborPosition, Double.MAX_VALUE);

                if (tentativeG < currentNeighborG) {
                    cameFrom.put(neighborPosition, currentPosition);
                    gScores.put(neighborPosition, tentativeG);

                    double h = heuristic(neighborPosition, end);
                    double f = tentativeG + h;

                    openSet.add(new AStarNode(neighborPosition, tentativeG, h));
                }
            }
        }

        return Collections.emptyList();
    }

    private double heuristic(Position a, Position b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private List<Position> reconstructPath(Map<Position, Position> cameFrom, Position current) {
        List<Position> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }

        return path;
    }

    private static class AStarNode {
        Position position;
        double gScore;
        double fScore;

        AStarNode(Position position, double gScore, double hScore) {
            this.position = position;
            this.gScore = gScore;
            this.fScore = gScore + hScore;
        }
    }
}
