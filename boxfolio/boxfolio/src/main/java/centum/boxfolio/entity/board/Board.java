package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "boardType")
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private Boolean commentAllow;
    private Boolean scrapAllow;
    private Long starTally;
    private Long commentTally;
    private Long scrapTally;
    private Long viewTally;
    private String visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BoardStar> boardStars = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BoardScrap> boardScraps = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BoardComment> boardComments = new ArrayList<>();

    public Board(String title, String contents, LocalDateTime createdDate,
                 boolean commentAllow, boolean scrapAllow, String visibility, Member member) {
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.commentAllow = commentAllow;
        this.scrapAllow = scrapAllow;
        this.starTally = 0L;
        this.commentTally = 0L;
        this.scrapTally = 0L;
        this.viewTally = 0L;
        this.visibility = visibility;
        setMember(member);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getBoards().remove(this);
        }
        this.member = member;
        member.getBoards().add(this);
    }
}
