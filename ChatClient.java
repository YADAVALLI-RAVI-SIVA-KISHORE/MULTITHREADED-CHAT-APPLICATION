import java.io.*;
import java.net.*;

// Client program for chat
public class ChatClient {
    public static void main(String[] args) {
        try {
            // Connect to server on localhost and port 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to chat server.");

            // Thread to read messages from server
            new ReadThread(socket).start();

            // For sending messages
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String msg;
            // Keep reading input from keyboard and send to server
            while ((msg = keyboard.readLine()) != null) {
                out.println(msg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Thread class for reading messages from server
class ReadThread extends Thread {
    Socket socket;

    ReadThread(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            // Keep reading from server and print
            while ((msg = in.readLine()) != null) {
                System.out.println("Message : " + msg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
