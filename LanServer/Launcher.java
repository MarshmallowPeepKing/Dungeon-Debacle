import java.util.*;

/**
 * The application launcher that launches various required network class applications
 */
public class Launcher {
    
    /**
     * Serves as the main entry point for the application.
     * Initializes core services and starts the user interface.
     *
     * @param args command-line arguments passed to the application (not used)
     */
    public static void main(String[] args) {

        //Clears the terminal
        try{new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();} catch(Exception e){}
        System.out.flush();
        
        //Temporary scanner used to get whether or not to lauch a server or client (this will be automated in the future with a GUI)
        Scanner sc = new Scanner(System.in);
        String result = "";
        System.out.println("Launching Server or Client?");

        //Repeats until a valid response is recorded
        while(!result.toLowerCase().equals("server") && !result.toLowerCase().equals("client")){
            result = sc.nextLine();
        }
        sc.close(); //Closes the scanner to prevent a memory leak

        //If launching a server, runs a server on a new thread
        //Threads are utilized to allow the launcher to continue running other code while managing the server separately
        if(result.toLowerCase().equals("server")){
            Thread serverThread = new Thread(() -> {
                LanServer server = new LanServer(8080);
                server.startServer();
            });
            serverThread.start();
        }

        //Otherwise if launching a client, runs a client on a new thread
        //Threads are utilized to allow the launcher to continue running other code while managing the server separately
        else if(result.toLowerCase().equals("client")){

            String hostIP = "192.168.56.1";
            int port = 8080;

            Thread clientThread = new Thread(() -> {
                LanClient client = new LanClient(hostIP, port);
                client.joinServer();
            });
            clientThread.start();
        }
    }
}