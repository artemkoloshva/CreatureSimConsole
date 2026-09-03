package com.github.artemkoloshva.model.actions;

import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.Entity;
import com.github.artemkoloshva.model.entity.environment.Rock;
import com.github.artemkoloshva.model.entity.environment.Tree;

import java.util.*;

public class CreateEnvironmentAction implements Action {
    private final World world;
    private final int maxRocksCount;
    private final int maxTreesCount;
    private final int minClusterSize;
    private final int maxClusterSize;
    private final double clusterDensity;
    private final double treeSpawnChanceNearRock;
    private final int forestClusterSize;

    public CreateEnvironmentAction(World world, int maxRocksCount, int maxTreesCount) {
        this(world, maxRocksCount, maxTreesCount, 3, 12, 0.6, 0.4, 5);
    }

    public CreateEnvironmentAction(World world, int maxRocksCount, int maxTreesCount,
                                   int minClusterSize, int maxClusterSize, double clusterDensity,
                                   double treeSpawnChanceNearRock, int forestClusterSize) {
        this.world = world;
        this.maxRocksCount = maxRocksCount;
        this.maxTreesCount = maxTreesCount;
        this.minClusterSize = minClusterSize;
        this.maxClusterSize = maxClusterSize;
        this.clusterDensity = clusterDensity;
        this.treeSpawnChanceNearRock = treeSpawnChanceNearRock;
        this.forestClusterSize = forestClusterSize;
    }

    @Override
    public void execute() {
        Random random = new Random();

        spawnMountains(world, random);
        spawnTrees(world, random);
    }

    private void spawnMountains(World world, Random random) {
        int spawned = 0;
        int avgClusterSize = (minClusterSize + maxClusterSize) / 2;
        int estimatedClusters = Math.max(1, maxRocksCount / avgClusterSize);

        List<Position> seeds = generateSeeds(world, estimatedClusters, random);

        for (Position seed : seeds) {
            if (spawned >= maxRocksCount) {
                break;
            }

            int currentClusterTarget = minClusterSize + random.nextInt(Math.max(1, maxClusterSize - minClusterSize));
            spawned += growCluster(world, seed, currentClusterTarget, random, new Rock());
        }
    }

    private void spawnTrees(World world, Random random) {
        int treesSpawned = 0;

        for (Map.Entry<Position, Entity> entry : world.getEntities().entrySet()) {
            if (treesSpawned >= maxTreesCount) {
                break;
            }

            if (entry.getValue() instanceof Rock) {
                Position rockPos = entry.getKey();

                treesSpawned += growTreeClusterAround(world, rockPos, random, 3);
            }
        }

        if (treesSpawned < maxTreesCount) {
            int remainingTrees = maxTreesCount - treesSpawned;
            int forestSeedsCount = Math.max(1, remainingTrees / forestClusterSize);

            List<Position> forestSeeds = generateSeeds(world, forestSeedsCount, random);

            for (Position seed : forestSeeds) {
                if (treesSpawned >= maxTreesCount) {
                    break;
                }

                treesSpawned += growCluster(world, seed, forestClusterSize, random, new Tree());
            }
        }
    }

    private int growTreeClusterAround(World world, Position center, Random random, int radius) {
        int placed = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                Position p = new Position(center.x() + x, center.y() + y);

                if (world.isValid(p) && !world.contains(p)) {

                    double distanceFactor = 1.0 - ((Math.abs(x) + Math.abs(y)) / (double)(radius * 2));

                    if (random.nextDouble() < (treeSpawnChanceNearRock * distanceFactor)) {
                        world.addEntity(p, new Tree());
                        placed++;
                    }
                }
            }
        }

        return placed;
    }

    private List<Position> generateSeeds(World world, int count, Random random) {
        List<Position> seeds = new ArrayList<>();

        int attempts = 0;
        int maxAttempts = count * 20;

        while (seeds.size() < count && attempts < maxAttempts) {
            int x = random.nextInt(world.getWidth());
            int y = random.nextInt(world.getHeight());

            Position position = new Position(x, y);

            if (!world.contains(position)) {
                seeds.add(position);
            }

            attempts++;
        }

        return seeds;
    }

    private int growCluster(World world, Position start, int targetSize, Random random, Entity entityTemplate) {
        Set<Position> visited = new HashSet<>();
        Queue<Position> frontier = new LinkedList<>();

        int placed = 0;

        frontier.add(start);
        visited.add(start);

        while (!frontier.isEmpty() && placed < targetSize) {
            Position current = frontier.poll();

            if (random.nextDouble() <= clusterDensity) {
                if (!world.contains(current)) {
                    world.addEntity(current, entityTemplate);

                    placed++;
                }
            }

            for (Position neighbor : getNeighbors(current, world)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    frontier.add(neighbor);
                }
            }
        }

        return placed;
    }

    private List<Position> getNeighbors(Position position, World world) {
        List<Position> neighbors = new ArrayList<>(4);

        int[][] offsets = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] offset : offsets) {
            Position neighborPosition = new Position(position.x() + offset[0], position.y() + offset[1]);

            if (world.isValid(neighborPosition)) {
                neighbors.add(neighborPosition);
            }
        }

        return neighbors;
    }
}
