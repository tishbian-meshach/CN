import java.io.*;
import java.net.*;

class Client {
    public static void main(String args[]) {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 4000);
            PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader serverinput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userinput = new BufferedReader(new InputStreamReader(System.in));

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
