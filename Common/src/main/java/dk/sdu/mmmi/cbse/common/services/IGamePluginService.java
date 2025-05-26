package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/*
 * Service interface for managing the lifecycle of game plugins.
 * Plugins are responsible for initializing and cleaning up entities or systems within the game world
 */

public interface IGamePluginService {

    /*
    * Called when the game plugin is started.
    * Responsible for adding necessary systems or entities to the game world.
    *
    * @param gameData the current game data. Must not be null.
    * @param world the current world model. Must not be null.
    *
    * @pre gameData != null && world !=null
    * @post relevant entities are added to the world
    */
    void start(GameData gameData, World world);

    /*
    * called when the game plugin is stopped.
    * Responsible for removing or cleaning up any entities created by this plugin.
    *
    * @param gameData The current game data; must not be null
    * @param world The current world model; must not be null
    *
    * @pre gameData != null && world != null
    * @post All entities added by this plugin are removed from the world
    */

    void stop(GameData gameData, World world);
}
