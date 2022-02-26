package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardStar;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface StarRepository {
    BoardStar upStar(Board board, Member member);
    BoardStar downStar(Board board, Member member);
    List<BoardStar> findStarAll();
    Optional<BoardStar> findStarByBoardIdAndMemberId(Long boardId, Long memberId);
}
