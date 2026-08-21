import com.github.artemkoloshva.core.loader.RegistryLoader;
import com.github.artemkoloshva.core.loader.YamlRegistryLoader;
import com.github.artemkoloshva.core.registry.Registry;
import com.github.artemkoloshva.core.registry.SpriteRegistry;
import com.github.artemkoloshva.core.strategy.SpriteMappingStrategy;
import com.github.artemkoloshva.model.Position;
import com.github.artemkoloshva.model.entity.*;
import com.github.artemkoloshva.view.ConsoleWorldRenderer;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.view.Renderer;

void main() {
    Registry<Class<? extends Entity>, String> registry = new SpriteRegistry();
    RegistryLoader<Class<? extends Entity>, String> loader = new YamlRegistryLoader<>(registry,
            "com\\github\\artemkoloshva\\resources\\sprites.yaml",
            new SpriteMappingStrategy());
    World world = new World(20, 20);
    Renderer renderer = new ConsoleWorldRenderer(world, registry);

    for (int i = 0; i < world.getWidth(); i++) {
        world.addEntity(new Position(i, 0), new Tree());

        world.addEntity(new Position(i, 2), new Rock());

        world.addEntity(new Position(i, 4), new Mushroom());
    }

    world.addEntity(new Position(0, 6), new Tree());

    world.addEntity(new Position(0, 8), new Rock());

    world.addEntity(new Position(0, 10), new Mushroom());

    try{
        loader.load();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    renderer.render();
}
