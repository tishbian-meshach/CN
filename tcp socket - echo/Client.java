import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {

        System.out.println("Trying to connect to the server...");

        try {
            Socket socket = new Socket("localhost", Server.PORT);
            PrintStream output = new PrintStream(socket.getOutputStream());
            output.println("Hi from client");

            DataInputStream input = new DataInputStream(socket.getInputStream());
            System.out.println("Received from server: " + input.readLine());
            socket.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
