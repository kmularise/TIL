package network.practice;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class RequestManager {
    private final int BUFFER_SIZE = 2048;
    private Socket socket;
    public RequestManager(Socket socket) {
        this.socket = socket;
    }

    public RequestDto readRequest() throws Exception {
        InputStream request = new BufferedInputStream(socket.getInputStream());
        byte[] receivedBytes = new byte[BUFFER_SIZE];
        request.read(receivedBytes);
        String requestData = new String(receivedBytes).trim();
        System.out.println("requestData = " + requestData);
        System.out.println("---------------");
        return parseData(requestData);
    }

    private RequestDto parseData(String requestData) throws Exception {
        String[] elements = requestData.split("\r\n");
        String host = null;
        for (String element : elements) {
            if (element.startsWith("Host: ")) {
                host = element.replace("Host: " , "");
            }
        }
        for (String element : elements) {
            if (element.startsWith("GET ")) {
                String uri = element.replace("GET ", "")
                        .replace(" HTTP/1.1" , "");
                RequestDto requestDto = new RequestDto(uri);
                return requestDto;
            }
        }
        throw new Exception("requestDto가 null 입니다.");
    }
}
