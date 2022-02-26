package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository {
    BoardScrap upScrap(Board board, Member member);
    void downScrap(Board board, Member member);

    List<BoardScrap> findScrapAll();
    Optional<BoardScrap> findScrapByBoardIdAndMemberId(Long boardId, Long memberId);
}
