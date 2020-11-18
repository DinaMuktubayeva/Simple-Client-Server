import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", PORT);
        System.out.println("[Client] : Connected to server");

        // Sending messages to the server
        PrintWriter sender = new PrintWriter(socket.getOutputStream());
        sender.println("is it working?");
        sender.flush();

        // Receiving messages from the server
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader receiver = new BufferedReader(in);

        String serverMsg = receiver.readLine();
        System.out.println("[Server] : " + serverMsg);

        // Socket must be closed at the end of the session
        System.out.println("[Client] Closing" + serverMsg);
        socket.close();
    }
}