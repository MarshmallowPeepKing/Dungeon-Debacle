import java.io.*;
import java.net.*;

/**
 * A list of possible network commands that can be used when sending objects between the client and server in place of an object
 * {@link #CLOSE_CONNECTION}
 */
enum NetworkCommand{

    /**
     * NetworkCommand to close a socket connection
     */
    CLOSE_CONNECTION
}

/**
 * Represents the two-way connection stream of a java.net.socket object
 */
class Connection {

    /** The socket that the connection is utilizing*/
    private Socket socket;

    /** An ObjectOutputStream from {@link #socket} to other sockets*/
    private ObjectOutputStream out;

    /** An ObjectInputStream from other sockets to {@link #socket}*/
    private ObjectInputStream in;

    /**
     * Constructs a new Connection stream to and from {@link #socket}
     * @param socket The socket that is hosting the input and output streams
     */
    Connection(Socket socket){
        this.socket = socket;
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e){}
    }

    /**
     * Gets the socket's ObjectOutputStream
     * @return The socket's ObjectOutputStream
     */
    ObjectOutputStream getOutputStream(){
        return out;
    }

    /**
     * Gets the socket's ObjectInputStream
     * @return The socket's ObjectInputStream
     */
    ObjectInputStream getInputStream(){
        return in;
    }

    /**
     * Gets the Connection's socket
     * @return The Connection's socket
     */
    Socket getSocket(){
        return socket;
    }
}
