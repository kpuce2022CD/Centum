package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostStar;
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
public class BoardStarRepositoryImpl implements BoardStarRepository {

    private final EntityManager em;

    @Override
    public PostStar upStar(Post post, Member member) {
        post.setStarTally(post.getStarTally() + 1);
        PostStar postStar = new PostStar(post, member);
        em.persist(postStar);
        return postStar;
    }

    @Override
    public void downStar(Post post, Member member) {
        if (post.getStarTally() > 0) {
            post.setStarTally(post.getStarTally() - 1);
        }
        Optional<PostStar> boardStar = findByPostIdAndMemberId(post.getId(), member.getId());
        em.remove(boardStar.get());
    }

    @Override
    public List<PostStar> findAll() {
        return em.createQuery("select b from PostStar b", PostStar.class).getResultList();
    }

    @Override
    public Optional<PostStar> findByPostIdAndMemberId(Long postId, Long memberId) {
        return findAll().stream()
                .filter(b -> b.getPost().getId() == postId)
                .filter(b -> b.getMember().getId() == memberId)
                .findAny();
    }
}
