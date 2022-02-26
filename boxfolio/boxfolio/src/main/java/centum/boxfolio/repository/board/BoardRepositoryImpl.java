package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
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
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public Board saveGeneralPost(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public Optional<Board> findGeneralPostById(Long id) {
        return findGeneralBoard().stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    @Override
    public Optional<Board> findGeneralPostByMemberId(Long memberId) {
        return findGeneralBoard().stream()
                .filter(m -> m.getMember().getId() == memberId)
                .findAny();
    }

    @Override
    public List<Board> findGeneralBoard() {
        return em.createQuery("SELECT b FROM Board b", Board.class).getResultList();
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
    public Board upView(Board board) {
        board.setViewTally(board.getViewTally() + 1);
        return board;
    }
}
