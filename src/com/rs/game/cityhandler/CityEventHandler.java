package com.rs.game.cityhandler;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.rs.Settings;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

public final class CityEventHandler {

    private static final Map<Integer, CityEvent> cityEvents = new HashMap<Integer, CityEvent>();

    private static final Logger logger = Logger.getLogger(CityEventHandler.class
            .getCanonicalName());

    private static final String PATH = Paths.get(
            System.getProperty("user.dir"),
            "src",
            "com",
            "rs",
            "game",
            "cityhandler",
            "impl"
    ).toString();

    public static final boolean registerCitys() {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        System.out.println("Resolved PATH: " + PATH);

        File directory = new File(PATH);
        if (!directory.exists()) {
            System.out.println("Directory does not exist: " + PATH);
            return false;
        }
        if (!directory.isDirectory()) {
            System.out.println("Provided PATH is not a directory: " + PATH);
            return false;
        }
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in directory: " + PATH);
            return false;
        }
        //C:\Users\andre\Documents\GitHub\Avalon\Avalon\src\com\rs\game\cityhandler\impl
        //C:\Users\andre\Documents\GitHub\Avalon\src\com\rs\game\cityhandler\impl

        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            try {
                String className = file.getName().replace(".java", "");
                CityEvent event = (CityEvent) Class.forName("com.rs.game.cityhandler.impl." + className).newInstance();
                if (!event.init()) {
                    return false;
                }
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public static final boolean reload() throws Throwable {
        cityEvents.clear();
        return registerCitys();
    }

    public static final boolean handleNPCClick(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick(player, npc);
    }

    public static boolean handleNPCClick2(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick2(player, npc);
    }

    public static boolean handleNPCClick3(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick3(player, npc);
    }

    public static boolean handleNPCClick4(Player player, NPC npc, int npcId) {
        CityEvent cityEvent = cityEvents.get(npcId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleNPCClick4(player, npc);
    }

    public static final boolean handleObjectClick(Player player, WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick(player, object);
    }

    public static final boolean handleObjectClick2(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick2(player, object);
    }

    public static final boolean handleObjectClick3(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick3(player, object);
    }

    public static final boolean handleObjectClick4(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick4(player, object);
    }

    public static final boolean handleObjectClick5(Player player,
    		WorldObject object, int objectId) {
        CityEvent cityEvent = cityEvents.get(objectId);
        if (cityEvent == null)
            return false;
        return cityEvent.handleObjectClick5(player, object);
    }

    public static boolean registerNPCs(int npcId, CityEvent cityEvent) {
        if (cityEvents.containsKey(npcId)) {
            logger.info("City: " + cityEvent.getClass().getCanonicalName()
                    + " Id => " + npcId+ ", already registered with "
                    + cityEvents.get(npcId).getClass().getCanonicalName() + ".");
            return false;
        }
        cityEvents.put(npcId, cityEvent);
        return true;
    }

    public static boolean registerObjects(int objectId, CityEvent cityEvent) {
        if (cityEvents.containsKey(objectId)) {
            logger.info("City: " + cityEvent.getClass().getCanonicalName()
                    + " Id => " + objectId + " already registered with "
                    + cityEvents.get(objectId).getClass().getCanonicalName()
                    + ".");
            return false;
        }
        cityEvents.put(objectId, cityEvent);
        return true;
    }

	public static boolean handleItemOnObject(Player player, WorldObject object, Item item) {
		 CityEvent cityEvent = cityEvents.get(object.getId());
	        if (cityEvent == null)
	            return false;
	        return cityEvent.handleItemOnObject(player, object, item);
	}
}