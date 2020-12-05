import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class server {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {

        try (var listener = new ServerSocket(PORT)) {
            System.out.println("[Server] Server is running ...");

            // Created a thread pool
            var pool = Executors.newFixedThreadPool(20);

            // The executor manages the threads
            while (true)
                pool.execute(new ClientHandler(listener.accept()));
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            System.out.println("[Server] Connected to client");

            try {
                while (true) {
                    // The server echoes the client's messages
                    String msg = in.readLine();
                    out.println(msg);
                }
            } catch (Exception e) {
                System.out.println("[Server] Error: " + socket);
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

                System.out.println("[Server] Sucsessfully closed " + socket);
            }

        }
    }
}
