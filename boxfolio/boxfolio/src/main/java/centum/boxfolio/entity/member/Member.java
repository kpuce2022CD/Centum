package centum.boxfolio.entity.member;

import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
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
    private Date birth;
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
}