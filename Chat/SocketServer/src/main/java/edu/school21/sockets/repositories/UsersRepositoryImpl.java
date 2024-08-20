package edu.school21.sockets.repositories;
import javax.sql.DataSource;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import edu.school21.sockets.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Repository
public class UsersRepositoryImpl implements UsersRepository{
    private JdbcTemplate template;
    @Autowired
    public UsersRepositoryImpl(DataSource source){
        this.template = new JdbcTemplate(source);
    }
    public Optional<User> findById(Long id){
        try{
            String query = "select * from users where id = ?;";
            User user = template.queryForObject(query, new Object[]{id}, new UserRowMapper());
            return Optional.ofNullable(user);
        } catch (Exception e){}
        return Optional.empty();
    }

    public List<User> findAll(){
        String query = "select * from users;";
        return template.query(query, new UserRowMapper());
    }

    public void save(User entity){
        String query = "insert into users(id, userName, password) values (?, ?, ?);";
        template.update(query, entity.getId(), entity.getUserName(), entity.getPassword());
    }

    public void update(User entity){
        String query = "update users set userName = ?, password = ? where id = ?";
        template.update(query, entity.getUserName(), entity.getPassword(), entity.getId());
    }

    public void delete(Long id){
        String query = "delete from users where id = ?";
        template.update(query, id);
    }

    public Optional<User> findByUserName(String userName){
        try{
            String query = "select * from users where userName = ?;";
            User user = template.queryForObject(query, new Object[]{userName}, new UserRowMapper());
            return Optional.ofNullable(user);
        } catch (Exception e){}
        return Optional.empty();
    }
}