import java.net.*;
import java.util.*;
import static java.lang.System.out;


/**
 * Gets a list of various networ information such as IPv4 addresses
 */
public class ListNets {

    /**
     * Gets the wireless LAN IPv4 address that the host device is using
     * @return String containing the wireless LAN IPv4 address of the host device
     */
    static String getWirelessNetwork(){
        try{
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets))
                if(isWirelessNetwork(netint)){
                    for(InetAddress inetAddress : Collections.list(netint.getInetAddresses())){
                        if(!inetAddress.toString().contains("wireless")){
                            return inetAddress.toString().substring(1);
                        }
                    }
                }
        } catch(Exception e){}
        return null;
    }

    /**
     * Checks if a network is wireless utilizing the Intel Wi-Fi driver
     * @param netint The network interface to check
     * @return True if the network uses the Intel Wi-Fi driver
     */
    static boolean isWirelessNetwork(NetworkInterface netint){
        try{
            //The desired LAN IPv4 address is contained under this NetworkInterface
            if(netint.getDisplayName().contains("Intel(R) Wi-Fi")){
                return true;
            }
        } catch(Exception e){}
        return false;
    }

    //
    /**
     * Displays a list of all network information (the equivalent of using the ipconfig command in the command prompt)
     * @throws SocketException
     * @author Code provided from Oracle's website under java tutorials (https://docs.oracle.com/javase/tutorial/networking/nifs/listing.html)
     */
    static void displayInterfaceInformation() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)){
            out.printf("Display name: %s\n", netint.getDisplayName());
            out.printf("Name: %s\n", netint.getName());
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                out.printf("InetAddress: %s\n", inetAddress);
            }
            out.printf("\n");
        }
    }
}  