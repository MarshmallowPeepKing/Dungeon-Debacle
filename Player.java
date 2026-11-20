import java.util.ArrayList;

public class Player extends GameObject {

    /** A list of every item the player has access to */
    ArrayList<Item> inventory = new ArrayList<>();

    /** The maximum number of items a player can have in their inventory */
    int maxInvSize = 10;

    /** The index of the currently selected inventory slot */
    int selectedSlot;



    /** The name of the player<p>This is used for display purposes */
    String name;

    /**
     * Constructs a new player object
     * @param name The name of the player
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Adds an item to the inventory, if there's space
     * @param newItem the item to be added
     */
    public void addItem(Item newItem) {
        if (maxInvSize > inventory.size()) {
            inventory.add(newItem);
        }
    }

    /**
     * Selects a new slot in the inventory,
     * provided that there is an item in it
     * @param i the number of the inventory slot being selected
     */
    public void selectItem(int i) {
        if (i < inventory.size()) {
            selectedSlot = i;
        }
    }

    /**
     * Uses the currently selected item
     */
    public void useItem() {
        inventory.get(selectedSlot).use();
    }


    public void move() {
        //implementation requires room
    }
}
