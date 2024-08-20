package edu.school21.sockets.services;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.models.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsersServiceImpl implements UsersService{
    private UsersRepository repository;
    private PasswordEncoder encoder;
    @Autowired
    public UsersServiceImpl(UsersRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    public boolean signUp(String userName, String password){
        Optional<User> optional = repository.findByUserName(userName);
        if(!optional.isPresent()){
            User user = new User();
            user.setId((long)(repository.findAll().size() + 1));
            user.setUserName(userName);
            user.setPassword(encoder.encode(password));
            repository.save(user);
            return true;
        }
        return false;
    }

    public boolean signIn(String userName, String password){
        Optional<User> optional = repository.findByUserName(userName);
        if(optional.isPresent()){
            if(encoder.matches(password, optional.get().getPassword())){
                return true;
            }
        }
        return false;
    }

}