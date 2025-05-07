package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    private float timeSinceLastShot = 0;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Entity.class)) {
            // You can refine this to only include enemies
            if (!isEnemy(entity)) continue;

            // Random movement
            double vx = Math.random() * 2 - 1;
            double vy = Math.random() * 2 - 1;

            entity.setX(entity.getX() + vx);
            entity.setY(entity.getY() + vy);

            // Shooting every 1 second
            timeSinceLastShot += gameData.getDelta();
            if (timeSinceLastShot > 1) {
                timeSinceLastShot = 0;
                spawnBulletFrom(entity, world);
            }
        }
    }

    private boolean isEnemy(Entity e) {
        return e.getClass().getSimpleName().toLowerCase().contains("enemy");
    }

    private void spawnBulletFrom(Entity shooter, World world) {
        Entity bullet = new Entity();
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRadius(2);
        bullet.setPolygonCoordinates(-1, -1, 2, 0, -1, 1);

        world.addEntity(bullet);
    }
}
