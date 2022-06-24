package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PostComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private Integer commentClass;
    private Long commentOrder;
    private Long groupNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PostComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<PostComment> postComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @Builder
    public PostComment(String contents, LocalDateTime createdDate, Integer commentClass, Long commentOrder, Long groupNum, PostComment postComment, Post post, Member member) {
        this.contents = contents;
        this.createdDate = createdDate;
        this.commentClass = commentClass;
        this.commentOrder = commentOrder;
        this.groupNum = groupNum;
        setParent(postComment);
        setPost(post);
        setMember(member);
    }

    private void setParent(PostComment postComment) {
        if (this.parentComment != null) {
            this.parentComment.getPostComments().remove(this);
        }
        this.parentComment = postComment;
        if (postComment != null) {
            postComment.getPostComments().add(this);
        }
    }

    private void setPost(Post post) {
        if (this.post != null) {
            this.post.getPostComments().remove(this);
        }
        this.post = post;
        post.getPostComments().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getPostComments().remove(this);
        }
        this.member = member;
        member.getPostComments().add(this);
    }
}
