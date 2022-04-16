package centum.boxfolio.repository.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
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
    Optional<ProjectMember> findProjectMemberByBoardIdAndMemberId(Long boardId, Long memberId);

    Recruitment setRecruitStatusToTrue(Recruitment recruitment);
    Recruitment setRecruitStatusToFalse(Recruitment recruitment);

    Board upView(Board board);
}
