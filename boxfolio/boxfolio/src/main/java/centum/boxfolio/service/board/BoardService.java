package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.board.BoardStar;
import centum.boxfolio.entity.board.Recruitment;

import java.util.List;

public interface BoardService {
    Board countView(Board board);
    BoardStar countStar(Long boardId, Long memberId);
    BoardScrap countScrap(Long boardId, Long memberId);

    Board createGeneralPost();
    Board updateGeneralPost();
    Board readGeneralPost(Long id);
    List<Board> readGeneralBoard();

    Recruitment createRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long memberId);
    Recruitment updateRecruitPost();
    Recruitment readRecruitPost(Long id);
    List<Recruitment> readRecruitBoard();
}
