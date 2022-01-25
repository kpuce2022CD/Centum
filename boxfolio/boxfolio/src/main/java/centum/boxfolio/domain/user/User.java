package centum.boxfolio.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class User {
    private String id;
    private String passwd;
    private String nickname;
    private String phone;
    private String email;
    private String interest;
    private String birth;
    private int sex;
    private String github;

    public User(String id, String passwd, String nickname, String phone, String email, String interest, String birth, int sex, String github) {
        this.id = id;
        this.passwd = passwd;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.interest = interest;
        this.birth = birth;
        this.sex = sex;
        this.github = github;
    }
}