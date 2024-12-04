import java.net.*;
import java.io.*;

public class Server {
    public static final int PORT = 4000;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port: " + PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            DataInputStream input = new DataInputStream(socket.getInputStream());
            System.out.println("Received from client: " + input.readLine());

            PrintStream output = new PrintStream(socket.getOutputStream());
            output.println("Hello from server");

	    socket.close();
	    serverSocket.close();

        }catch (Exception e) {
            System.out.println("Couldn't start server: " + e.getMessage());
        } 
    }
}
