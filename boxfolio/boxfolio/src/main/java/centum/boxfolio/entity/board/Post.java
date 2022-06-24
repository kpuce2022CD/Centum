package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "postType")
public class Post {
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
    private Boolean visibility;
    @Column(insertable = false, updatable = false)
    private String postType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostStar> postStars = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostScrap> postScraps = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostComment> postComments = new ArrayList<>();

    public Post(String title, String contents, LocalDateTime createdDate,
                boolean commentAllow, boolean scrapAllow, Boolean visibility, Member member) {
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

    protected void setModifiableBoard(String title, String contents, boolean commentAllow, boolean scrapAllow, Boolean visibility) {
        this.title = title;
        this.contents = contents;
        this.commentAllow = commentAllow;
        this.scrapAllow = scrapAllow;
        this.visibility = visibility;
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getPosts().remove(this);
        }
        this.member = member;
        member.getPosts().add(this);
    }

    public void setCommentTally(Long commentTally) {
        this.commentTally = commentTally;
    }

    public void setScrapTally(Long scrapTally) {
        this.scrapTally = scrapTally;
    }

    public void setStarTally(Long starTally) {
        this.starTally = starTally;
    }

    public void setViewTally(Long viewTally) {
        this.viewTally = viewTally;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
}
