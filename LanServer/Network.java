
/**
 * Implements opening an input stream between two sockets and ensures handling of objects being sent between connections
 */
public interface Network {

    /**
     * Sends Object {@link #obj} from the current class to Connection {@link #conn} 
     * @param conn The connection (socket) to send the object to
     * @param obj The object to send to the connection (socket)
     * @return True if the object was successfully sent or false if it failed to send
     */
    public boolean sendObject(Connection conn, Object obj);

    /**
     * Starts an ObjectInputStream to Connection {@link #conn} on a new Thread
     * @param conn The connection to start an InputStream to
     */
    public void startInputStream(Connection conn);

    /**
     * Closes the a connection including its Socket, InputStream, and OutputStream
     */
    public void closeConnection();
}
