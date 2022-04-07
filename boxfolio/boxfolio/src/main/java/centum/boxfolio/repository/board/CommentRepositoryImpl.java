package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardComment;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.board.Free;
import centum.boxfolio.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {

    private final EntityManager em;

    @Override
    public BoardComment save(BoardComment comment) {
        Board board = comment.getBoard();
        board.setCommentTally(board.getCommentTally() + 1);
        em.persist(comment);
        if (comment.getGroupNum() == null) {
            comment.setGroupNum(comment.getId());
        }
        return comment;
    }

    @Override
    public Optional<BoardComment> findById(Long id) {
        return findAll().stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    @Override
    public List<BoardComment> findCertainCommentsByBoardId(Long boardId) {
        return findAllOrdered().stream()
                .filter(c -> c.getBoard().getId() == boardId)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardComment> findAll() {
        return em.createQuery("SELECT c FROM BoardComment c", BoardComment.class).getResultList();
    }

    @Override
    public List<BoardComment> findAllOrdered() {
        return em.createQuery("SELECT c FROM BoardComment c ORDER BY c.groupNum, c.commentClass, c.commentOrder, c.createdDate", BoardComment.class).getResultList();
    }

    @Override
    public Long findMaxOrderInGroupNum(Long groupNum) {
        return findAll().stream()
                .filter(c -> c.getGroupNum() == groupNum)
                .max(Comparator.comparing(c -> c.getGroupNum()))
                .get().getGroupNum();
    }

    @Override
    public void remove(BoardComment comment) {
        Board board = comment.getBoard();
        board.setCommentTally(board.getCommentTally() - 1);
        em.remove(comment);
    }
}
