import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
            Socket socket = new Socket("127.0.0.1", 5000);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream reader = new DataInputStream(System.in);
            // Send IP address to server
            System.out.print("Enter the Logical address (IP): ");
            String ipAddress = reader.readLine();
            output.writeUTF(ipAddress);

            // Receive and display MAC address
            String macAddress = input.readUTF();
            System.out.println("The Physical Address is: " + macAddress);
    }
}
