public class PlayerClient extends LanClient{
    
    private String username;

    public PlayerClient(String username, String hostIP, int port){
        super(hostIP, port);
        this.username = username;

        Thread clientThread = new Thread(() -> {
            joinServer();
        });
        clientThread.start();
    }

    @Override
    public String toString(){
        return username;
    }
}
