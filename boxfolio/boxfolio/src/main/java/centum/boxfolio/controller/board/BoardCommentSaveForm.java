package centum.boxfolio.controller.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardComment;
import centum.boxfolio.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
public class BoardCommentSaveForm {
    @NotNull
    private String contents;

    public BoardComment toBoardComment(Integer commentClass, Long commentOrder, Board board, Member member) {
        return new BoardComment(contents, LocalDateTime.now(), commentClass, commentOrder, board, member);
    }

    public BoardComment toBoardComment(Integer commentClass, Long commentOrder, Long groupNum, Board board, Member member) {
        return new BoardComment(contents, LocalDateTime.now(), commentClass, commentOrder, groupNum, board, member);
    }
}
