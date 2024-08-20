package edu.school21.sockets.repositories;
import edu.school21.sockets.models.Message;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.school21.sockets.models.User;

public class MessageRowMapper implements RowMapper<Message>{
    private UsersRepository usersRepository;

    public MessageRowMapper(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException{
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setSender(usersRepository.findById(rs.getLong("sender")).get());
        message.setText(rs.getString("text"));
        message.setTime(rs.getTimestamp("time").toLocalDateTime());
        return message;
    }
}