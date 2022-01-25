package centum.boxfolio.repository.user;

import centum.boxfolio.domain.user.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User findById(String id);
    List<User> findAll();
}
