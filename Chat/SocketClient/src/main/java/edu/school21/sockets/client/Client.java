package edu.school21.sockets.client;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client{
    private int port;
    private Scanner scanner;
    public Client(int port){
        this.port = port;
        scanner = new Scanner(System.in);
    }
    public void start(){
        try(
        Socket socket = new Socket("localhost", port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
            System.out.println(in.readLine());//Greating
            String option = scanner.nextLine();
            out.println(option);//signUp or signIn
            String response = in.readLine();
            if(response.equals("Fail to sign up.")){
                in.close();
                out.close();
                socket.close();
                return;
            }
            System.out.println(response);
            out.println(scanner.nextLine()); //name
            System.out.println(in.readLine());
            out.println(scanner.nextLine()); //password
            String result = in.readLine();
            System.out.println(result);
            if(option.equals("signIn") && !result.equals("Fail to sign in.")){
                ServerMessagesThread serverMessagesThread = new ServerMessagesThread(in, socket);
                Thread messageListenerThread = new Thread(serverMessagesThread);
                messageListenerThread.setDaemon(true);
                messageListenerThread.start();
                while (true) {
                    if(serverMessagesThread.isDisconnected()){
                        break;
                    }
                    String text = scanner.nextLine();
                    if(!text.equals("Exit") ){
                        out.println(text);
                    } else{
                        System.out.println("You have left the chat.");
                        return;
                    }
                }
            }
            scanner.close();
            in.close();
            out.close();
            socket.close();
        } catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                scanner.close();
            } catch (Exception e) {
                // Ignore
            }
        }
    }

}