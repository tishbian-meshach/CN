import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        DataInputStream reader = new DataInputStream(System.in);

            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

            // Send MAC address to server
            System.out.print("Enter the Physical Address (MAC): ");
            String macAddress = reader.readLine().trim();
            byte[] sendBytes = macAddress.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBytes, sendBytes.length, serverAddress, 1309);
            client.send(sendPacket);

            // Receive IP address from server
            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            client.receive(receivePacket);

            String ipAddress = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("The Logical Address (IP) is: " + ipAddress);
    }
}
