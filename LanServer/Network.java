
/**
 * Implements opening an input stream between two sockets and ensures handling of objects being sent between connections
 */
public interface Network {

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
