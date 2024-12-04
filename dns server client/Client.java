import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        DataInputStream reader = new DataInputStream(System.in);
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress serverAddress =InetAddress.getLocalHost();
        int serverPort = 8080;

        System.out.print("Enter the hostname: ");
        String hostname = reader.readLine();

        byte[] requestData = hostname.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
        clientSocket.send(requestPacket);

        byte[] buffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(responsePacket);

        String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
        System.out.println("IP Address: " + response);

        clientSocket.close();
    }
}
