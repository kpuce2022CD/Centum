package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PostStar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public PostStar(Post post, Member member) {
        setPost(post);
        setMember(member);
    }

    private void setPost(Post post) {
        if (this.post != null) {
            this.post.getPostStars().remove(this);
        }
        this.post = post;
        post.getPostStars().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getPostStars().remove(this);
        }
        this.member = member;
        member.getPostStars().add(this);
    }
}
