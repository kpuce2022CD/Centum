package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.BoardCommentSaveForm;
import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.*;

import java.util.List;

public interface BoardService {
    Board countView(Board board);
    BoardStar countStar(Long boardId, Long memberId);
    BoardScrap countScrap(Long boardId, Long memberId);

    Board readGeneralPost(Long id);
    List<Board> readGeneralBoard();
    void deleteGeneralBoard(Long id);

    Free createFreePost(FreeBoardSaveForm freeBoardSaveForm, Long memberId);
    Free updateFreePost(FreeBoardSaveForm freeBoardSaveForm, Long boardId);
    Free readFreePost(Long id);
    List<Free> readFreeBoard();
    void deleteFreeBoard(Long id);

    Information createInfoPost(InfoBoardSaveForm infoBoardSaveForm, Long memberId);
    Information updateInfoPost(InfoBoardSaveForm infoBoardSaveForm, Long boardId);
    Information readInfoPost(Long id);
    List<Information> readInfoBoard();
    void deleteInfoBoard(Long id);

    Recruitment createRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long memberId);
    Recruitment updateRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long boardId);
    Recruitment readRecruitPost(Long id);
    List<Recruitment> readRecruitBoard();
    void deleteRecruitBoard(Long id);
}
