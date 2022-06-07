package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.BoardCommentSaveForm;
import centum.boxfolio.entity.board.BoardComment;

import java.util.List;

public interface CommentService {

    BoardComment createComment(BoardCommentSaveForm boardCommentSaveForm, Long boardId, Long memberId);
    List<BoardComment> findAll();
    List<BoardComment> findByBoardId(Long boardId);
    void deleteComment(Long commentId);

    BoardComment createReply(BoardCommentSaveForm boardCommentSaveForm, Long commentId, Long memberId);
}
