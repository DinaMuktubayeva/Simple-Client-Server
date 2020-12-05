import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Handles the delivery of messages from the server
 */
public class ServerConnection implements Runnable {
    private Socket server;
    private BufferedReader in;

    public ServerConnection(Socket s) throws IOException {
        this.server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                // A message from the server
                String serverResponse = in.readLine();
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
