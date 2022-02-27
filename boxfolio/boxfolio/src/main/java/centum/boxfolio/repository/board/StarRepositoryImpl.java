package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.board.BoardStar;
import centum.boxfolio.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class StarRepositoryImpl implements StarRepository {

    private final EntityManager em;

    @Override
    public BoardStar upStar(Board board, Member member) {
        board.setStarTally(board.getStarTally() + 1);
        BoardStar boardStar = new BoardStar(board, member);
        em.persist(boardStar);
        return boardStar;
    }

    @Override
    public void downStar(Board board, Member member) {
        if (board.getStarTally() > 0) {
            board.setStarTally(board.getStarTally() - 1);
        }
        Optional<BoardStar> boardStar = findStarByBoardIdAndMemberId(board.getId(), member.getId());
        em.remove(boardStar.get());
    }

    @Override
    public List<BoardStar> findStarAll() {
        return em.createQuery("select b from BoardStar b", BoardStar.class).getResultList();
    }

    @Override
    public Optional<BoardStar> findStarByBoardIdAndMemberId(Long boardId, Long memberId) {
        return findStarAll().stream()
                .filter(b -> b.getBoard().getId() == boardId)
                .filter(b -> b.getMember().getId() == memberId)
                .findAny();
    }
}