package MultipleClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        try(var listener = new ServerSocket(59898)){
            System.out.println("The server is running");
            var pool = Executors.newFixedThreadPool(20);
            while(true){
                pool.execute(new Handler(listener.accept()));
            }
        }
    }

    private static class Handler implements Runnable {
        private Socket socket;

        Handler(Socket socket) throws IOException {
            this.socket = socket;
        }

        public void run() {
            System.out.println("Connected: " + socket);
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {
                    System.out.println("has next line");
                    out.println("Received from server: " + in.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Error: " + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {

                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}
