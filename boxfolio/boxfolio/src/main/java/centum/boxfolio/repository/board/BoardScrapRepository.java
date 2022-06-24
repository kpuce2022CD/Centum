package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostScrap;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardScrapRepository {
    PostScrap upScrap(Post post, Member member);
    void downScrap(Post post, Member member);

    List<PostScrap> findAll();
    Optional<PostScrap> findByPostIdAndMemberId(Long postId, Long memberId);
    List<PostScrap> findByMemberId(Long memberId);
}
