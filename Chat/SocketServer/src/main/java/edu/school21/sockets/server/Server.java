package edu.school21.sockets.server;
import java.io.*;
import java.net.*;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.MessagesService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.LinkedList;
import edu.school21.sockets.models.Message;

@Component
public class Server{
    private UsersService usersService;
    private MessagesService messagesService;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private List<ClientHandler> clients;

    @Autowired
    public Server(UsersService service, MessagesService mService){
        this.usersService = service;
        this.messagesService = mService;
        clients = new LinkedList<>();
    }

    public void start(int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                try{
                    clientSocket = serverSocket.accept();
                    ClientHandler client = new ClientHandler(clientSocket, usersService, messagesService);
                    clients.add(client);
                    client.setClients(clients);
                    new Thread(client).start();
                } catch (Exception e){
                    System.out.println(e);
                }
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
}