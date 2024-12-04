import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) {

        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("Server started on port 4000");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader clientinput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userinput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String clientMessage = clientinput.readLine();
                if (clientMessage == null || clientMessage.equals("end")) {
                    break; // Exit if the client disconnects or sends "end"
                }
                System.out.println("Client: " + clientMessage);
                System.out.print("Message to Client: ");
                String serverMessage = userinput.readLine();
                output.println(serverMessage); // Send message to client
            }

            System.out.println("Client disconnected");
            serverSocket.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
