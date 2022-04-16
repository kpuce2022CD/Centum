package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardComment;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    BoardComment save(BoardComment comment);
    Optional<BoardComment> findById(Long id);
    List<BoardComment> findCommentsByMemberId(Long memberId);
    List<BoardComment> findCommentsByBoardId(Long boardId);
    List<BoardComment> findAll();
    List<BoardComment> findAllOrdered();
    Long findMaxOrderInGroupNum(Long groupNum);
    void remove(BoardComment comment);

}
