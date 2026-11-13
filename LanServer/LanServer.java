import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A LAN (Local Area Network) server that handles connections with clients
 * <p>Implements {@link #Network}
 */
public class LanServer implements Network{

    /** The ServerSocket that the server is hosted on */
    private ServerSocket server;

    /** A list of all client Connections to the server */
    private ArrayList<Connection> connectedClients;

    /** The port the server is hosted on */
    private int port;
    
    /**
     * Constructs a new LanServer hosted on the local machine's LAN IPv4 Address
     * @param _port The port to host the server on
     */
    public LanServer(int _port){
        port = _port;
        // queue = new ArrayList<>();
        connectedClients = new ArrayList<>();
    }

    /**
     * Creates the server and starts a thread accepting new client connections
     */
    public void startServer(){
        try{
            //Creates a new ServerSocket connection
            server = new ServerSocket(port);

            //Server debug information relaying port and host IP information
            System.out.println("Server started on port " + port);
            System.out.println("Host IP: " + ListNets.getWirelessNetwork());

            //Thread that accepts clients as they connect and adds them to connectedClients
            Thread checkForClientConnection = new Thread(() -> {
                while(!server.isClosed()){
                    try{
                        //Accepts the next connection to the server
                        Socket clientSocket = server.accept();

                        //Creates a new Connection object with the client's socket and starts the input and output streams
                        Connection client = new Connection(clientSocket);

                        //Adds the client to the list of connected clients and prints the client's IP address to the terminal
                        connectedClients.add(client);
                        System.out.println("Client connected from " + clientSocket.getInetAddress().toString());

                        //Sends a message to the client confirming it has connected to the server
                        ObjectOutputStream out = client.getOutputStream();
                        out.writeObject("Successfully connected to " + ListNets.getWirelessNetwork() + " on port " + port);

                        //Starts the input stream to the client on a new thread
                        startInputStream(client);
                    } catch(Exception e){}
                }
            });
            //Starts the thread
            checkForClientConnection.start();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sends Object {@link #_object} from the server to client 
     * @param _client The client's Connection to send the object to
     * @param _object The object to send to the client
     * @return True if the object was successfully sent or false if it failed to send
     */
    public boolean sendObject(Connection _client, Object _object){
        try{
            //Gets the current output stream
            ObjectOutputStream out = _client.getOutputStream();
            
            //Sends an object to the client
            out.writeObject(_object);

            //Clears the output stream's buffer (reseting it so there's no leftover data)
                out.flush();

            //Returns true signaling that the object was successfully sent without errors
            return true;
        } catch(Exception e){
            //Returns false signaling that there was an error thrown
            return false;
        }
    }

    /**
     * Starts an ObjectInputStream from the client to this server on a new Thread
     * @param _client The client's Connection to start an InputStream from
     */
    public void startInputStream(Connection _client){
        Thread clientInputStream = new Thread(() -> {
            try{
                //Gets the client's input stream
                ObjectInputStream in = _client.getInputStream();

                //Handles all inputs sent to the server
                while(!_client.getSocket().isClosed()){
                    Object o = in.readObject();

                    //NetworkCommand handling
                    switch(o){
                        case NetworkCommand.CLOSE_CONNECTION:
                            closeConnection();
                            break;

                        default:
                            break;
                    }

                    //Object Type handling
                    switch(o.getClass().getSimpleName()){
                        case "Integer":
                            break;

                        case "String":
                            System.out.println(o);
                            break;

                        default:
                            break;
                    }
                }
                closeConnection();
            } 
            catch(Exception e){
                e.printStackTrace();
                closeConnection();
            }
        });
        //Starts the thread
        clientInputStream.start();
    }

    /**
     * Closes the server, all of its Connections, and terminates runtime with error code -1
     */
    public void closeConnection(){
        try{server.close();} catch(Exception e){}
        for(Connection client : connectedClients){
            sendObject(client, NetworkCommand.CLOSE_CONNECTION);
            try{
                client.getSocket().close();
            } catch(Exception e){e.printStackTrace();}
        }
        System.out.println("Server closed");
        System.exit(-1);
    }
}