package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.*;
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
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public Post savePost(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public List<Post> findAllPost() {
        return em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    public List<Post> findPostsByMemberId(Long memberId) {
        return findAllPost().stream()
                .filter(b -> b.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return findAllPost().stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    @Override
    public void deletePost(Post post) {
        em.remove(post);
    }

    @Override
    public List<Free> findAllFreePost() {
        return em.createQuery("SELECT f FROM Free f LEFT JOIN Post b ON f.id = b.id", Free.class).getResultList();
    }

    @Override
    public Optional<Free> findFreePostById(Long id) {
        return findAllFreePost().stream()
                .filter(free -> free.getId() == id)
                .findAny();
    }

    @Override
    public Free updateFreePost(Free free, Free changedFree) {
        free.setModifiableFree(changedFree.getTitle(), changedFree.getContents(), changedFree.getCommentAllow(), changedFree.getScrapAllow(), changedFree.getVisibility());
        return free;
    }

    @Override
    public List<Information> findAllInfoPost() {
        return em.createQuery("SELECT i FROM Information i LEFT JOIN Post b ON i.id = b.id", Information.class).getResultList();
    }

    @Override
    public Optional<Information> findInfoPostById(Long id) {
        return findAllInfoPost().stream()
                .filter(information -> information.getId() == id)
                .findAny();
    }

    @Override
    public Information updateInfoPost(Information information, Information changedInfo) {
        information.setModifiableInfo(changedInfo.getTitle(), changedInfo.getContents(), changedInfo.getCommentAllow(), changedInfo.getScrapAllow(), changedInfo.getVisibility());
        return information;
    }

    @Override
    public List<Recruitment> findAllRecruitPost() {
        return em.createQuery("SELECT r FROM Recruitment r LEFT JOIN Post b ON r.id = b.id", Recruitment.class).getResultList();
    }

    @Override
    public Optional<Recruitment> findRecruitPostById(Long id) {
        return findAllRecruitPost().stream()
                .filter(recruitment -> recruitment.getId() == id)
                .findAny();
    }

    @Override
    public Recruitment updateRecruitPost(Recruitment recruitment, Recruitment changedRecruit) {
        recruitment.setModifiableRecruit(changedRecruit.getTitle(), changedRecruit.getContents(), changedRecruit.getCommentAllow(), changedRecruit.getScrapAllow(),
                changedRecruit.getVisibility(), changedRecruit.getAutoMatchingStatus(), changedRecruit.getDeadlineDate(), changedRecruit.getMemberTotal(),
                changedRecruit.getProjectSubject(), changedRecruit.getProjectField(), changedRecruit.getProjectLevel(), changedRecruit.getRequiredMemberLevel(), changedRecruit.getExpectedPeriod());
        return recruitment;
    }

    @Override
    public RecruitMember saveRecruitMember(Recruitment recruitment, Member member) {
        recruitment.setMemberTally(recruitment.getMemberTally() + 1);
        RecruitMember recruitMember = new RecruitMember(recruitment, member);
        em.persist(recruitMember);
        return recruitMember;
    }

    @Override
    public List<RecruitMember> findAllRecruitMember() {
        return em.createQuery("select rm from RecruitMember rm", RecruitMember.class).getResultList();
    }

    @Override
    public List<RecruitMember> findRecruitMembersByMemberId(Long memberId) {
        return findAllRecruitMember().stream()
                .filter(recruitMember -> recruitMember.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruitMember> findRecruitMembersByPostId(Long PostId) {
        return findAllRecruitMember().stream()
                .filter(recruitMember -> recruitMember.getRecruitment().getId() == PostId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RecruitMember> findRecruitMemberByPostIdAndMemberId(Long PostId, Long memberId) {
        return findAllRecruitMember().stream()
                .filter(recruitMember -> recruitMember.getRecruitment().getId() == PostId)
                .filter(recruitMember -> recruitMember.getMember().getId() == memberId)
                .findAny();
    }

    @Override
    public Optional<RecruitMember> findRecruitMemberByPostIdAndMemberLoginId(Long PostId, String loginId) {
        return findAllRecruitMember().stream()
                .filter(recruitMember -> recruitMember.getRecruitment().getId() == PostId)
                .filter(recruitMember -> recruitMember.getMember().getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public void deleteRecruitMember(RecruitMember recruitMember) {
        em.remove(recruitMember);
    }

    /**
     * 마감 완료
     */
    @Override
    public Recruitment setDeadlineStatusToTrue(Recruitment recruitment) {
        recruitment.setDeadlineStatus(true);
        return recruitment;
    }

    @Override
    public Recruitment setDeadlineStatusToFalse(Recruitment recruitment) {
        recruitment.setDeadlineStatus(false);
        return recruitment;
    }

    @Override
    public Post upView(Post post) {
        post.setViewTally(post.getViewTally() + 1);
        return post;
    }
}
