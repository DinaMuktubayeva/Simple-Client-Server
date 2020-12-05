import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Takes user input and sends it to the server
 */
public class Client {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", PORT);
        ServerConnection serverConn = new ServerConnection(socket);

        // User input
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        // Output to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(serverConn).start();

        while (true) {
            System.out.print("> ");

            // Reading user input
            String command = keyboard.readLine();
            out.println(command);

            if (command.equals("quit"))
                break;            
        }
        
        keyboard.close();
        out.close();
    }
}