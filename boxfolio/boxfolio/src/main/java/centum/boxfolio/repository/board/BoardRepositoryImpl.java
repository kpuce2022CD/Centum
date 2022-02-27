package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Free;
import centum.boxfolio.entity.board.Information;
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
    public void removeRecruitBoard(Long id) {
        Optional<Recruitment> post = findRecruitPostById(id);
        em.remove(post.get());
    }

    @Override
    public Board upView(Board board) {
        board.setViewTally(board.getViewTally() + 1);
        return board;
    }
}
