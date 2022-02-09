package centum.boxfolio.entity.member;

import centum.boxfolio.controller.member.MemberSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    private String id;
    private String passwd;
    private String realName;
    private String nickname;
    private String phone;
    private String email;
    @Temporal(TemporalType.DATE)
    private LocalDate birth;
    private int sex;
    private String githubId;
    private String interestField;
    private String progressField;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_ability_id")
    private MemberAbility memberAbility;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PortfolioStar> portfolioStars = new ArrayList<PortfolioStar>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<Board>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardStar> boardStars = new ArrayList<BoardStar>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardScrap> boardScraps = new ArrayList<BoardScrap>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardComment> boardComments = new ArrayList<BoardComment>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BoardReply> boardReplies = new ArrayList<BoardReply>();

    public Member(MemberSaveForm form) {
        this.id = form.getId();
        this.passwd = form.getPasswd();
        this.realName = form.getRealName();
        this.nickname = form.getNickname();
        this.phone = form.getPhone();
        this.email = form.getEmail();
        this.birth = LocalDate.parse(form.getYear() + form.getMonth() + form.getDay(), DateTimeFormatter.BASIC_ISO_DATE);
        this.sex = form.getSex();
        this.githubId = form.getGithubId();
        this.interestField = form.getInterestField();
        this.progressField = "";
    }
}