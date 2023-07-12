package network.practice;

import java.net.Socket;

public class RequestHandler extends Thread{
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket =socket;
    }

    @Override
    public void run() {
        RequestManager requestManager = new RequestManager(socket);
        try {
            RequestDto requestDto = requestManager.readRequest();
            ResponseManager responseManager = new ResponseManager(socket, requestDto);
            responseManager.writeResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
