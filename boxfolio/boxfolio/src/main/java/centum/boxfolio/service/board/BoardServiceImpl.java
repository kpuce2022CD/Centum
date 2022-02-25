package centum.boxfolio.service.board;

import centum.boxfolio.controller.board.RecruitBoardSaveForm;
import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Board createGeneralBoard() {
        return null;
    }

    @Override
    public Board createRecruitBoard(RecruitBoardSaveForm recruitBoardSaveForm, long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Recruitment recruitBoard = recruitBoardSaveForm.toRecruitBoard(member.get());
        return boardRepository.saveRecruitBoard(recruitBoard);
    }

    @Override
    public Board updateRecruitBoard() {
        return null;
    }
}
