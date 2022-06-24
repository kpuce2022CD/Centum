package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostComment;
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
    public PostComment save(PostComment comment) {
        Post post = comment.getPost();
        post.setCommentTally(post.getCommentTally() + 1);
        em.persist(comment);
        if (comment.getGroupNum() == null) {
            comment.setGroupNum(comment.getId());
        }
        return comment;
    }

    @Override
    public Optional<PostComment> findById(Long id) {
        return findAll().stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    @Override
    public List<PostComment> findCommentsByMemberId(Long memberId) {
        return findAll().stream()
                .filter(c -> c.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostComment> findCommentsByPostId(Long PostId) {
        return findAllOrdered().stream()
                .filter(c -> c.getPost().getId() == PostId)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostComment> findAll() {
        return em.createQuery("SELECT c FROM PostComment c", PostComment.class).getResultList();
    }

    @Override
    public List<PostComment> findAllOrdered() {
        return em.createQuery("SELECT c FROM PostComment c ORDER BY c.groupNum, c.commentClass, c.commentOrder, c.createdDate", PostComment.class).getResultList();
    }

    @Override
    public Long countCommentsByPostId(Long postId) {
        return em.createQuery("SELECT COUNT(c) FROM PostComment c WHERE c.post.id = :postId", Long.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    @Override
    public void delete(PostComment comment) {
        Post post = comment.getPost();
        em.remove(comment);
        post.setCommentTally(countCommentsByPostId(post.getId()));
    }

    @Override
    public Long findMaxOrderInGroupNum(Long groupNum) {
        return findAll().stream()
                .filter(c -> c.getGroupNum() == groupNum)
                .max(Comparator.comparing(c -> c.getCommentOrder()))
                .get().getCommentOrder();
    }
}
