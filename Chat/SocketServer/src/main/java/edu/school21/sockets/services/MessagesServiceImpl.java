package edu.school21.sockets.services;
import edu.school21.sockets.models.User;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.UsersRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessagesServiceImpl implements MessagesService{
    private MessagesRepository messagesRepository;
    private UsersRepository usersRepository;
    @Autowired
    public MessagesServiceImpl(MessagesRepository messagesRepository, UsersRepository usersRepository){
        this.messagesRepository = messagesRepository;
        this.usersRepository = usersRepository;
    }
    public void send(String text, String userName){
        User sender = usersRepository.findByUserName(userName).get();
        Message message = new Message((long)(messagesRepository.findAll().size() + 1), sender, text, LocalDateTime.now());
        messagesRepository.save(message);
    }

    public List<Message> findAll(){
        return messagesRepository.findAll();
    }
}