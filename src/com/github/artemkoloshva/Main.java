import com.github.artemkoloshva.core.loader.RegistryLoader;
import com.github.artemkoloshva.core.loader.YamlRegistryLoader;
import com.github.artemkoloshva.core.registry.Registry;
import com.github.artemkoloshva.core.registry.SpriteRegistry;
import com.github.artemkoloshva.core.strategy.SpriteMappingStrategy;
import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.entity.*;
import com.github.artemkoloshva.model.entity.environment.Rock;
import com.github.artemkoloshva.model.entity.environment.Tree;
import com.github.artemkoloshva.utils.*;
import com.github.artemkoloshva.view.ConsoleWorldRenderer;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.view.Renderer;

void main() {
    Registry<Class<? extends Entity>, String> registry = new SpriteRegistry();
    RegistryLoader<Class<? extends Entity>, String> loader = new YamlRegistryLoader<>(registry,
            "com\\github\\artemkoloshva\\resources\\entity-sprites.yaml",
            new SpriteMappingStrategy());
    World world = new World(10, 10);
    Renderer renderer = new ConsoleWorldRenderer(world, registry);

    for (int i = 0; i < 15; i++) {
        randomSpawnEntity(world, new Tree());
        randomSpawnEntity(world, new Rock());
    }

    world.removeEntity(new Position(0, 0));
    world.removeEntity(new Position(world.getWidth(), world.getHeight()));

    try{
        loader.load();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    renderer.render();

    WorldPathFinder pathFinder = new AstarWorldPathFinder(world);

    printPositions(pathFinder.findPath(new Position(0,0), new Position(world.getWidth() - 1, world.getHeight() - 1)));
}

public static void randomSpawnEntity(World world, Entity entity) {
    Random random = new Random();

    Position position = new Position(random.nextInt(0, world.getWidth()),
            random.nextInt(0, world.getHeight()));

    world.addEntity(position, entity);
}

public static void printPositions(List<Position> positions) {
    if (positions == null || positions.isEmpty()) {
        System.out.println("Список позиций пуст.");
        return;
    }

    System.out.println("--- Список позиций ---");
    for (int i = 0; i < positions.size(); i++) {
        Position pos = positions.get(i);
        System.out.printf("[%d] x: %d, y: %d%n", i, pos.x(), pos.y());
    }
    System.out.println("----------------------");
}