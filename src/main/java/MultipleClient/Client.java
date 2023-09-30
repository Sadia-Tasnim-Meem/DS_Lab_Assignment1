package MultipleClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try(var socket = new Socket("127.0.0.1", 59898)){
            System.out.println("Enter message to server\n");
            var scanner = new Scanner(System.in);
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            while(scanner.hasNextLine()){
                out.println(scanner.nextLine());
                System.out.println(in.nextLine());
            }
        }
    }
}
