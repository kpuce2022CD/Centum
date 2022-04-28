package centum.boxfolio.repository.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.controller.member.ProgressProjectSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public Optional<Board> findGeneralPostById(Long id) {
        return findGeneralBoard().stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    @Override
    public List<Board> findGeneralBoard() {
        return em.createQuery("SELECT b FROM Board b", Board.class).getResultList();
    }

    @Override
    public List<Board> findBoardByMemberId(Long memberId) {
        return findGeneralBoard().stream()
                .filter(b -> b.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public void removeGeneralBoard(Long id) {
        Optional<Board> post = findGeneralPostById(id);
        em.remove(post.get());
    }

    @Override
    public Free saveFreePost(Free free) {
        em.persist(free);
        return free;
    }

    @Override
    public Optional<Free> findFreePostById(Long id) {
        return findFreeBoard().stream()
                .filter(f -> f.getId() == id)
                .findAny();
    }

    @Override
    public List<Free> findFreeBoard() {
        return em.createQuery("SELECT f FROM Free f LEFT JOIN Board b ON f.id = b.id", Free.class).getResultList();
    }

    @Override
    public Optional<Free> modifyFreePost(Free free, FreeBoardSaveForm freeBoardSaveForm) {
        free.setFree(freeBoardSaveForm.getTitle(), freeBoardSaveForm.getContents(), freeBoardSaveForm.getCommentAllow(), freeBoardSaveForm.getScrapAllow(), freeBoardSaveForm.getVisibility());
        return Optional.ofNullable(free);
    }

    @Override
    public void removeFreeBoard(Long id) {
        Optional<Free> post = findFreePostById(id);
        em.remove(post.get());
    }

    @Override
    public Information saveInfoPost(Information information) {
        em.persist(information);
        return information;
    }

    @Override
    public Optional<Information> findInfoPostById(Long id) {
        return findInfoBoard().stream()
                .filter(i -> i.getId() == id)
                .findAny();
    }

    @Override
    public List<Information> findInfoBoard() {
        return em.createQuery("SELECT i FROM Information i LEFT JOIN Board b ON i.id = b.id", Information.class).getResultList();
    }

    @Override
    public Optional<Information> modifyInfoPost(Information information, InfoBoardSaveForm infoBoardSaveForm) {
        information.setInfo(infoBoardSaveForm.getTitle(), infoBoardSaveForm.getContents(), infoBoardSaveForm.getCommentAllow(), infoBoardSaveForm.getScrapAllow(), infoBoardSaveForm.getVisibility());
        return Optional.ofNullable(information);
    }

    @Override
    public void removeInfoBoard(Long id) {
        Optional<Information> post = findInfoPostById(id);
        em.remove(post.get());
    }

    @Override
    public Recruitment saveRecruitPost(Recruitment recruitment) {
        em.persist(recruitment);
        return recruitment;
    }

    @Override
    public Optional<Recruitment> findRecruitPostById(Long id) {
        return findRecruitBoard().stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    @Override
    public List<Recruitment> findRecruitBoard() {
        return em.createQuery("SELECT r FROM Recruitment r LEFT JOIN Board b ON r.id = b.id", Recruitment.class).getResultList();
    }

    @Override
    public Optional<Recruitment> modifyRecruitPost(Recruitment recruitment, RecruitBoardSaveForm recruitBoardSaveForm) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadlineDate = LocalDateTime.of(recruitBoardSaveForm.getDeadlineYear(), recruitBoardSaveForm.getDeadlineMonth(), recruitBoardSaveForm.getDeadlineDay(), now.getHour(), now.getMinute(), now.getSecond());
        recruitment.setRecruit(recruitBoardSaveForm.getTitle(), recruitBoardSaveForm.getContents(), recruitBoardSaveForm.getCommentAllow(), recruitBoardSaveForm.getScrapAllow(),
                recruitBoardSaveForm.getVisibility(), recruitBoardSaveForm.getAutoMatchingStatus(), deadlineDate, recruitBoardSaveForm.getMemberTotal(),
                recruitBoardSaveForm.getProjectSubject(), recruitBoardSaveForm.getProjectField(), "", recruitBoardSaveForm.getProjectLevel(), recruitBoardSaveForm.getRequiredMemberLevel(), recruitBoardSaveForm.getExpectedPeriod());
        return Optional.ofNullable(recruitment);
    }

    @Override
    public void removeRecruitBoard(Long id) {
        Optional<Recruitment> post = findRecruitPostById(id);
        em.remove(post.get());
    }

    @Override
    public ProjectMember saveProjectMember(Recruitment recruitment, Member member) {
        recruitment.setMemberTally(recruitment.getMemberTally() + 1);
        ProjectMember projectMember = new ProjectMember(recruitment, member);
        em.persist(projectMember);
        return projectMember;
    }

    @Override
    public List<ProjectMember> findAllProjectMember() {
        return em.createQuery("select pm from ProjectMember pm", ProjectMember.class).getResultList();
    }

    @Override
    public List<ProjectMember> findProjectMemberByMemberId(Long memberId) {
        return findAllProjectMember().stream()
                .filter(pm -> pm.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectMember> findProjectMemberByBoardId(Long boardId) {
        return findAllProjectMember().stream()
                .filter(pm -> pm.getRecruitment().getId() == boardId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectMember> findProjectMemberByBoardIdAndMemberId(Long boardId, Long memberId) {
        return findAllProjectMember().stream()
                .filter(pm -> pm.getRecruitment().getId() == boardId)
                .filter(pm -> pm.getMember().getId() == memberId)
                .findAny();
    }

    /**
     * 마감 완료
     */
    @Override
    public Recruitment setDeadlineStatusToTrue(Recruitment recruitment) {
        recruitment.setDeadlineStatus(true);
        return recruitment;
    }

    @Override
    public Recruitment setDeadlineStatusToFalse(Recruitment recruitment) {
        recruitment.setDeadlineStatus(false);
        return recruitment;
    }

    @Override
    public List<ProjectRule> findAllProjectRule() {
        return em.createQuery("SELECT pr FROM ProjectRule pr", ProjectRule.class).getResultList();
    }

    @Override
    public List<ProjectRule> findProjectRulesByBoardId(Long boardId) {
        return findAllProjectRule().stream()
                .filter(pr -> pr.getRecruitment().getId() == boardId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectPlan> findAllProjectPlan() {
        return em.createQuery("SELECT pp FROM ProjectPlan pp", ProjectPlan.class).getResultList();
    }

    @Override
    public List<ProjectPlan> findProjectPlansByBoardId(Long boardId) {
        return findAllProjectPlan().stream()
                .filter(pp -> pp.getRecruitment().getId() == boardId)
                .collect(Collectors.toList());
    }

    @Override
    public Recruitment modifySubjectAndPreview(Recruitment recruitment, ProgressProjectSaveForm progressProjectSaveForm) {
        recruitment.setProjectSubject(progressProjectSaveForm.getProjectSubject());
        recruitment.setProjectPreview(progressProjectSaveForm.getProjectPreview());
        return recruitment;
    }

    @Override
    public ProjectPlan saveProjectPlan(ProjectPlan projectPlan) {
        em.persist(projectPlan);
        return projectPlan;
    }

    @Override
    public ProjectRule saveProjectRule(ProjectRule projectRule) {
        em.persist(projectRule);
        return projectRule;
    }

    @Override
    public Board upView(Board board) {
        board.setViewTally(board.getViewTally() + 1);
        return board;
    }
}
