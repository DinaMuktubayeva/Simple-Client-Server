import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A chat room server implemented with multithreading
 */
public class ChatServer {
    private static final int PORT = 9090;
    // A pool of 50 threads
    private static ExecutorService pool = Executors.newFixedThreadPool(5);
    // A list of usernames
    private static ArrayList<String> names = new ArrayList<>();
    // A list of currently active threads
    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("[Server] The chat server is running ...");

            while (true) {
                Socket client = listener.accept();
                ClientHandler clientThread = new ClientHandler(client, names, clients);

                // Execute a thread for the new client and add it to the list
                clients.add(clientThread);
                pool.execute(clientThread);
            }
        }
    }
}