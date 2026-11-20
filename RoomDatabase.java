import java.util.*;

/**
 * Information class that contains a database of all rooms<p>
 * HashMap defined as {@literal HashMap<String, Room>}
 */
public class RoomDatabase {

    /**
     * Type of GameObject that represents an empty space in a room
     */
    public static class Empty extends GameObject{}

    /**
     * Type of GameObject that represents a wall in a room<p>
     * Walls block movement, projectiles, and attacks
     */
    public static class Wall extends GameObject{}

    /**
     * Database containing all room presets<p>
     * Stored in a {@literal HashMap<String, Room>}<p>
     * Naming convention for Rooms utilizes Title Case
     */
    private static HashMap<String, Room> rooms = new HashMap<>(Map.of(

        /* Room 1
        [   ][   ][###][   ]
        [ i ][   ][   ][   ]
        [###][###][   ][###]
        [   ][   ][ i ][###]
        */
        "Room 1", new Room(new GameObject[][]{
            {Empty(),   Empty(),    Wall(),     Empty()},
            {Item(),    Empty(),    Empty(),    Empty()},
            {Wall(),    Wall(),     Empty(),    Wall()},
            {Empty(),   Empty(),    Item(),     Wall()}
        }),

        /* Room 2
        [   ][   ][   ][###][###][ i ][   ]
        [###][###][   ][###][   ][###][   ]
        [   ][   ][   ][   ][   ][   ][   ]
        [   ][   ][   ][   ][###][   ][   ]
        [   ][   ][###][   ][   ][###][ i ]
        [ i ][   ][###][   ][ i ][###][###]
        */
        "Room 2", new Room(new GameObject[][]{
            {Empty(),   Empty(),    Empty(),    Wall(),     Wall(),     Item(),     Empty()},
            {Wall(),    Wall(),     Empty(),    Wall(),     Empty(),    Wall(),     Empty()},
            {Empty(),   Empty(),    Empty(),    Empty(),    Empty(),    Empty(),    Empty()},
            {Empty(),   Empty(),    Empty(),    Empty(),    Wall(),     Empty(),    Empty()},
            {Empty(),   Empty(),    Wall(),     Empty(),    Empty(),    Wall(),     Item()},
            {Item(),    Empty(),    Wall(),     Empty(),    Item(),     Wall(),     Wall()}
        })
    
    ));

    /**
     * Gets a room from the database with the given name
     * @param name The name of the room
     * @return The room object with the given name in the database
     */
    public static Room getRoom(String name){
        return rooms.get(name);
    }

    /**
     * Creates a new Wall
     * @return A new {@link #Wall} object
     */
    public static Wall Wall(){
        return new Wall();
    }

    /**
     * Creates a new Empty space
     * @return A new {@link #Empty} object
     */
    public static Empty Empty(){
        return new Empty();
    }

    /**
     * Creates a new Item (This is used as a dummy until actual item classes are implemented)
     * @return A new {@link #Item} object
     */
    public static Item Item(){
        return new DummyItem();
    }

    //DummyItem to use as a placeholder until actual item objects are created
    public static class DummyItem extends Item{}
}