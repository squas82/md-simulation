package de.haw.md.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * Static values like network topology, sending attemts, neighbours-map and package ID
 * 
 * @author Sascha Waltz
 *
 */
public class StaticValues {
	
    public static String[] ROUTES = { "A-C", "A-B", "B-C", "B-D", "C-H", "C-F", "F-H", "H-I", "D-E", "E-F", "E-G", "F-G", "F-I" };

    public static String[] NODES = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
	
	public static int MAX_ATTEMPTS_TO_TIMEOUT = 10;

	public static final Map<String, String[]> NEIGHBOURS = createMap();

	public static final int SENDING_ATTEMPTS = 5;

	private static Map<String, String[]> createMap() {
		Map<String, String[]> myMap = new HashMap<>();
        myMap.put("A", new String[]{"C", "B"});
		myMap.put("B", new String[]{"A", "C", "D"});
        myMap.put("C", new String[]{"A", "B", "F", "H"});
        myMap.put("D", new String[]{"B", "E"});
        myMap.put("E", new String[]{"D", "F", "G"});
        myMap.put("F", new String[]{"C", "E", "G", "H", "I"});
        myMap.put("G", new String[]{"E", "F"});
        myMap.put("H", new String[]{"C", "F", "I"});
        myMap.put("I", new String[]{"F", "H"});

		return myMap;
	}
	
	public static String generatePackageID() {
		return UUID.randomUUID().toString();
	}

	public static int randInt(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

}
