package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.BoardCommentSaveForm;
import centum.boxfolio.controller.board.FreeBoardSaveForm;
import centum.boxfolio.controller.board.InfoBoardSaveForm;
import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.board.CommentRepository;
import centum.boxfolio.repository.board.ScrapRepository;
import centum.boxfolio.repository.board.StarRepository;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ScrapRepository scrapRepository;
    private final StarRepository starRepository;
    private final MemberRepository memberRepository;

    @Override
    public Board countView(Board board) {
        return boardRepository.upView(board);
    }

    @Override
    public BoardStar countStar(Long boardId, Long memberId) {
        Optional<BoardStar> boardStar = starRepository.findStarByBoardIdAndMemberId(boardId, memberId);
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Board> post = boardRepository.findGeneralPostById(boardId);
        if (boardStar.isEmpty()) {
            return starRepository.upStar(post.get(), member.get());
        }
        starRepository.downStar(post.get(), member.get());
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
    public Recruitment endRecruit(Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.setRecruitStatusToTrue(post.get());
    }

    @Override
    public Recruitment restartRecruit(Long boardId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        if (post.isEmpty()) {
            return null;
        }
        return boardRepository.setRecruitStatusToFalse(post.get());
    }

    @Override
    public ProjectMember applyRecruit(Long boardId, Long memberId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(boardId);
        Optional<Member> member = memberRepository.findById(memberId);
        if (post.isEmpty() || member.isEmpty()) {
            return null;
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
}
