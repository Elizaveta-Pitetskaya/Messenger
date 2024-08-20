package edu.school21.sockets.repositories;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository{
    private JdbcTemplate template;
    private UsersRepository usersRepository;
    @Autowired
    public MessagesRepositoryImpl(DataSource source, UsersRepository usersRepository){
        template = new JdbcTemplate(source);
        this.usersRepository = usersRepository;
    }

    public Optional<Message> findById(Long id){
        try{
            String query = "select * from messages where id = ?;";
            Message message = template.queryForObject(query, new Object[]{id}, new MessageRowMapper(usersRepository));
            return Optional.ofNullable(message);
        } catch (Exception e){}
        return Optional.empty();
    }

    public List<Message> findAll(){
        String query = "select * from messages;";
        return template.query(query, new MessageRowMapper(usersRepository));
    }

    public void save(Message entity){
        try{
            String query = "insert into messages(id, sender, text, time) values(?, ?, ?, ?);";
            template.update(query, entity.getId(), entity.getSender().getId(), entity.getText(), entity.getTime());
        } catch(Exception e){}
    }

    public void update(Message entity){
        String query = "update message set sender = ?, text = ?, time = ? where id = ?;";
        template.update(query, entity.getSender().getId(), entity.getText(), entity.getTime(), entity.getId());
    }

    public void delete(Long id){
        String query = "delete from massages where id = ?;";
        template.update(query, id);
    }

    public List<Message> findAllBySender(Long id){
        String query = "select * from messages where sender = ?;";
        return template.query(query, new Object[]{id}, new MessageRowMapper(usersRepository));
    }
}