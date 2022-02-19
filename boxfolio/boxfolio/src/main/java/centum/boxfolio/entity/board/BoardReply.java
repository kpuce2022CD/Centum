package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class BoardReply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contents;
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_comment_id")
    private BoardComment boardComment;
}
