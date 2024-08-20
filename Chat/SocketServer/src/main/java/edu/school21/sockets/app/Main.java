package edu.school21.sockets.app;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.models.User;
public class Main{
    public static void main(String args[]){
        try{
            if(args.length != 1 || !args[0].startsWith("--port=")){
                System.out.println("Incorrect argument.");
                System.exit(0);
            }
            String input[] = args[0].split("=");
            if(input.length != 2){
                System.out.println("Number of port is not found.");
                System.exit(0);
            }
            int port = Integer.parseInt(input[1]);
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
//        UsersRepository usersRepository = context.getBean("usersRepositoryImpl", UsersRepository.class);
//        System.out.println("JDBC Template Impl");
//        System.out.println("\nFind All test");
//        System.out.println(usersRepository.findAll());
//        System.out.println("\nFindBy Id test");
//        System.out.println(usersRepository.findById(5L));
//        System.out.println("\nSave test");
//        usersRepository.save(new User(6L, "leshka@yandex.ru", "asdcsdces"));
//        System.out.println(usersRepository.findAll());
//        System.out.println("\nUpdate test");
//        usersRepository.update(new User(2L, "REAL@goodmorning.ru", "dc"));
//        System.out.println(usersRepository.findAll());
//        System.out.println("\nDelete test");
//        usersRepository.delete(5L);
//        System.out.println(usersRepository.findAll());
//        System.out.println("\nFind By UserName test");
//        System.out.println(usersRepository.findByUserName("Bob"));

            Server server = context.getBean("server", Server.class);
            server.start(port);
        } catch (Exception e){
            System.out.println(e);
        }

    }
}