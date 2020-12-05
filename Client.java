import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Receives user's input and sends to the server
 */
public class Client {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", PORT);
        ServerConnection serverConn = new ServerConnection(socket);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // ServerConnection allows to receive broadcasted messages in real time
        (new Thread(serverConn)).start();

        while (true) {
            System.out.print("> ");

            // Getting user's input
            String msg = keyboard.readLine();

            // Sending to the server
            out.println(msg);

            if (msg.equals("quit"))
                break;
        }

        keyboard.close();
        out.close();
    }

}