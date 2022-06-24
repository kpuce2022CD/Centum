package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PostScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public PostScrap(Post post, Member member) {
        setPost(post);
        setMember(member);
    }

    private void setPost(Post post) {
        if (this.post != null) {
            this.post.getPostScraps().remove(this);
        }
        this.post = post;
        post.getPostScraps().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getPostScraps().remove(this);
        }
        this.member = member;
        member.getPostScraps().add(this);
    }
}
