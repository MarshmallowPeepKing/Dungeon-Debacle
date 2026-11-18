public interface Server {
    /**
     * Sends Object {@link #obj} from the current class to Connection {@link #conn} 
     * @param conn The connection (socket) to send the object to
     * @param obj The object to send to the connection (socket)
     * @return True if the object was successfully sent or false if it failed to send
     */
    public boolean sendObject(Connection conn, Object obj);
}
