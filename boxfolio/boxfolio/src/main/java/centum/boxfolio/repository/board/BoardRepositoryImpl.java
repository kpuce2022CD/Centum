package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
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
    public Board saveGeneralBoard(Board board) {
        return null;
    }

    @Override
    public Board saveRecruitBoard(Recruitment recruitment) {
        em.persist(recruitment);
        return recruitment;
    }

    @Override
    public Optional<Board> findById(Long Id) {
        return Optional.empty();
    }

    @Override
    public Optional<Board> findByMemberId(Long memberId) {
        return Optional.empty();
    }

    @Override
    public List<Board> findAll() {
        return null;
    }
}
