package network.practice;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * port 번호 터미널에서 입력받게 하기
 */
public class SimpleWebServerInitial {
    private final int BUFFER_SIZE = 2048;
    public static void main(String[] args) {
        SimpleWebServerInitial server = new SimpleWebServerInitial();
        Scanner sc = new Scanner(System.in);
//        int port = sc.nextInt();
//        System.out.println("port = " + port);
        server.start(9000);
    }

    private void start(int port) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                RequestManager requestManager = new RequestManager(socket);
                RequestDto requestDto = requestManager.readRequest();
                ResponseManager responseManager = new ResponseManager(socket, requestDto);
                responseManager.writeResponse();
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
