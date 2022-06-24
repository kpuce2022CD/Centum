package centum.boxfolio.service.board;

import centum.boxfolio.entity.board.PostComment;
import centum.boxfolio.repository.board.CommentRepository;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public PostComment saveComment(PostComment postComment) {
        return commentRepository.save(postComment);
    }

    @Override
    public List<PostComment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public PostComment findById(Long id) {
        Optional<PostComment> postComment = commentRepository.findById(id);
        if (postComment.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        }
        return postComment.get();
    }

    @Override
    public List<PostComment> findByPostId(Long PostId) {
        return commentRepository.findCommentsByPostId(PostId);
    }

    @Override
    public List<PostComment> findByMemberId(Long memberId) {
        return commentRepository.findCommentsByMemberId(memberId);
    }

    @Override
    public void deleteComment(PostComment postComment) {
        commentRepository.delete(postComment);
    }

    @Override
    public Long findMaxOrderInParentComment(PostComment postComment) {
        return commentRepository.findMaxOrderInGroupNum(postComment.getGroupNum());
    }
}
