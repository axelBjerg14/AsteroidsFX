package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        for(int i = 0; i < 3; i++){
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    public Entity createAsteroid(GameData gameData){
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

        int edge = rnd.nextInt(4); // Randomly select one of the four edges
        int x = 0, y = 0;

        switch (edge) {
            case 0: // Top edge
                x = rnd.nextInt(gameData.getDisplayWidth());
                y = 0;
                break;
            case 1: // Right edge
                x = gameData.getDisplayWidth();
                y = rnd.nextInt(gameData.getDisplayHeight());
                break;
            case 2: // Bottom edge
                x = rnd.nextInt(gameData.getDisplayWidth());
                y = gameData.getDisplayHeight();
                break;
            case 3: // Left edge
                x = 0;
                y = rnd.nextInt(gameData.getDisplayHeight());
                break;
        }

        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setHeight(size);
        asteroid.setWidth(size);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(360));
        return asteroid;
    }
}