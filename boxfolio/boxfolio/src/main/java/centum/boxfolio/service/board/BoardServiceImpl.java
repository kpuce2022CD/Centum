package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.controller.member.ProjectPlanSaveForm;
import centum.boxfolio.controller.member.ProjectRuleSaveForm;
import centum.boxfolio.controller.member.ProgressProjectSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.board.BoardScrapRepository;
import centum.boxfolio.repository.board.BoardStarRepository;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardScrapRepository scrapRepository;
    private final BoardStarRepository boardStarRepository;
    private final MemberRepository memberRepository;

    @Override
    public Board countView(Board board) {
        return boardRepository.upView(board);
    }

    @Override
    public BoardStar countStar(Long boardId, Long memberId) {
        Optional<BoardStar> boardStar = boardStarRepository.findStarByBoardIdAndMemberId(boardId, memberId);
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Board> post = boardRepository.findGeneralPostById(boardId);
        if (boardStar.isEmpty()) {
            return boardStarRepository.upStar(post.get(), member.get());
        }
        boardStarRepository.downStar(post.get(), member.get());
        return null;
    }

    @Override
    public BoardScrap countScrap(Long boardId, Long memberId) {
        Optional<BoardScrap> boardScrap = scrapRepository.findScrapByBoardIdAndMemberId(boardId, memberId);
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Board> post = boardRepository.findGeneralPostById(boardId);
        if (boardScrap.isEmpty()) {
            return scrapRepository.upScrap(post.get(), member.get());
        }
        scrapRepository.downScrap(post.get(), member.get());
        return null;
    }

    @Override
    public Board readGeneralPost(Long id) {
        Optional<Board> post = boardRepository.findGeneralPostById(id);
        if (post.isEmpty()) {
            return null;
        }
        countView(post.get());
        return post.get();
    }

    @Override
    public List<Board> readGeneralBoard() {
        return boardRepository.findGeneralBoard();
    }

    @Override
    public void deleteGeneralBoard(Long id) {
        boardRepository.removeGeneralBoard(id);
    }

    @Override
    public Free createFreePost(FreeBoardSaveForm freeBoardSaveForm, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return boardRepository.saveFreePost(freeBoardSaveForm.toFreeBoard(member.get()));
    }

    @Override
    public Free updateFreePost(FreeBoardSaveForm freeBoardSaveForm, Long boardId) {
        Optional<Free> post = boardRepository.findFreePostById(boardId);
        Optional<Free> free = boardRepository.modifyFreePost(post.get(), freeBoardSaveForm);
        return free.get();
    }

    @Override
    public Free readFreePost(Long id) {
        Optional<Free> freePost = boardRepository.findFreePostById(id);
        if (freePost.isEmpty()) {
            return null;
        }
        countView(freePost.get());
        return freePost.get();
    }

    @Override
    public List<Free> readFreeBoard() {
        return boardRepository.findFreeBoard();
    }

    @Override
    public void deleteFreeBoard(Long id) {
        boardRepository.removeFreeBoard(id);
    }

    @Override
    public Information createInfoPost(InfoBoardSaveForm infoBoardSaveForm, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return boardRepository.saveInfoPost(infoBoardSaveForm.toInfoBoard(member.get()));
    }

    @Override
    public Information updateInfoPost(InfoBoardSaveForm infoBoardSaveForm, Long boardId) {
        Optional<Information> post = boardRepository.findInfoPostById(boardId);
        Optional<Information> information = boardRepository.modifyInfoPost(post.get(), infoBoardSaveForm);
        return information.get();
    }

    @Override
    public Information readInfoPost(Long id) {
        Optional<Information> infoPost = boardRepository.findInfoPostById(id);
        if (infoPost.isEmpty()) {
            return null;
        }
        countView(infoPost.get());
        return infoPost.get();
    }

    @Override
    public List<Information> readInfoBoard() {
        return boardRepository.findInfoBoard();
    }

    @Override
    public void deleteInfoBoard(Long id) {
        boardRepository.removeInfoBoard(id);
    }

    @Override
    public Recruitment createRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Recruitment recruitment = boardRepository.saveRecruitPost(recruitBoardSaveForm.toRecruitBoard(member.get()));
        boardRepository.saveProjectMember(recruitment, member.get());
        checkClosingRecruit(recruitment);
        return recruitment;
    }

    @Override
    public Recruitment updateRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        Optional<Recruitment> recruitment = boardRepository.modifyRecruitPost(post.get(), recruitBoardSaveForm);
        return recruitment.get();
    }

    @Override
    public Recruitment readRecruitPost(Long id) {
        Optional<Recruitment> recruitPost = boardRepository.findRecruitPostById(id);
        if (recruitPost.isEmpty()) {
            return null;
        }
        countView(recruitPost.get());
        return recruitPost.get();
    }

    @Override
    public List<Recruitment> readRecruitBoard() {
        return boardRepository.findRecruitBoard();
    }

    @Override
    public void deleteRecruitBoard(Long id) {
        boardRepository.removeRecruitBoard(id);
    }

    @Override
    public List<Recruitment> recommendRecruitBoard(Member member) {
        return boardRepository.findRecruitBoard().stream()
                .filter(r -> r.getDeadlineStatus() == false)
                .filter(r -> r.getAutoMatchingStatus() == true)
                .filter(r -> r.getMemberTally() < r.getMemberTotal())
                .filter(r -> boardRepository.findProjectMemberByBoardIdAndMemberId(r.getId(), member.getId()).isEmpty())
                .filter(r -> r.getRequiredMemberLevel() <= member.getMemberAbility().getMemberLevel())
                .collect(Collectors.toList());
    }

    @Override
    public List<Recruitment> readProgressProjectByPage(Integer page, Long memberId) {
        Integer lastProject = page * 10;
        List<Recruitment> recruitments = boardRepository.findProjectMemberByMemberId(memberId).stream()
                .map(r -> r.getRecruitment())
                .collect(Collectors.toList());
        if (recruitments.size() < lastProject) {
            lastProject = recruitments.size();
        }
        return recruitments.subList(page * 10 - 10, lastProject);
    }

    @Override
    public Integer findLastProgressProjectPage(Long memberId) {
        Long projectCount = boardRepository.findProjectMemberByMemberId(memberId).stream()
                .count();
        if (projectCount == 0L) {
            projectCount = 1L;
        }
        Long lastPage = projectCount / 10L;
        if (projectCount % 10L == 0L) {
            return lastPage.intValue();
        }
        return lastPage.intValue() + 1;
    }

    @Override
    public List<Member> findMembersByBoardId(Long boardId) {
        return boardRepository.findProjectMemberByBoardId(boardId).stream()
                .map(pm -> pm.getMember())
                .collect(Collectors.toList());
    }

    @Override
    public Recruitment endRecruit(Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.setDeadlineStatusToTrue(post.get());
    }

    @Override
    public Recruitment restartRecruit(Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.setDeadlineStatusToFalse(post.get());
    }

    @Override
    public ProjectMember applyRecruit(Long boardId, Long memberId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        Optional<Member> member = memberRepository.findById(memberId);
        if (post.isEmpty() || member.isEmpty()) {
            return null;
        }

        Optional<ProjectMember> foundProjectMember = boardRepository.findProjectMemberByBoardIdAndMemberId(boardId, memberId);
        if (foundProjectMember.isPresent()) {
            return foundProjectMember.get();
        }

        ProjectMember projectMember = boardRepository.saveProjectMember(post.get(), member.get());
        if (post.get().getMemberTally() == post.get().getMemberTotal()) {
            endRecruit(boardId);
        }
        return projectMember;
    }

    @Override
    public Boolean checkApplyStatus(Long boardId, Long memberId) {
        Optional<ProjectMember> projectMember = boardRepository.findProjectMemberByBoardIdAndMemberId(boardId, memberId);
        if (projectMember.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkClosingRecruit(Recruitment recruitment) {
        if (recruitment.getMemberTally() >= recruitment.getMemberTotal()) {
            boardRepository.setDeadlineStatusToTrue(recruitment);
            return true;
        }
        return false;
    }

    @Override
    public Recruitment updateProjectSubjectAndPreview(ProgressProjectSaveForm progressProjectSaveForm, Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.modifySubjectAndPreview(post.get(), progressProjectSaveForm);
    }

    @Override
    public ProjectPlan createProjectPlan(ProgressProjectSaveForm progressProjectSaveForm, Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.saveProjectPlan(progressProjectSaveForm.toProjectPlan(post.get()));
    }

    @Override
    public ProjectRule createProjectRule(ProgressProjectSaveForm progressProjectSaveForm, Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.saveProjectRule(progressProjectSaveForm.toProjectRule(post.get()));
    }
}
