package com.java8.study.udemy;

import java.util.*;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오후 7:51
 **/


public class LocationDemo {
    private static Map<Integer, Location> locations = new HashMap<>();
    public static final String WEST = "W";
    public static final String EAST = "E";
    public static final String SOUTH = "S";
    public static final String NORTH = "N";
    public static final String QUIT = "Q";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        locations.get(1).addExit(WEST, 2);
        locations.get(1).addExit(EAST, 3);
        locations.get(1).addExit(SOUTH, 4);
        locations.get(1).addExit(NORTH, 5);
        //locations.get(1).addExit(QUIT, 0); //Adding Quit is duplicated

        locations.get(2).addExit(NORTH, 5);
        //locations.get(2).addExit(QUIT, 0);

        locations.get(3).addExit(WEST, 1);
        //locations.get(3).addExit(QUIT, 0);

        locations.get(4).addExit(NORTH, 1);
        locations.get(4).addExit(WEST, 2);
        //locations.get(4).addExit(QUIT, 0);

        locations.get(5).addExit(SOUTH, 1);
        locations.get(5).addExit(WEST, 2);
        //locations.get(5).addExit(QUIT, 0);

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", QUIT);
        vocabulary.put("NORTH", NORTH);
        vocabulary.put("SOUTH", SOUTH);
        vocabulary.put("EAST", EAST);
        vocabulary.put("WEST", WEST);

        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0) {
                //Game Quit
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");
            exits.keySet()
                    .forEach(exit -> System.out.print(exit+", "));
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                Optional<String> found = Arrays.stream(words)
                        .filter(vocabulary::containsKey)
                        .findFirst();

                if(found.isPresent()) {
                    direction = vocabulary.get(found.get());
                }
            }
            if(exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
