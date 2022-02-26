package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board saveGeneralPost(Board board);
    Optional<Board> findGeneralPostById(Long id);
    Optional<Board> findGeneralPostByMemberId(Long memberId);
    List<Board> findGeneralBoard();

    Recruitment saveRecruitPost(Recruitment recruitment);
    Optional<Recruitment> findRecruitPostById(Long id);
    List<Recruitment> findRecruitBoard();

    Board upView(Board board);
}
