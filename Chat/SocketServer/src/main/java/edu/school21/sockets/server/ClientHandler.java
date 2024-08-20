package edu.school21.sockets.server;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.MessagesService;
import java.io.*;
import java.net.*;
import java.util.List;
public class ClientHandler implements Runnable{
    private Socket socket;
    private List<ClientHandler> clients;
    private UsersService usersService;
    private MessagesService messagesService;
    private String userName;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, UsersService usersService, MessagesService messagesService){
        this.socket = socket;
        this.usersService = usersService;
        this.messagesService = messagesService;
    }

    public void setClients(List<ClientHandler> clients) {this.clients = clients;}

    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from Server! Choose option: signUp or signIn");
            String option = in.readLine();
            if(option.equals("signUp")){
                signUp();
            } else if(option.equals("signIn")){
                signIn();
                while(true){
                    String text = in.readLine();
                    if(text == null || text.equals("Exit")){
                        break;
                    }
                    messagesService.send(text, userName);
                    broadcastMessage(userName + ":" + text);
                }
            }
        } catch (Exception e){

        } finally{
            try{
                in.close();
                out.close();
                socket.close();
            } catch (Exception e){}
        }
    }

    private void signUp(){
        try{
            out.println("Enter username:");
            userName = in.readLine();
            out.println("Enter password:");
            String password = in.readLine();
            if(usersService.signUp(userName, password)){
                out.println("Successful!");
            }
            else {
                out.println("Fail to sign up.");
                socket.close();
            }
        } catch (Exception e){}
    }
    private void signIn(){
        try{
            out.println("Enter username:");
            userName = in.readLine();
            out.println("Enter password:");
            String password = in.readLine();
            if(usersService.signIn(userName, password)){
                out.println("Start messaging");
            } else{
                out.println("Fail to sign in.");
                socket.close();
            }
        } catch (Exception e){}
    }

    private void broadcastMessage(String text){
        for(ClientHandler client : clients){
            client.out.println(text);
        }
    }
}