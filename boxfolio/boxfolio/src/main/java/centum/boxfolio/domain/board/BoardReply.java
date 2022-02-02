package centum.boxfolio.domain.board;

import centum.boxfolio.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity
public class BoardReply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contents;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_comment_id")
    private BoardComment boardComment;
}
