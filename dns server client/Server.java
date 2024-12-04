import java.io.IOException;
import java.net.*;

public class Server {
    private static String getIpAddress(String host, String[] hosts, String[] ips) {
        for (int i = 0; i < hosts.length; i++) {
            if (hosts[i].equalsIgnoreCase(host.trim())) {
                return ips[i];
            }
        }
        return "Host Not Found";
    }

    public static void main(String[] args) throws IOException {
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ips = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16"};

        System.out.println("DNS Server is running...");

        DatagramSocket serverSocket = new DatagramSocket(8080);

        while (true) {
            byte[] buffer = new byte[1024];

            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(requestPacket);

            String receivedHost = new String(requestPacket.getData(), 0, requestPacket.getLength());
            System.out.println("Received request for: " + receivedHost);

            String response = getIpAddress(receivedHost, hosts, ips);

            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                responseData,
                responseData.length,
                requestPacket.getAddress(),
                requestPacket.getPort()
            );

            serverSocket.send(responsePacket);
        }
    }
}
