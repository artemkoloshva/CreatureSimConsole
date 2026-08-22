import com.github.artemkoloshva.core.loader.RegistryLoader;
import com.github.artemkoloshva.core.loader.YamlRegistryLoader;
import com.github.artemkoloshva.core.registry.Registry;
import com.github.artemkoloshva.core.registry.SpriteRegistry;
import com.github.artemkoloshva.core.strategy.SpriteMappingStrategy;
import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.entity.*;
import com.github.artemkoloshva.model.entity.concrete.*;
import com.github.artemkoloshva.view.ConsoleWorldRenderer;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.view.Renderer;

void main() {
    Registry<Class<? extends Entity>, String> registry = new SpriteRegistry();
    RegistryLoader<Class<? extends Entity>, String> loader = new YamlRegistryLoader<>(registry,
            "com\\github\\artemkoloshva\\resources\\entity-sprites.yaml",
            new SpriteMappingStrategy());
    World world = new World(20, 20);
    Renderer renderer = new ConsoleWorldRenderer(world, registry);

    for (int y = 0; y < world.getHeight(); y++) {
        for (int x = 0; x < world.getWidth(); x++) {
            world.addEntity(new Position(x, 0), new Rock());
            world.addEntity(new Position(0, y), new Rock());
            world.addEntity(new Position(world.getWidth() - 1, y), new Rock());
            world.addEntity(new Position(x, world.getHeight() - 1), new Rock());
        }
    }

    world.addEntity(new Position(1, 1), new Tree());
    world.addEntity(new Position(1, 5), new Tree());
    world.addEntity(new Position(5, 1), new Tree());
    world.addEntity(new Position(1, 2), new Tree());

    world.addEntity(new Position(1, 7), new Grass());
    world.addEntity(new Position(8, 1), new Grass());
    world.addEntity(new Position(10, 1), new Grass());
    world.addEntity(new Position(1, 11), new Grass());

    world.addEntity(new Position(5, 6), new Fox(10, 1, 5));
    world.addEntity(new Position(3, 4), new Rabbit(2, 2));
    world.addEntity(new Position(10, 10), new Wolf(30, 1, 10));
    world.addEntity(new Position(9, 15), new Deer(50, 2));

    try{
        loader.load();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    renderer.render();
}
