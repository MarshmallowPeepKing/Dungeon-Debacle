public interface Client {
    /**
     * Sends Object {@link #obj} from the current class to the default connection
     * @param obj The object to send to the connection (socket)
     * @return True if the object was successfully sent or false if it failed to send
     */
    public boolean sendObject(Object obj);
}
