import java.io.*;
import java.net.*;

class Client {
    public static void main(String args[]) {

        try {
            Socket socket = new Socket("localhost",Server.PORT);
            PrintStream output = new PrintStream(socket.getOutputStream());
            DataInputStream serverinput = new DataInputStream(socket.getInputStream());
            DataInputStream userinput = new DataInputStream(System.in);

            System.out.println("Type 'end' to Quit");

            while (true) {
                System.out.print("Message to Server: ");
                String clientMessage = userinput.readLine();
                output.println(clientMessage); // Send message to server
                if (clientMessage.equals("end")) {
                    break; // Exit if the user types "end"
                }
                String serverMessage = serverinput.readLine(); // Receive message from server
                System.out.println("Server: " + serverMessage);
            }

            socket.close();
            System.out.println("Client disconnected");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
