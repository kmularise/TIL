package network.practice;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServer{
    private final int port = 9000;

    public static void main(String[] args) {
        SimpleWebServer server = new SimpleWebServer();
        server.run();
    }

    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
