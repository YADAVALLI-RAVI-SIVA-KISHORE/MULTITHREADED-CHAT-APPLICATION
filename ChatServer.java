import java.io.*;
import java.net.*;

class ChatServer {
    public static void main(String[] args) throws Exception {
        // Create a welcoming socket at port 1804
        ServerSocket welcomeSocket = new ServerSocket(1804);

        while (true) {
            System.out.println("Now the server is running.\nWaiting for a client to connect.....");

            // Waiting for a client to connect
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("A client was connected : " + connectionSocket.getInetAddress());

            // Create input stream from socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            // Create output stream to socket
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // Read a line from the client
            String clientSentence = inFromClient.readLine();
            System.out.println("Message received from client : " + clientSentence);

            // Process the line: convert to upper case
            String capitalizedSentence = clientSentence.toUpperCase() + '\n';

            // Send the capitalized line back to the client
            outToClient.writeBytes(capitalizedSentence);
            System.out.println("Message was sent to client : " + capitalizedSentence);

            // Close the client-specific socket
            connectionSocket.close();
            System.out.println("Client was disconnected.\n");
        }
    }

}
