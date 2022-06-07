package centum.boxfolio.repository.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.controller.member.ProgressProjectSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board saveBoard(Board board);
    Optional<Board> findGeneralPostById(Long id);
    List<Board> findGeneralBoard();
    List<Board> findBoardByMemberId(Long memberId);
    void removeGeneralBoard(Long id);

    Free saveFreePost(Free free);
    Optional<Free> findFreePostById(Long id);
    List<Free> findFreeBoard();
    Optional<Free> modifyFreePost(Free free, FreeBoardSaveForm freeBoardSaveForm);
    void removeFreeBoard(Long id);

    Information saveInfoPost(Information information);
    Optional<Information> findInfoPostById(Long id);
    List<Information> findInfoBoard();
    Optional<Information> modifyInfoPost(Information information, InfoBoardSaveForm infoBoardSaveForm);
    void removeInfoBoard(Long id);

    Recruitment saveRecruitPost(Recruitment recruitment);
    Optional<Recruitment> findRecruitPostById(Long id);
    List<Recruitment> findRecruitBoard();
    Optional<Recruitment> modifyRecruitPost(Recruitment recruitment, RecruitBoardSaveForm recruitBoardSaveForm);
    void removeRecruitBoard(Long id);

    ProjectMember saveProjectMember(Recruitment recruitment, Member member);
    List<ProjectMember> findAllProjectMember();
    List<ProjectMember> findProjectMemberByMemberId(Long memberId);
    List<ProjectMember> findProjectMemberByBoardId(Long boardId);
    Optional<ProjectMember> findProjectMemberByBoardIdAndMemberId(Long boardId, Long memberId);
    Optional<ProjectMember> findProjectMemberByBoardIdAndMemberLoginId(Long boardId, String loginId);

    Recruitment setDeadlineStatusToTrue(Recruitment recruitment);
    Recruitment setDeadlineStatusToFalse(Recruitment recruitment);

    List<ProjectRule> findAllProjectRule();
    List<ProjectRule> findProjectRulesByBoardId(Long boardId);
    List<ProjectPlan> findAllProjectPlan();
    List<ProjectPlan> findProjectPlansByBoardId(Long boardId);

    Recruitment modifySubjectAndPreview(Recruitment recruitment, ProgressProjectSaveForm progressProjectSaveForm);
    ProjectPlan saveProjectPlan(ProjectPlan projectPlan);
    ProjectRule saveProjectRule(ProjectRule projectRule);

    Board upView(Board board);
}
