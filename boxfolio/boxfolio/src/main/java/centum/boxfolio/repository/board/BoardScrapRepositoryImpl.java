package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class BoardScrapRepositoryImpl implements BoardScrapRepository {

    private final EntityManager em;

    @Override
    public BoardScrap upScrap(Board board, Member member) {
        board.setScrapTally(board.getScrapTally() + 1);
        BoardScrap boardScrap = new BoardScrap(board, member);
        em.persist(boardScrap);
        return boardScrap;
    }

    @Override
    public void downScrap(Board board, Member member) {
        if (board.getScrapTally() > 0) {
            board.setScrapTally(board.getScrapTally() - 1);
        }
        Optional<BoardScrap> boardScrap = findByBoardIdAndMemberId(board.getId(), member.getId());
        em.remove(boardScrap.get());
    }

    @Override
    public List<BoardScrap> findAll() {
        return em.createQuery("select b from BoardScrap b", BoardScrap.class).getResultList();
    }

    @Override
    public Optional<BoardScrap> findByBoardIdAndMemberId(Long boardId, Long memberId) {
        return findAll().stream()
                .filter(b -> b.getBoard().getId() == boardId)
                .filter(b -> b.getMember().getId() == memberId)
                .findAny();
    }

    @Override
    public List<BoardScrap> findByMemberId(Long memberId) {
        return findAll().stream()
                .filter(b -> b.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }
}
