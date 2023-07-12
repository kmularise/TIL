package network.practice;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

public class ResponseManager {
    private Socket socket;
    private RequestDto requestDto;

    public ResponseManager(Socket socket, RequestDto requestDto) {
        this.socket = socket;
        this.requestDto = requestDto;
    }

    public void writeResponse() throws Exception {
        PrintStream response = new PrintStream(socket.getOutputStream());
        writeHeader(response);
        writeBody(response);
        response.flush();
        response.close();
    }

    private void writeBody(PrintStream response) throws InterruptedException {
        System.out.println(requestDto.getUri());

        if (requestDto.getUri().equals("/today")) {
            response.println("Today " + LocalDateTime.now() + "<br>");
            response.println("Today in Date Type " + new Date());
        } else if (requestDto.getUri().equals("/long/task")) {
            System.out.println("very long task");
            Thread.sleep(10000);
            response.println("very long task done!!");
        } else {
            response.println("working");
        }
    }

    private void writeHeader(PrintStream response) {
        response.println("HTTP/1.1 200 OK");
        response.println("Content-type: text/html");
        response.println();
    }
}
