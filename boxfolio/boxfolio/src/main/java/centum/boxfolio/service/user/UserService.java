package centum.boxfolio.service.user;

import centum.boxfolio.domain.user.User;

public interface UserService {
    void signUp(User user);
    void signIn(String id, String pw);
}
