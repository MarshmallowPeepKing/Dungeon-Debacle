import java.io.*;
import java.net.*;

/**
 * The main client class that manages client connections to the LAN server
 * <p>Implements {@link #Network}
 */
public class LanClient implements Network{
    // public ArrayList<QueueObject> queue = new ArrayList<>();

    /** The LAN IPv4 address of the server */
    private String hostIP;

    /** The port number that the server is hosting from */
    private int port;

    /** The Connection to the server which includes the ServerSocket, ObjectInputStream, and ObjectOutputStream */
    private Connection server;

    /**
     * A LanClient that handles a connection to a server
     * @param hostIP The LAN IPv4 address of the server
     * @param port The port number the server is using
     */
    public LanClient(String hostIP, int port){
        this.hostIP = hostIP;
        this.port = port;
    }

    /**
     * Creates a Connection to the server and opens an ObjectInputStream and ObjectOutputStream
     */
    public void joinServer(){
        try{
            //Connect to the Server by creating a new socket with the LAN IPv4 IP Address and port number
            server = new Connection(new Socket(hostIP, port));

            //Starts an ObjectInputStream with the server on a thread
            startInputStream(server);
            
            //Main loop implementing client logic
            while(!server.getSocket().isClosed()){
                // Implement logic here
            }

            closeConnection();
            
        } catch(Exception e) {e.printStackTrace();}
    }

    /**
     * Sends Object {@link #_object} from the current class to the Connection {@link #_server} 
     * @param _server The server's Connection
     * @param _object The object to send to the server
     * @return True if the object was successfully sent or false if it failed to send
     */
    public boolean sendObject(Connection _server, Object _object){
        if(!_server.getSocket().isClosed()){
            try{
                //Gets the current output stream
                ObjectOutputStream out = server.getOutputStream();
                
                //Sends an object to the server
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
        //If the socket is no longer connected to the server, close the connection
        closeConnection();

        //Returns false signaling that there was an error thrown
        return false;
    }

    /**
     * Starts an ObjectInputStream from the server to this client on a new Thread
     * @param _server The server's Connection to recieve the InputStream from
     */
    public void startInputStream(Connection _server){
        Thread serverInputStream = new Thread(() -> {
            try{
                //Gets the current InputStream from the server
                ObjectInputStream in = server.getInputStream();
                
                //Handles all inputs sent to the client
                while(!server.getSocket().isClosed()){
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
            } catch(Exception e){e.printStackTrace();}
        });
        //Starts the thread
        serverInputStream.start();
    }

    /**
     * Closes the client connection and terminates runtime with error code -1
     */
    public void closeConnection(){
        try{server.getSocket().close();} catch(Exception e){}
        System.exit(-1);
    }

}
