package edu.school21.sockets.repositories;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.school21.sockets.models.User;
public class UserRowMapper implements RowMapper<User>{
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}