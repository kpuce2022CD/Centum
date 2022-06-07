package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.controller.member.ProgressProjectSaveForm;
import centum.boxfolio.controller.member.ProjectPlanSaveForm;
import centum.boxfolio.controller.member.ProjectRuleSaveForm;
import centum.boxfolio.dto.board.FreeDto;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;

import java.util.List;

public interface BoardService {
    Board countView(Board board);
    BoardStar countStar(Long boardId, Long memberId);
    BoardScrap countScrap(Long boardId, Long memberId);

    Free createFreePost(FreeBoardSaveForm freeBoardSaveForm, Long memberId);
    Free updateFreePost(FreeBoardSaveForm freeBoardSaveForm, Long boardId);
    Free readFreePost(Long id);
    List<Free> readFreeBoard();
    void deleteFreeBoard(Long id);

    Board createBoard(Board board);

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

    List<Member> findProjectMembersByBoardId(Long boardId);
    Boolean checkProjectMemberByBoardIdAndMemberLoginId(Long boardId, String loginId);

    Recruitment endRecruit(Long boardId);
    Recruitment restartRecruit(Long boardId);
    ProjectMember applyRecruit(Long boardId, Long memberId);
    Boolean checkApplyStatus(Long boardId, Long memberId);
    Boolean checkClosingRecruit(Recruitment recruitment);

    Recruitment updateProjectSubjectAndPreview(ProgressProjectSaveForm progressProjectSaveForm, Long boardId);
    ProjectPlan createProjectPlan(ProgressProjectSaveForm progressProjectSaveForm, Long boardId);
    ProjectRule createProjectRule(ProgressProjectSaveForm progressProjectSaveForm, Long boardId);
}
