
/**
 * Abstract class for any object that can be in a room;
 * <p>Includes Players, Items, etc.
 */
public abstract class GameObject {

    /** The X coordinate of the object in its current room <p>Index starts at 0; null value is -1<p>Index increases as you move RIGHT*/
    int posX = -1;

    /** The Y coordinate of the object in its current room <p>Index starts at 0; null value is -1<p>Index increases as you move DOWN*/
    int posY = -1;

    /**
     * Gets the X coordinate of the object
     * @return The X coordinate of the object as an Integer
     */
    public int getX(){
        return posX;
    }

    /**
     * Gets the Y coordinate of the object
     * @return The Y coordinate of the object as an Integer
     */
    public int getY(){
        return posY;
    }

    /**
     * Gets the coordinates of the object
     * @return A coordinate pair of the X and Y position of the object (int[])
     */
    public int[] getPos(){
        return new int[] {posX, posY};
    }

    /**
     * Sets the X coordinate of the object
     * @param x The X coordinate to set the X position to
     */
    public void setX(int x){
        posX = x;
    }

    /**
     * Sets the Y coordinate of the object
     * @param y The Y coordinate to set the Y position to
     */
    public void setY(int y){
        posY = y;
    }

    /**
     * Sets the X and Y coordinates of the object
     * @param x The X coordinate to set the X position to
     * @param y The Y coordinate to set the Y position to
     */
    public void setPos(int x, int y){
        posX = x;
        posY = y;
    }
}
