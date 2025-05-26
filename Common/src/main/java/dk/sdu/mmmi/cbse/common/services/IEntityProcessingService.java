package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


/*
* Processes all entities each frame. Common operations include updating position, velocity or game logic.
 */
public interface IEntityProcessingService {

    /**
     * Called once very game tick to update the state of entities in the game world.
     *
     *
     * @param gameData the game data for the current frame, must not be null
     * @param world the world containing the entities, must not be null
     * @pre gameData != null && world != null
     * @post entities may have updated positions, velocities or internal states
     */
    void process(GameData gameData, World world);
}