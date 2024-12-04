import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        String[] ipAddresses = {"165.165.80.80", "165.165.79.1"};
        String[] macAddresses = {"6A:08:AA:C2", "8A:BC:E3:FA"};

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is running...");

            while (true) {
                
                    Socket clientSocket = serverSocket.accept();
                    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                
                    System.out.println("Client connected.");

                    // Read IP from client
                    String ip = input.readUTF();
                    String macAddress = "MAC Address not found";

                    // Find matching MAC address
                    for (int i = 0; i < ipAddresses.length; i++) {
                        if (ip.equals(ipAddresses[i])) {
                            macAddress = macAddresses[i];
                            break;
                        }
                    }
                    // Send MAC address to client
                    output.writeUTF(macAddress);
                    System.out.println("Response sent to client.");
                   }
}
}
