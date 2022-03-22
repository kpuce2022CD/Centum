package centum.boxfolio.entity.board;

import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class BoardComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;
    private LocalDateTime createdDate;
    private Integer commentClass;
    private Long commentOrder;
    private Long groupNum;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private BoardComment boardComment;

    @OneToMany(mappedBy = "boardComment", cascade = CascadeType.REMOVE)
    private List<BoardComment> boardComments;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public BoardComment(String contents, LocalDateTime createdDate, Integer commentClass, Long commentOrder, Long groupNum, BoardComment boardComment, Board board, Member member) {
        this.contents = contents;
        this.createdDate = createdDate;
        this.commentClass = commentClass;
        this.commentOrder = commentOrder;
        this.groupNum = groupNum;
        setParent(boardComment);
        setBoard(board);
        setMember(member);
    }

    public BoardComment(String contents, LocalDateTime createdDate, Integer commentClass, Long commentOrder, BoardComment boardComment, Board board, Member member) {
        this.contents = contents;
        this.createdDate = createdDate;
        this.commentClass = commentClass;
        this.commentOrder = commentOrder;
        setParent(boardComment);
        setBoard(board);
        setMember(member);
    }

    private void setParent(BoardComment boardComment) {
        if (this.boardComment != null) {
            this.boardComment.getBoardComments().remove(this);
        }
        this.boardComment = boardComment;
        if (boardComment != null) {
            boardComment.getBoardComments().add(this);
        }
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
