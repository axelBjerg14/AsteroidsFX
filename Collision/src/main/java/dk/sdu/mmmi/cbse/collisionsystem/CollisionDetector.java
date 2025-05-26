package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {

    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                /* Debug collision logging
                if (collides(entity1, entity2)) {
                    System.out.println("Collision: " + entity1.getClass().getSimpleName() + " vs " + entity2.getClass().getSimpleName());
                }*/

                    if (collides(entity1, entity2)) {
                    if (isBullet(entity1) && isAsteroid(entity2)) {
                        entity1.setHit(true); // bullet
                        entity2.setHit(true); // asteroid
                    } else if (isBullet(entity2) && isAsteroid(entity1)) {
                        entity2.setHit(true); // bullet
                        entity1.setHit(true); // asteroid
                    } else {
                        // Optional: Handle other collisions (e.g., enemy vs player)
                        entity1.setHit(true);
                        entity2.setHit(true);
                    }
                }
            }
        }
    }

    private boolean isBullet(Entity e) {
        return e.getClass().getSimpleName().equals("Bullet"); // or instanceof if allowed
    }

    private boolean isAsteroid(Entity e) {
        return e instanceof dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
