import java.util.ArrayList;

public class Player {

    /** A list of every item the player has access to */
    ArrayList<Item> inventory = new ArrayList<>();
    int maxInvSize = 10;
    int selectedSlot;

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
