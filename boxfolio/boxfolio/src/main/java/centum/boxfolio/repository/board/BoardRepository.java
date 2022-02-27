package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Free;
import centum.boxfolio.entity.board.Information;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Optional<Board> findGeneralPostById(Long id);
    List<Board> findGeneralBoard();
    void removeGeneralBoard(Long id);

    Free saveFreePost(Free free);
    Optional<Free> findFreePostById(Long id);
    List<Free> findFreeBoard();
    void removeFreeBoard(Long id);

    Information saveInfoPost(Information information);
    Optional<Information> findInfoPostById(Long id);
    List<Information> findInfoBoard();
    void removeInfoBoard(Long id);

    Recruitment saveRecruitPost(Recruitment recruitment);
    Optional<Recruitment> findRecruitPostById(Long id);
    List<Recruitment> findRecruitBoard();
    void removeRecruitBoard(Long id);

    Board upView(Board board);
}
