package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board saveGeneralBoard(Board board);
    Board saveRecruitBoard(Recruitment recruitment);
    Optional<Board> findById(Long Id);
    Optional<Board> findByMemberId(Long memberId);
    List<Board> findAll();
}
