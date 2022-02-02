package centum.boxfolio.domain.board;

import centum.boxfolio.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "boardType")
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String contents;
    private Date createdDate;
    private char commentAllow;
    private char scrapAllow;
    private long starTally;
    private long commentTally;
    private long scrapTally;
    private long viewTally;
    private String visibility;
    private String boardType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardStar> boardStars = new ArrayList<BoardStar>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardScrap> boardScraps = new ArrayList<BoardScrap>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardComment> boardComments = new ArrayList<BoardComment>();
}
