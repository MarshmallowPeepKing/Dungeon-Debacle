import java.util.*;

public class Main {
    /**
     * The entry point of the application.
     * Parses command-line options and starts the main processing loop.
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        //Creating a server
        LanServer server = new LanServer(8080);
        Thread serverThread = new Thread(() -> {
            server.startServer();
        });
        serverThread.start();

        //Hosting a game (this uses a scanner as we don't have GUI yet)
        System.out.println("How many players (2-16)");
        PlayerClient[] players = new PlayerClient[getIntRange(2, 16)];

        //Arbitrary list of names
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Dave", "Also Dave", "NoobSlayer6969", "<404> Error not found", "Steve", "Leonardo DaVinci", "Picasso", "Abraham_Lincoln77", "Master of Puppets", "Nobody", "AlexanderTheGr8t1059", "John Wilkes Photobooth", "xXKingGreatTheLegendXx", "NateWantsToBattle"));
        for(int i = 0; i < players.length; i++){
            players[i] = new PlayerClient(names.remove((int)(Math.random() * names.size())), "127.0.0.1", 8080);
        }

        for(int i = 0; i < players.length; i++){
            System.out.println(players[i]);
        }

    }

    public static int getIntRange(int min, int max){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while(num > max || num < min){
            System.out.println("Please input a number within the range");
            num = sc.nextInt();
        }
        sc.close();
        return num;
    }
}
