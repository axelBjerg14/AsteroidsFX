package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            moveEnemy(enemy, gameData);
            shoot(enemy, gameData, world);

            if(enemy.isHit()){
                world.removeEntity(enemy);
            }
        }
    }

    private void moveEnemy(Entity enemy, GameData gameData) {
        double changeX = Math.cos(Math.toRadians(enemy.getRotation())) + Math.random() * 2 - 1;
        double changeY = Math.sin(Math.toRadians(enemy.getRotation())) + Math.random() * 2 - 1;
        enemy.setX(enemy.getX() + changeX);
        enemy.setY(enemy.getY() + changeY);
        enemy.setRotation(enemy.getRotation() + Math.random() * 2 - 0.5);

        int outOfBounds = 1;

        if (enemy.getX() < -outOfBounds) {
            enemy.setX(gameData.getDisplayWidth() + 1);
        }

        if (enemy.getX() > gameData.getDisplayWidth() + outOfBounds) {
            enemy.setX(-1);
        }

        if (enemy.getY() < -outOfBounds) {
            enemy.setY(gameData.getDisplayHeight() + 1);
        }

        if (enemy.getY() > gameData.getDisplayHeight() + outOfBounds) {
            enemy.setY(-1);
        }
    }

    private void shoot(Entity enemy, GameData gameData, World world) {
        if (Math.random() < 0.01) {
            for (BulletSPI bulletSPI : getBulletSPIs()) {
                world.addEntity(bulletSPI.createBullet(enemy, gameData));
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}