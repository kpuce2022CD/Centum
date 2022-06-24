package centum.boxfolio.repository.board;

import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Post savePost(Post post);
    List<Post> findAllPost();
    List<Post> findPostsByMemberId(Long memberId);
    Optional<Post> findPostById(Long id);
    void deletePost(Post post);

    List<Free> findAllFreePost();
    Optional<Free> findFreePostById(Long id);
    Free updateFreePost(Free free, Free changedFree);

    List<Information> findAllInfoPost();
    Optional<Information> findInfoPostById(Long id);
    Information updateInfoPost(Information information, Information changedInfo);

    List<Recruitment> findAllRecruitPost();
    Optional<Recruitment> findRecruitPostById(Long id);
    Recruitment updateRecruitPost(Recruitment recruitment, Recruitment changedRecruit);

    RecruitMember saveRecruitMember(Recruitment recruitment, Member member);
    List<RecruitMember> findAllRecruitMember();
    List<RecruitMember> findRecruitMembersByMemberId(Long memberId);
    List<RecruitMember> findRecruitMembersByPostId(Long PostId);
    Optional<RecruitMember> findRecruitMemberByPostIdAndMemberId(Long PostId, Long memberId);
    Optional<RecruitMember> findRecruitMemberByPostIdAndMemberLoginId(Long PostId, String loginId);
    void deleteRecruitMember(RecruitMember recruitMember);

    Recruitment setDeadlineStatusToTrue(Recruitment recruitment);
    Recruitment setDeadlineStatusToFalse(Recruitment recruitment);

    Post upView(Post post);
}
