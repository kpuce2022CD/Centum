package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
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
public class BoardComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contents;
    private LocalDateTime createdDate;
    private long replyTally;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "boardComment", cascade = CascadeType.ALL)
    private List<BoardReply> boardReplies = new ArrayList<>();

    public BoardComment(String contents, LocalDateTime createdDate, long replyTally, Board board, Member member) {
        this.contents = contents;
        this.createdDate = createdDate;
        this.replyTally = replyTally;
        setBoard(board);
        setMember(member);
    }

    private void setBoard(Board board) {
        if (this.board != null) {
            this.board.getBoardComments().remove(this);
        }
        this.board = board;
        board.getBoardComments().add(this);
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getBoardComments().remove(this);
        }
        this.member = member;
        member.getBoardComments().add(this);
    }
}
