package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostScrap;
import centum.boxfolio.entity.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class BoardScrapRepositoryImpl implements BoardScrapRepository {

    private final EntityManager em;

    @Override
    public PostScrap upScrap(Post post, Member member) {
        post.setScrapTally(post.getScrapTally() + 1);
        PostScrap postScrap = new PostScrap(post, member);
        em.persist(postScrap);
        return postScrap;
    }

    @Override
    public void downScrap(Post post, Member member) {
        if (post.getScrapTally() > 0) {
            post.setScrapTally(post.getScrapTally() - 1);
        }
        Optional<PostScrap> boardScrap = findByPostIdAndMemberId(post.getId(), member.getId());
        em.remove(boardScrap.get());
    }

    @Override
    public List<PostScrap> findAll() {
        return em.createQuery("select b from PostScrap b", PostScrap.class).getResultList();
    }

    @Override
    public Optional<PostScrap> findByPostIdAndMemberId(Long postId, Long memberId) {
        return findAll().stream()
                .filter(b -> b.getPost().getId() == postId)
                .filter(b -> b.getMember().getId() == memberId)
                .findAny();
    }

    @Override
    public List<PostScrap> findByMemberId(Long memberId) {
        return findAll().stream()
                .filter(b -> b.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }
}
