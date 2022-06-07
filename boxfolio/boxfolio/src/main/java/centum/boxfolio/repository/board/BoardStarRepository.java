package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardStar;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardStarRepository {
    BoardStar upStar(Board board, Member member);
    void downStar(Board board, Member member);
    List<BoardStar> findAll();
    Optional<BoardStar> findByBoardIdAndMemberId(Long boardId, Long memberId);
}
