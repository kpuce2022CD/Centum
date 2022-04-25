package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.BoardCommentSaveForm;
import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.controller.member.ProjectPlanSaveForm;
import centum.boxfolio.controller.member.ProjectRuleSaveForm;
import centum.boxfolio.controller.member.ProjectSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;

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
    List<Recruitment> recommendRecruitBoard(Member member);
    List<Recruitment> readProgressProjectByPage(Integer page, Long memberId);
    Integer findLastProgressProjectPage(Long memberId);

    List<Member> findMembersByBoardId(Long boardId);

    Recruitment endRecruit(Long boardId);
    Recruitment restartRecruit(Long boardId);
    ProjectMember applyRecruit(Long boardId, Long memberId);
    Boolean checkApplyStatus(Long boardId, Long memberId);
    Boolean checkClosingRecruit(Recruitment recruitment);

    Recruitment updateProjectSubjectAndPreview(ProjectSaveForm projectSaveForm, Long boardId);
    ProjectPlan createProjectPlan(ProjectPlanSaveForm projectPlanSaveForm, Long boardId);
    ProjectRule createProjectRule(ProjectRuleSaveForm projectRuleSaveForm, Long boardId);
}
