package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.PostComment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    PostComment save(PostComment comment);
    Optional<PostComment> findById(Long id);
    List<PostComment> findCommentsByMemberId(Long memberId);
    List<PostComment> findCommentsByPostId(Long PostId);
    List<PostComment> findAll();
    List<PostComment> findAllOrdered();
    Long countCommentsByPostId(Long postId);
    void delete(PostComment comment);
    Long findMaxOrderInGroupNum(Long groupNum);
}
