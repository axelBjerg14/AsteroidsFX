package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {

    @Test
    void testBulletAsteroidCollision() {
        // Arrange
        Entity bullet = new BulletStub(0, 0, 2);  // position (0,0), radius 2
        Entity asteroid = new AsteroidStub(1, 0, 2); // position (1,0), radius 2 → distance = 1 < 4 → collision

        World world = new World();
        world.addEntity(bullet);
        world.addEntity(asteroid);

        GameData gameData = new GameData(); // Can be empty unless you need keys/time etc.

        CollisionDetector collisionDetector = new CollisionDetector();

        // Act
        collisionDetector.process(gameData, world);

        // Assert
        assertTrue(bullet.isHit());
        assertTrue(asteroid.isHit());
    }

    @Test
    void testNoCollision() {
        // Arrange
        Entity bullet = new BulletStub(0, 0, 1);
        Entity asteroid = new AsteroidStub(10, 0, 1); // distance = 10 > 2 → no collision

        World world = new World();
        world.addEntity(bullet);
        world.addEntity(asteroid);

        GameData gameData = new GameData();

        CollisionDetector collisionDetector = new CollisionDetector();

        // Act
        collisionDetector.process(gameData, world);

        // Assert
        assertFalse(bullet.isHit());
        assertFalse(asteroid.isHit());
    }

        static class BulletStub extends Entity {
        public BulletStub(float x, float y, float radius) {
            setX(x);
            setY(y);
            setRadius(radius);
        }

        public String getSimpleName() {
            return "Bullet";
        }
    }

    static class AsteroidStub extends dk.sdu.mmmi.cbse.common.asteroids.Asteroid {
        public AsteroidStub(float x, float y, float radius) {
            setX(x);
            setY(y);
            setRadius(radius);
        }
    }
}

