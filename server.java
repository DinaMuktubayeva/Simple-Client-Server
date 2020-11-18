import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("[Server] Waiting for client connection ...");
        Socket socket = serverSocket.accept();
        System.out.println("[Server] Connected to client");

        // Receiving messages from the client
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader receiver = new BufferedReader(in);

        String clientMsg = receiver.readLine();
        System.out.println("[Client] : " + clientMsg);

        // Sending messages to the client
        PrintWriter sender = new PrintWriter(socket.getOutputStream());
        sender.println("yes, it is");
        sender.flush();

        // Socket must be closed at the end of the session
        System.out.println("[Server] Closing");
        socket.close();
        serverSocket.close();
    }
}