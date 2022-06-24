package centum.boxfolio.service.board;

import centum.boxfolio.entity.board.PostComment;

import java.util.List;

public interface CommentService {

    PostComment saveComment(PostComment postComment);
    List<PostComment> findAll();
    PostComment findById(Long id);
    List<PostComment> findByPostId(Long PostId);
    List<PostComment> findByMemberId(Long memberId);
    void deleteComment(PostComment postComment);
    Long findMaxOrderInParentComment(PostComment postComment);
}
