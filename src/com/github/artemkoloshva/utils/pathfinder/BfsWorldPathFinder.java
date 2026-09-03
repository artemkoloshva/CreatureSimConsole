package com.github.artemkoloshva.utils.pathfinder;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;

import java.util.*;

public class BfsWorldPathFinder extends WorldPathFinder {
    public BfsWorldPathFinder(World world) {
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

        Queue<Node> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        Node startNode = new Node(start, null);
        queue.add(startNode);
        visited.add(start);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.position.x() + dx[i];
                int newY = current.position.y() + dy[i];
                Position neighborPosition = new Position(newX, newY);

                if (!world.isValid(neighborPosition)
                        || (world.contains(neighborPosition) && !neighborPosition.equals(end))
                        || visited.contains(neighborPosition)) {
                    continue;
                }

                if (neighborPosition.equals(end)) {
                    return world.contains(neighborPosition)
                            ? reconstructPath(current)
                            : reconstructPath(new Node(neighborPosition, current));
                }

                visited.add(neighborPosition);
                queue.add(new Node(neighborPosition, current));
            }
        }

        return Collections.emptyList();
    }

    private List<Position> reconstructPath(Node endNode) {
        List<Position> path = new ArrayList<>();
        Node current = endNode;

        while (current != null) {
            path.add(0, current.position);
            current = current.parent;
        }

        return path;
    }

    private static class Node {
        Position position;
        Node parent;

        Node(Position position, Node parent) {
            this.position = position;
            this.parent = parent;
        }
    }
}
