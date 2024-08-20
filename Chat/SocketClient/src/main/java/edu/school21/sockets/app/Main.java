package edu.school21.sockets.app;
import edu.school21.sockets.client.Client;
public class Main{
    public static void main(String args[]){
        try{
            if(args.length != 1 || !args[0].startsWith("--server-port=")){
                System.out.println("Incorrect number of arguments");
                System.exit(0);
            }
            String input[] = args[0].split("=");
            if(input.length != 2){
                System.out.println("Port nubmer is not found.");
                System.exit(0);
            }
            int port = Integer.parseInt(input[1]);
            Client client = new Client(port);
            client.start();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}