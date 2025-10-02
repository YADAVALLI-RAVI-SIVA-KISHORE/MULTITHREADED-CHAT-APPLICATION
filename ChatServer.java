import java.io.*;
import java.net.*;
import java.util.*;

// Server program for chat
public class ChatServer {
    // To store all connected client writers
    static Vector<PrintWriter> clientList = new Vector<>();

    public static void main(String[] args) {
        try {
            // Create server socket on port 1234
            ServerSocket server = new ServerSocket(1234);
            System.out.println("Server started. Waiting for clients...");

            // Keep accepting clients
            while (true) {
                Socket socket = server.accept(); // accept client connection
                System.out.println("Client connected.");

                // Create a handler (thread) for this client
                ClientHandler handler = new ClientHandler(socket);
                handler.start(); // start the thread
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Thread class to handle each client
class ClientHandler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    ClientHandler(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            // Input from client
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output to client
            out = new PrintWriter(socket.getOutputStream(), true);

            // Add this client to the list
            ChatServer.clientList.add(out);

            String msg;
            // Keep reading messages from client
            while ((msg = in.readLine()) != null) {
                System.out.println("Message from client : " + msg);

                // Send the message to all clients
                for (PrintWriter writer : ChatServer.clientList) {
                    writer.println(msg);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
