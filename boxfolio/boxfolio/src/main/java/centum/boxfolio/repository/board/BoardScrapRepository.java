package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardScrapRepository {
    BoardScrap upScrap(Board board, Member member);
    void downScrap(Board board, Member member);

    List<BoardScrap> findAll();
    Optional<BoardScrap> findByBoardIdAndMemberId(Long boardId, Long memberId);
    List<BoardScrap> findByMemberId(Long memberId);
}
