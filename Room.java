
/**
 * Class for a Room containing GameObjects<p>
 * Methods:<p>
 * {@link #setObjectAtPosition()}<p>
 * {@link #setPos()}<p>
 * {@link #getPos()}<p>
 * {@link #posFree()}<p>
 * {@link #gameObjectExists()}<p>
 * {@link #gameObjectTypeExists()}<p>
 * {@link #toString()}<p>
 */
public class Room {
    
    /**
     * The Room stored as a 2D array 
     */
    private GameObject[][] room;

    /**
     * Constructs a new Room
     * @param _room The room layout as a GameObject[][]
     */
    public Room(GameObject[][] _room){
        room = _room;
    }

    
    /**
     * Sets the GameObject at a location to a new object and replaces the previous location with Empty if the object existed previously
     * @param obj The object to add to the location
     * @param objectPos The position of the object you want to add as coordinates (int x, int y); coordinates should be (-1, -1) to represent null
     * @param x The x coordinate of the new destination
     * @param y The y coordinate of the new destination
     */
    private void setObjectAtPosition(GameObject obj, int[] objectPos, int x, int y){
        room[y][x] = obj;
        obj.setPos(x,y);
        if(objectPos[0] != -1 && objectPos[1] != -1){
            room[objectPos[1]][objectPos[0]] = RoomDatabase.Empty();
        }
    }

    /**
     * Sets the position of an object in a room
     * @param obj The object to set the position of
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public void setPos(GameObject obj, int x, int y){

        //Gets the current position of the object
        int[] objectPos = obj.getPos();

        //If the position is within the bounds of the room:
        if((y >= 0 && y < room.length) && (x >= 0 && x < room[0].length)){

            GameObject itemAtLocation = room[y][x];

            //Switch statement for handling different objects that could be in the location
            //getClass().getSimpleName() returns the name of the class as a String
            switch(itemAtLocation){

                //If the object is an item...
                case Item item:

                    //If the object moving to the spot is a Player, add the item to the player's inventory
                    if(obj instanceof Player){
                        Player player = (Player) obj;
                        player.addItem((Item) item);
                    }
                
                    //Set obj's position to (x,y) and it's previous space with Empty
                    setObjectAtPosition(obj, objectPos, x, y);

                    break;

                //If the object is Emtpy...
                case RoomDatabase.Empty _:{

                    //Set obj's position to (x,y) and it's previous space with Empty
                    setObjectAtPosition(obj, objectPos, x, y);
                }

                //Else...
                default:
                    break;
            }
        }
    }

    /**
     * Finds the location of an object in the room
     * @param obj The object to find
     * @return The location of the object as coordinates (int[]); Returns (-1,-1) if not found
     */
    public int[] getPos(GameObject obj){
        for(int y = 0; y < room.length; y++){
            for(int x = 0; x < room[y].length; x++){
                if(room[y][x].equals(obj)){
                    return new int[]{x, y};
                }
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * Checks if a location in the room is empty
     * @param x The X coordinate of the location
     * @param y The Y coordinate of the Location
     * @return True if the location is empty
     */
    public boolean posFree(int x, int y){
        return room[y][x].getClass() == RoomDatabase.Empty.class;
    }

    /**
     * Checks if a specific GameObject exists in the room
     * @param obj The object to find
     * @return True if the object is found in the room
     */
    public boolean gameObjectExists(GameObject obj){
        for(int y = 0; y < room.length; y++){
            for(int x = 0; x < room[y].length; x++){
                if(room[y][x].equals(obj)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if any GameObject of a specific type exists in a room
     * @param c the GameObject class to find
     * @return True if any object of the given class is found in the room
     */
    public boolean gameObjectTypeExists(Class<? extends GameObject> c){
        for(int y = 0; y < room.length; y++){
            for(int x = 0; x < room[y].length; x++){
                if(c.isAssignableFrom(room[y][x].getClass())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override 
    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString(){
        String result = "";
        for(int row = 0; row < room.length; row++){
            for(int col = 0; col < room[row].length; col++){
                Object obj = room[row][col];
                switch(obj){

                    //If it's a Player...
                    case Player _:
                        result += "[ p ]";
                        break;
                    
                    //If it's an Item...
                    case Item i:
                        if(i instanceof RoomDatabase.DummyItem)
                            result += "[-d-]";
                        else
                            result += "[-i-]";
                        break;

                    //If it's a wall...
                    case RoomDatabase.Wall _:
                        result += "[###]";
                        break;
                    
                    //else
                    default:
                        result += "[   ]";
                        break;
                }
            }
            result += "\n";
        }
        return result;
    }
}
