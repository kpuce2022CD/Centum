package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.Board;

public interface BoardService {
    Board createGeneralBoard();
    Board createRecruitBoard(RecruitBoardSaveForm recruitBoardSaveForm, long memberId);
    Board updateRecruitBoard();
}
