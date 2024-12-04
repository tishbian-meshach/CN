import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        String[] macAddresses = {"D4:3D:7E:12:A3:D9"};
        String[] ipAddresses = {"10.0.3.186"};

        DatagramSocket server = new DatagramSocket(1309);
        System.out.println("Server is running and waiting for requests...");

        byte[] buffer = new byte[1024];

            while (true) {
                // Receive MAC address from client
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                server.receive(receivePacket);

                String mac = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Find corresponding IP address
                String response = "MAC Address not found";
                for (int i = 0; i < macAddresses.length; i++) {
                    if (mac.equals(macAddresses[i])) {
                        response = ipAddresses[i];
                        break;
                    }
                }

                // Send response to client
                byte[] responseBytes = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
                server.send(sendPacket);
            }
      }
}
