package centum.boxfolio.service.board;

import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.exception.AccountException;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.exception.PostException;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.board.BoardScrapRepository;
import centum.boxfolio.repository.board.BoardStarRepository;
import centum.boxfolio.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardScrapRepository postScrapRepository;
    private final BoardStarRepository postStarRepository;
    private final MemberRepository memberRepository;

    @Override
    public Post savePost(Post post) {
        return boardRepository.savePost(post);
    }

    @Override
    public Post findPostById(Long id) {
        Optional<Post> post = boardRepository.findPostById(id);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }

        return post.get();
    }

    @Override
    public List<Post> findPostsByMemberId(Long memberId) {
        return boardRepository.findPostsByMemberId(memberId);
    }

    @Override
    public void deletePost(Long id) {
        Optional<Post> post = boardRepository.findPostById(id);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        boardRepository.deletePost(post.get());
    }

    @Override
    public Free modifyFreePost(Free free, Long id) {
        Optional<Free> post = boardRepository.findFreePostById(id);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        return boardRepository.updateFreePost(post.get(), free);
    }

    @Override
    public Free findFreeById(Long id) {
        Optional<Free> freePost = boardRepository.findFreePostById(id);
        if (freePost.isEmpty()) {
            return null;
        }
        countView(freePost.get());
        return freePost.get();
    }

    @Override
    public List<Free> findAllFree() {
        return boardRepository.findAllFreePost();
    }

    @Override
    public Information modifyInfoPost(Information information, Long id) {
        Optional<Information> post = boardRepository.findInfoPostById(id);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        return boardRepository.updateInfoPost(post.get(), information);
    }

    @Override
    public Information findInfoById(Long id) {
        Optional<Information> infoPost = boardRepository.findInfoPostById(id);
        if (infoPost.isEmpty()) {
            return null;
        }
        countView(infoPost.get());
        return infoPost.get();
    }

    @Override
    public List<Information> findAllInfo() {
        return boardRepository.findAllInfoPost();
    }

    @Override
    public Recruitment modifyRecruitPost(Recruitment recruitment, Long id) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(id);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        return boardRepository.updateRecruitPost(post.get(), recruitment);
    }

    @Override
    public Recruitment findRecruitById(Long id) {
        Optional<Recruitment> recruitPost = boardRepository.findRecruitPostById(id);
        if (recruitPost.isEmpty()) {
            return null;
        }
        countView(recruitPost.get());
        return recruitPost.get();
    }

    @Override
    public List<Recruitment> findAllRecruit() {
        return boardRepository.findAllRecruitPost();
    }

    @Override
    public List<Recruitment> recommendRecruitPost(Member member) {
        return boardRepository.findAllRecruitPost().stream()
                .filter(r -> r.getDeadlineStatus() == false)
                .filter(r -> r.getAutoMatchingStatus() == true)
                .filter(r -> r.getMemberTally() < r.getMemberTotal())
                .filter(r -> boardRepository.findRecruitMemberByPostIdAndMemberId(r.getId(), member.getId()).isEmpty())
                .filter(r -> r.getRequiredMemberLevel() <= member.getMemberAbility().getMemberLevel())
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> findRecruitMembersByPostId(Long postId) {
        return boardRepository.findRecruitMembersByPostId(postId).stream()
                .map(pm -> pm.getMember())
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkRecruitMemberByPostIdAndMemberLoginId(Long postId, String loginId) {
        return boardRepository.findRecruitMemberByPostIdAndMemberLoginId(postId, loginId).isPresent();
    }

    @Override
    public void deleteRecruitMembersByPostId(Long recruitmentId) {
        boardRepository.findRecruitMembersByPostId(recruitmentId).stream()
                .forEach(recruitMember -> boardRepository.deleteRecruitMember(recruitMember));
    }

    @Override
    public Recruitment endRecruit(Long postId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(postId);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        return boardRepository.setDeadlineStatusToTrue(post.get());
    }

    @Override
    public Recruitment restartRecruit(Long postId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(postId);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        return boardRepository.setDeadlineStatusToFalse(post.get());
    }

    @Override
    public RecruitMember applyRecruit(Long postId, Long memberId) {
        Optional<Recruitment> post = boardRepository.findRecruitPostById(postId);
        Optional<Member> member = memberRepository.findById(memberId);
        if (post.isEmpty()) {
            throw new PostException(ErrorType.POST_NOT_EXISTS);
        }
        if (member.isEmpty()) {
            throw new AccountException(ErrorType.USER_NOT_EXISTS);
        }
        if (post.get().getMemberTally() == post.get().getMemberTotal()) {
            throw new IllegalArgumentException("이미 인원이 다 찼습니다.");
        }
        Optional<RecruitMember> foundProjectMember = boardRepository.findRecruitMemberByPostIdAndMemberId(postId, memberId);
        if (foundProjectMember.isPresent()) {
            throw new IllegalArgumentException("이미 신청했습니다.");
        }

        RecruitMember recruitMember = boardRepository.saveRecruitMember(post.get(), member.get());
        if (post.get().getMemberTally() == post.get().getMemberTotal()) {
            endRecruit(postId);
        }
        return recruitMember;
    }

    @Override
    public Post countView(Post post) {
        return boardRepository.upView(post);
    }

    @Override
    public PostStar countStar(Post post, Member member) {
        Optional<PostStar> boardStar = postStarRepository.findByPostIdAndMemberId(post.getId(), member.getId());

        if (boardStar.isEmpty()) {
            return postStarRepository.upStar(post, member);
        }
        postStarRepository.downStar(post, member);
        return null;
    }

    @Override
    public PostScrap countScrap(Post post, Member member) {
        Optional<PostScrap> boardScrap = postScrapRepository.findByPostIdAndMemberId(post.getId(), member.getId());
        if (boardScrap.isEmpty()) {
            return postScrapRepository.upScrap(post, member);
        }
        postScrapRepository.downScrap(post, member);
        return null;
    }

    @Override
    public List<PostScrap> findScrapsByMemberId(Long memberId) {
        return postScrapRepository.findByMemberId(memberId);
    }
}
