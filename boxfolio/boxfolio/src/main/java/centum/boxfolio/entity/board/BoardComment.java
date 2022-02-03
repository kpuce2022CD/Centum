package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class BoardComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contents;
    private Date createdDate;
    private long replyTally;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "boardComment", cascade = CascadeType.ALL)
    private List<BoardReply> boardReplies = new ArrayList<BoardReply>();
}
