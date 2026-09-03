import com.github.artemkoloshva.model.actions.*;
import com.github.artemkoloshva.controller.SimulationController;
import com.github.artemkoloshva.model.entity.food.Mushroom;
import com.github.artemkoloshva.utils.loader.RegistryLoader;
import com.github.artemkoloshva.utils.loader.YamlRegistryLoader;
import com.github.artemkoloshva.utils.registry.Registry;
import com.github.artemkoloshva.utils.registry.SpriteRegistry;
import com.github.artemkoloshva.utils.strategy.SpriteMappingStrategy;
import com.github.artemkoloshva.model.World;
import com.github.artemkoloshva.model.entity.creature.*;
import com.github.artemkoloshva.model.entity.food.Grass;
import com.github.artemkoloshva.view.ConsoleWorldRenderer;
import com.github.artemkoloshva.view.Renderer;

void main() {
    try {
        Registry registry = new SpriteRegistry();
        RegistryLoader loader = new YamlRegistryLoader(
                registry,
                "com/github/artemkoloshva/resources/entity-sprites.yaml",
                new SpriteMappingStrategy());

        loader.load();

        World world = new World(30, 15);
        Renderer renderer = new ConsoleWorldRenderer(world, registry);

        List<Action> initActions = new ArrayList<>();
        List<Action> turnActions = new ArrayList<>();

        initActions.add(new CreateEnvironmentAction(world, 40, 80));
        initActions.add(new SpawnAction<Mushroom>(world, 20, Mushroom::new));
        initActions.add(new SpawnAction<Grass>(world, 20, Grass::new));
        initActions.add(new SpawnAction<Fox>(world, 2, ()
                -> new Fox(10, 5, 3, 5, Rabbit.class)));
        initActions.add(new SpawnAction<Rabbit>(world, 2, ()
                -> new Rabbit(5, 3, 5, Mushroom.class)));
        initActions.add(new SpawnAction<Deer>(world, 2, ()
                -> new Deer(50, 4, 6, Grass.class, Mushroom.class)));
        initActions.add(new SpawnAction<Wolf>(world, 2, ()
                -> new Wolf(20, 8, 3, 5, Rabbit.class, Fox.class, Deer.class)));

        turnActions.add(new MoveAction(world));
        turnActions.add(new DeathAction(world));
        turnActions.add(new AttackAction(world));
        turnActions.add(new EatAction(world));

        SimulationController simulation = new SimulationController(world, renderer, initActions, turnActions);
        simulation.startSimulation();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}