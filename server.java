import java.net.*;
import java.io.*;

public class server{
    public static void main(String[] args) throws IOException {
        int port = 3000;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        System.out.println("Client connected");

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String clientMsg = bf.readLine();
        System.out.println("Client: "+ clientMsg);

        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println("yes, it is");
        pr.flush();

        serverSocket.close();
    }
}