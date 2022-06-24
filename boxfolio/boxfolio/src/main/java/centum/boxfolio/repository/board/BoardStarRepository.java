package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostStar;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardStarRepository {
    PostStar upStar(Post post, Member member);
    void downStar(Post post, Member member);
    List<PostStar> findAll();
    Optional<PostStar> findByPostIdAndMemberId(Long postId, Long memberId);
}
