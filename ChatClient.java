import java.io.*;
import java.net.*;
import java.util.Scanner;

class ChatClient {
    public static void main(String[] args) throws Exception {
        // Create a scanner for user input
        Scanner inFromUser = new Scanner(System.in);

        // Create client socket and connect to server
        Socket clientSocket = new Socket("localhost", 1804);

        // Create output stream attached to the socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream attached to the socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Get a sentence from the user
        System.out.print("Enter a Message : ");
        String sentence = inFromUser.nextLine();

        // Send the sentence to the server
        outToServer.writeBytes(sentence + '\n');
        System.out.println("Message was sent to server : " + sentence);

        // Read the capitalized sentence from the server
        String modifiedSentence = inFromServer.readLine();

        // Print the server's response
        System.out.println("FROM SERVER : " + modifiedSentence);

        // Close the client socket
        clientSocket.close();
        inFromUser.close();
    }

}
