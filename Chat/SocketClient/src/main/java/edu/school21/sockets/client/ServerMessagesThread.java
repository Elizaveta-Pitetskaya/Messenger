package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;

public class ServerMessagesThread implements Runnable {
    private BufferedReader in;
    private Socket socket;
    private boolean disconnected = false;

    public ServerMessagesThread(BufferedReader in, Socket socket) {
        this.in = in;
        this.socket = socket;

    }

    public boolean isDisconnected(){
        return disconnected;
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
        } finally {
            disconnected = true;
            try {
                socket.close();
                in.close();
            } catch (IOException e) {
            }
        }
    }
}
