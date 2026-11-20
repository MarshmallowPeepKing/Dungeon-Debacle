import java.util.*;

public class Main {
    /**
     * The entry point of the application.
     * Parses command-line options and starts the main processing loop.
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {

        Player player = new Player("Jose");

        Room room = RoomDatabase.getRoom("Room 2");

        room.setPos((Player) player, 0, 0);

        Scanner sc = new Scanner(System.in);
        while(true){

            updateRoom(room);

            switch(sc.nextLine().toLowerCase()){
                case "left", "a":
                    moveLeft(room, player);
                    break;
                    
                case "right", "d":
                    moveRight(room, player);
                    break;
                    
                case "up", "w":
                    moveUp(room, player);
                    break;
                    
                case "down", "s":
                    moveDown(room, player);
                    break;
            }

            if(!room.gameObjectTypeExists(Item.class)){
                break;
            }
        }
        updateRoom(room);

        sc.close();
        System.out.println("You Win!");
    }
    
    static void clearTerminal(){
        try{new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();} catch(Exception e){}
        System.out.flush();
    }

    static void updateRoom(Room room){
        clearTerminal();
        System.out.println(room);
    }

    static void moveUp(Room room, GameObject player){
        if(player.getClass().equals(Player.class)) player = (Player) player;
        room.setPos(player, room.getPos(player)[0], room.getPos(player)[1] - 1);
    }
    static void moveDown(Room room, GameObject player){
        if(player.getClass().equals(Player.class)) player = (Player) player;
        room.setPos(player, room.getPos(player)[0], room.getPos(player)[1] + 1);
    }
    static void moveRight(Room room, GameObject player){
        if(player.getClass().equals(Player.class)) player = (Player) player;
        room.setPos(player, room.getPos(player)[0] + 1, room.getPos(player)[1]);
    }
    static void moveLeft(Room room, GameObject player){
        if(player.getClass().equals(Player.class)) player = (Player) player;
        room.setPos(player, room.getPos(player)[0] - 1, room.getPos(player)[1]);
    }

}
