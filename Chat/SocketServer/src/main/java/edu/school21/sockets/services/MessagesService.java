package edu.school21.sockets.services;
import java.util.List;
import edu.school21.sockets.models.Message;
public interface MessagesService{
    void send(String text, String userName);
    List<Message> findAll();
}