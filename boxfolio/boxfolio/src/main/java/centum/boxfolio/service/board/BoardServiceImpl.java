package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.board.BoardStar;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.board.BoardRepository;
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
    public Board createGeneralPost() {
        return null;
    }

    @Override
    public Board updateGeneralPost() {
        return null;
    }

    @Override
    public Board readGeneralPost(Long id) {
        Optional<Board> post = boardRepository.findGeneralPostById(id);
        if (post.isPresent()) {
            countView(post.get());
            return post.get();
        }
        return null;
    }

    @Override
    public List<Board> readGeneralBoard() {
        return boardRepository.findGeneralBoard();
    }

    @Override
    public Recruitment createRecruitPost(RecruitBoardSaveForm recruitBoardSaveForm, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Recruitment recruitBoard = recruitBoardSaveForm.toRecruitBoard(member.get());
        return boardRepository.saveRecruitPost(recruitBoard);
    }

    @Override
    public Recruitment updateRecruitPost() {
        return null;
    }

    @Override
    public Recruitment readRecruitPost(Long id) {
        Optional<Recruitment> recruitPost = boardRepository.findRecruitPostById(id);
        if (recruitPost.isPresent()) {
            countView(recruitPost.get());
            return recruitPost.get();
        }
        return null;
    }

    @Override
    public List<Recruitment> readRecruitBoard() {
        return boardRepository.findRecruitBoard();
    }
}
