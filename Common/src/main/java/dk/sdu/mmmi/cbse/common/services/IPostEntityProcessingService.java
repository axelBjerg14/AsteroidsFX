package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Called after the main entity processing. Typically used for post-processing logic
 *
 */
public interface IPostEntityProcessingService {

    /*
    * Executing post-processing on entities after the main game logic has run
    *
    * @param gameData the game data context; must not be null
    * @param world the world containing all the entities, must not be null
    *
    * @pre gameData != null && world != null
    * @post Entities may be removed, merged or have their state adjusted based on interactions
    */
    void process(GameData gameData, World world);
}
