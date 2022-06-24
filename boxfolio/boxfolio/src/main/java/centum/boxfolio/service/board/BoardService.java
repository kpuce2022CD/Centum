package centum.boxfolio.service.board;

import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;

import java.util.List;

public interface BoardService {
    Post savePost(Post post);
    Post findPostById(Long id);
    List<Post> findPostsByMemberId(Long memberId);
    void deletePost(Long id);

    Free modifyFreePost(Free free, Long id);
    Free findFreeById(Long id);
    List<Free> findAllFree();

    Information modifyInfoPost(Information information, Long id);
    Information findInfoById(Long id);
    List<Information> findAllInfo();

    Recruitment modifyRecruitPost(Recruitment recruitment, Long id);
    Recruitment findRecruitById(Long id);
    List<Recruitment> findAllRecruit();
    List<Recruitment> recommendRecruitPost(Member member);

    List<Member> findRecruitMembersByPostId(Long postId);
    Boolean checkRecruitMemberByPostIdAndMemberLoginId(Long postId, String loginId);
    void deleteRecruitMembersByPostId(Long recruitmentId);

    Recruitment endRecruit(Long postId);
    Recruitment restartRecruit(Long postId);
    RecruitMember applyRecruit(Long postId, Long memberId);

    Post countView(Post post);
    PostStar countStar(Post post, Member member);
    PostScrap countScrap(Post post, Member member);
    List<PostScrap> findScrapsByMemberId(Long memberId);
}
