import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {

        try(var socket = new Socket(args[0], PORT)){
            System.out.println("[Client] Connected to server");
            System.out.println("[Client] Enter text (type \"quit\" to exit)");

            // User input
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            // Server response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Messages for the server            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                System.out.print("[Client] > ");
                // Reading user input
                String command = keyboard.readLine();

                if(command.equals("quit"))
                    break;

                out.println(command);

                String response = in.readLine();
                System.out.println("[Server] > " + response);
            }
        }
    }
}