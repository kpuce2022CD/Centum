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

    BoardComment createComment(BoardCommentSaveForm boardCommentSaveForm, Long boardId, Long memberId);
    List<BoardComment> readComments();
    List<BoardComment> readCommentsByBoardId(Long boardId);
    void deleteComment(Long commentId);

    Board readGeneralPost(Long id);
    List<Board> readGeneralBoard();
    void deleteGeneralBoard(Long id);

    Free createFreePost(FreeBoardSaveForm freeBoardSaveForm, Long memberId);
    Free updateFreePost();
    Free readFreePost(Long id);
    List<Free> readFreeBoard();
    void deleteFreeBoard(Long id);

    Information createInfoPost(InfoBoardSaveForm infoBoardSaveForm, Long memberId);
    Information updateInfoPost();
    Information readInfoPost(Long id);
    List<Information> readInfoBoard();
    void deleteInfoBoard(Long id);

    Recruitment createRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long memberId);
    Recruitment updateRecruitPost();
    Recruitment readRecruitPost(Long id);
    List<Recruitment> readRecruitBoard();
    void deleteRecruitBoard(Long id);
}
