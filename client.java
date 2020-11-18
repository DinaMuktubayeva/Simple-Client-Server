import java.net.*;
import java.io.*;

public class client{
    public static void main(String[] args) throws IOException {
        int port = 3000;
        Socket socket = new Socket("localhost", port);

        PrintWriter pr = new PrintWriter(socket.getOutputStream());
        pr.println("is it working?");
        pr.flush();

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String serverMsg = bf.readLine();
        System.out.println("Server: "+ serverMsg);

        socket.close();
    }
}