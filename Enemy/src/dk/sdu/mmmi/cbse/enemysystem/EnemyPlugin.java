package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Entity(); // or use a subclass like Enemy extends Entity

        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemyShip.setX(Math.random() * gameData.getDisplayWidth());
        enemyShip.setY(Math.random() * gameData.getDisplayHeight());
        enemyShip.setRadius(8);

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
