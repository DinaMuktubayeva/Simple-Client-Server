import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Broadcasts user's messages
 */
public class ClientHandler implements Runnable {
    private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<String> names;
    private ArrayList<ClientHandler> clients;

    ClientHandler(Socket socket, ArrayList<String> names, ArrayList<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.names = names;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        System.out.println("[Server] Connected to client");

        try {
            // Getting a unique username
            while (true) {
                out.println("Enter a unique username");
                name = in.readLine();
                if (!names.contains(name)){
                    names.add(name);
                    break;
                }
            }

            while (true) {
                // Get user's message
                String msg = in.readLine();

                if (msg.equals("quit")) {
                    msg = "[" + name + "] is leaving";
                    printToAll(msg);
                    break;
                }

                // Broadcast the message
                printToAll("[" + name + "] " + msg);
            }

        } catch (Exception e) {
            System.err.println(e);
            System.out.println(e.getStackTrace());
        } finally {
            try {
                // Socket must be closed at the end of the session
                System.out.println("[Server] Closing " + socket);
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println("[Server] Error closing " + socket);
                System.err.println(e.getStackTrace());
            }

            System.out.println("[Server] Successfully closed " + socket);
        }
    }

    public void printToAll(String msg) {
        for (ClientHandler client : clients)
            client.out.println(msg);
    }
}