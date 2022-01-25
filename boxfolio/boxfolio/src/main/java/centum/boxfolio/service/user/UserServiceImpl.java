package centum.boxfolio.service.user;

import centum.boxfolio.domain.user.User;
import centum.boxfolio.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(User user) {

    }

    @Override
    public void signIn(String id, String pw) {

    }
}
