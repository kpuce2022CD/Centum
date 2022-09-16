package centum.boxfolio.controller.board;

import centum.boxfolio.dto.board.PostCommentDto;
import centum.boxfolio.dto.board.FreeDto;
import centum.boxfolio.dto.board.InformationDto;
import centum.boxfolio.dto.board.RecruitmentDto;
import centum.boxfolio.dto.project.ProjectDto;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectAnalysis;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.board.BoardService;
import centum.boxfolio.service.board.CommentService;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.project.ProjectService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final ProjectService projectService;
    private final ResponseService responseService;

    @GetMapping("/free")
    public Response<List<Post>> getFreePosts() {
        return responseService.getResult("posts", boardService.findAllFree());
    }

    @GetMapping("/info")
    public Response<List<Post>> getInfoPosts() {
        return responseService.getResult("posts", boardService.findAllInfo());
    }

    @GetMapping("/recruit")
    public Response<List<Post>> getRecruitPosts() {
        return responseService.getResult("posts", boardService.findAllRecruit());
    }

    @GetMapping("/free/{id}")
    public Response<Post> getFreePost(@PathVariable Long id) {
        Post post = boardService.findFreeById(id);
        if (post == null) {
            return responseService.getFailResult(ErrorType.POST_NOT_EXISTS);
        }
        return responseService.getResult("post", post);
    }

    @GetMapping("/info/{id}")
    public Response<Post> getInfoPost(@PathVariable Long id) {
        Information information = boardService.findInfoById(id);
        if (information == null) {
            return responseService.getFailResult(ErrorType.POST_NOT_EXISTS);
        }
        return responseService.getResult("post", information);
    }

    @GetMapping("/recruit/{id}")
    public Response<Post> getRecruitPost(@PathVariable Long id) {
        Recruitment recruitment = boardService.findRecruitById(id);
        if (recruitment == null) {
            return responseService.getFailResult(ErrorType.POST_NOT_EXISTS);
        }
        return responseService.getResult("post", recruitment);
    }

    @PostMapping("/free")
    public Response<FreeDto> createFreePost(@RequestBody @Validated FreeDto freeDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Free free = Free.builder()
                .title(freeDto.getTitle())
                .contents(freeDto.getContents())
                .commentAllow(freeDto.getCommentAllow())
                .scrapAllow(freeDto.getScrapAllow())
                .createdDate(LocalDateTime.now())
                .visibility(freeDto.getVisibility())
                .member(member)
                .build();
        return responseService.getResult("post",convertFreeToFreeDto((Free) boardService.savePost(free)));
    }

    @PostMapping("/info")
    public Response<InformationDto> createInfoPost(@RequestBody @Validated InformationDto informationDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Information information = Information.builder()
                .title(informationDto.getTitle())
                .contents(informationDto.getContents())
                .commentAllow(informationDto.getCommentAllow())
                .scrapAllow(informationDto.getScrapAllow())
                .createdDate(LocalDateTime.now())
                .visibility(informationDto.getVisibility())
                .member(member)
                .build();

        return responseService.getResult("post", convertInformationToInformationDto((Information) boardService.savePost(information)));
    }

    @PostMapping("/recruit")
    public Response<RecruitmentDto> createRecruitPost(@RequestBody @Validated RecruitmentDto recruitmentDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Recruitment recruitment = Recruitment.builder()
                .title(recruitmentDto.getTitle())
                .contents(recruitmentDto.getContents())
                .createdDate(LocalDateTime.now())
                .commentAllow(recruitmentDto.getCommentAllow())
                .scrapAllow(recruitmentDto.getScrapAllow())
                .visibility(recruitmentDto.getVisibility())
                .member(member)
                .autoMatchingStatus(recruitmentDto.getAutoMatchingStatus())
                .deadlineDate(recruitmentDto.getDeadlineDate())
                .memberTally(0L)
                .memberTotal(recruitmentDto.getMemberTotal())
                .projectSubject(recruitmentDto.getProjectSubject())
                .projectField(recruitmentDto.getProjectField())
                .projectLevel(recruitmentDto.getProjectLevel())
                .requiredMemberLevel(recruitmentDto.getRequiredMemberLevel())
                .expectedPeriod(recruitmentDto.getExpectedPeriod())
                .build();

        Recruitment post = (Recruitment) boardService.savePost(recruitment);
        boardService.applyRecruit(post.getId(), member.getId());

        return responseService.getResult("post", convertRecruitmentToRecruitmentDto(post));
    }


    @PutMapping("/free/{id}")
    public Response<FreeDto> modifyFreePost(@PathVariable Long id, @RequestBody @Validated FreeDto freeDto) {
        Free free = new Free();
        free.setModifiableFree(freeDto.getTitle(), freeDto.getContents(), freeDto.getCommentAllow(), freeDto.getScrapAllow(), freeDto.getVisibility());

        return responseService.getResult("post", convertFreeToFreeDto(boardService.modifyFreePost(free, id)));
    }

    @PutMapping("/info/{id}")
    public Response<RecruitmentDto> modifyInfoPost(@PathVariable Long id, @RequestBody @Validated InformationDto informationDto) {
        Information information = new Information();
        information.setModifiableInfo(informationDto.getTitle(), informationDto.getContents(), informationDto.getCommentAllow(), informationDto.getScrapAllow(), informationDto.getVisibility());

        return responseService.getResult("post", convertInformationToInformationDto(boardService.modifyInfoPost(information, id)));
    }

    @PutMapping("/recruit/{id}")
    public Response<RecruitmentDto> modifyRecruitPost(@PathVariable Long id, @RequestBody @Validated RecruitmentDto recruitmentDto) {
        Recruitment recruitment = new Recruitment();
        recruitment.setModifiableRecruit(recruitmentDto.getTitle(), recruitmentDto.getContents(), recruitmentDto.getCommentAllow(), recruitmentDto.getScrapAllow(), recruitmentDto.getVisibility(),
                recruitmentDto.getAutoMatchingStatus(), recruitmentDto.getDeadlineDate(), recruitmentDto.getMemberTotal(), recruitmentDto.getProjectSubject(), recruitmentDto.getProjectField(),
                recruitmentDto.getProjectLevel(), recruitmentDto.getRequiredMemberLevel(), recruitmentDto.getExpectedPeriod());

        return responseService.getResult("post", convertRecruitmentToRecruitmentDto(boardService.modifyRecruitPost(recruitment, id)));
    }

    @DeleteMapping("/{id}")
    public Response deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
        return responseService.getSuccessResult();
    }

    @GetMapping("/recruit/recommends")
    public Response<List<RecruitmentDto>> getRecommendedRecruit(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        List<RecruitmentDto> recruitmentDtos = boardService.recommendRecruitPost(member).stream()
                .map(recruitment -> convertRecruitmentToRecruitmentDto(recruitment))
                .collect(Collectors.toList());

        return responseService.getResult("recommend", recruitmentDtos);
    }

    @GetMapping("/{id}/comments")
    public Response<List<PostCommentDto>> getCommentList(@PathVariable Long id) {
        List<PostComment> comments = commentService.findByPostId(id);
        List<PostCommentDto> postCommentDtos = comments.stream()
                .map(comment -> PostCommentDto.builder()
                        .id(comment.getId())
                        .contents(comment.getContents())
                        .createdDate(comment.getCreatedDate())
                        .commentClass(comment.getCommentClass())
                        .commentOrder(comment.getCommentOrder())
                        .groupNum(comment.getGroupNum())
                        .parentComment(comment.getParentComment())
                        .postId(comment.getPost().getId())
                        .postType(comment.getPost().getPostType())
                        .memberLoginId(comment.getMember().getLoginId())
                        .memberNickname(comment.getMember().getNickname())
                        .build())
                .collect(Collectors.toList());

        return responseService.getResult("comments", postCommentDtos);
    }

    @PostMapping("/{id}/comments")
    public Response<PostCommentDto> createComment(@PathVariable Long id, @RequestBody @Validated PostCommentDto postCommentDto, Principal principal) {
        Post post = boardService.findPostById(id);
        Member member = memberService.findByLoginId(principal.getName());
        PostComment postComment = PostComment.builder()
                .contents(postCommentDto.getContents())
                .createdDate(LocalDateTime.now())
                .commentClass(0)
                .commentOrder(0L)
                .groupNum(null)
                .postComment(null)
                .post(post)
                .member(member)
                .build();

        PostComment savedPostComment = commentService.saveComment(postComment);

        PostCommentDto savedPostCommentDto = PostCommentDto.builder()
                .id(savedPostComment.getId())
                .contents(savedPostComment.getContents())
                .createdDate(savedPostComment.getCreatedDate())
                .commentClass(savedPostComment.getCommentClass())
                .commentOrder(savedPostComment.getCommentOrder())
                .groupNum(savedPostComment.getGroupNum())
                .postId(savedPostComment.getPost().getId())
                .postType(savedPostComment.getPost().getPostType())
                .memberLoginId(savedPostComment.getMember().getLoginId())
                .memberNickname(savedPostComment.getMember().getNickname())
                .build();

        return responseService.getResult("postComment", savedPostCommentDto);
    }

    @PostMapping("/{id}/comments/{commentId}/replies")
    public Response<PostCommentDto> createReply(@PathVariable Long id, @PathVariable Long commentId, @RequestBody @Validated PostCommentDto postCommentDto, Principal principal) {
        Post post = boardService.findPostById(id);
        Member member = memberService.findByLoginId(principal.getName());
        PostComment parentComment = commentService.findById(commentId);
        Long maxOrder = commentService.findMaxOrderInParentComment(parentComment);
        PostComment postComment = PostComment.builder()
                .contents(postCommentDto.getContents())
                .createdDate(LocalDateTime.now())
                .commentClass(1)
                .commentOrder(maxOrder + 1L)
                .groupNum(parentComment.getGroupNum())
                .postComment(parentComment)
                .post(post)
                .member(member)
                .build();

        PostComment savedPostComment = commentService.saveComment(postComment);
        PostCommentDto savedPostCommentDto = PostCommentDto.builder()
                .id(savedPostComment.getId())
                .contents(savedPostComment.getContents())
                .createdDate(savedPostComment.getCreatedDate())
                .commentClass(savedPostComment.getCommentClass())
                .commentOrder(savedPostComment.getCommentOrder())
                .groupNum(savedPostComment.getGroupNum())
                .postId(savedPostComment.getPost().getId())
                .memberLoginId(savedPostComment.getMember().getLoginId())
                .memberNickname(savedPostComment.getMember().getNickname())
                .build();

        return responseService.getResult("postComment", savedPostCommentDto);
    }

    @DeleteMapping("/comments/{id}")
    public Response deletePostComment(@PathVariable Long id, Principal principal) {
        PostComment postComment = commentService.findById(id);
        Member member = memberService.findByLoginId(principal.getName());
        if (postComment.getMember().getId() != member.getId()) {
            return responseService.getFailResultByMessage("작성자가 일치하지 않습니다.");
        }
        commentService.deleteComment(postComment);
        return responseService.getSuccessResult();
    }

    @GetMapping("/recruit/{id}/recruitMembers")
    public Response<List<Member>> getRecruitMembers(@PathVariable Long id) {
        return responseService.getResult("recruitMembers", boardService.findRecruitMembersByPostId(id));
    }

    @GetMapping("/recruit/{id}/isRecruitMember")
    public Response<Boolean> getRecruitMember(@PathVariable Long id, Principal principal) {
        return responseService.getResult("isRecruitMember", boardService.checkRecruitMemberByPostIdAndMemberLoginId(id, principal.getName()));
    }

    @GetMapping("/recruit/{id}/end")
    public Response setRecruitEnd(@PathVariable Long id) {
        boardService.endRecruit(id);

        return responseService.getSuccessResult();
    }

    @GetMapping("/recruit/{id}/restart")
    public Response setRecruitRestart(@PathVariable Long id) {
        boardService.restartRecruit(id);

        return responseService.getSuccessResult();
    }

    @GetMapping("/recruit/{id}/apply")
    public Response addRecruitMember(@PathVariable Long id, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        boardService.applyRecruit(id, member.getId());

        return responseService.getSuccessResult();
    }

    @GetMapping("/recruit/{id}/complete")
    public Response<ProjectDto> createProject(@PathVariable Long id) {
        Recruitment post = boardService.findRecruitById(id);
        Project project = Project.builder()
                .title(post.getProjectSubject())
                .projectField(post.getProjectField())
                .projectPreview("")
                .updatedDate(post.getCreatedDate())
                .memberTally(post.getMemberTally())
                .fromRepository(false)
                .repositoryName("")
                .isCompleted(false)
                .portfolio(null)
                .member(post.getMember())
                .projectAnalysis(new ProjectAnalysis())
                .build();
        Project savedProject = projectService.save(project);
        projectService.saveProjectMembersInRecruit(savedProject, post);
        boardService.deleteRecruitMembersByPostId(post.getId());
        boardService.deletePost(post.getId());
        return responseService.getSuccessResult();
    }

    @GetMapping("/{id}/star")
    public Response countStar(@PathVariable Long id, Principal principal) {
        Post post = boardService.findPostById(id);
        Member member = memberService.findByLoginId(principal.getName());

        return responseService.getResult("postStar", boardService.countStar(post, member));
    }

    @GetMapping("/{id}/scrap")
    public Response countScrap(@PathVariable Long id, Principal principal) {
        Post post = boardService.findPostById(id);
        Member member = memberService.findByLoginId(principal.getName());

        return responseService.getResult("postScrap", boardService.countScrap(post, member));
    }

    private FreeDto convertFreeToFreeDto(Free free) {
        return FreeDto.builder()
                .id(free.getId())
                .title(free.getTitle())
                .contents(free.getContents())
                .createdDate(free.getCreatedDate())
                .commentAllow(free.getCommentAllow())
                .scrapAllow(free.getScrapAllow())
                .visibility(free.getVisibility())
                .commentTally(free.getCommentTally())
                .starTally(free.getStarTally())
                .scrapTally(free.getScrapTally())
                .viewTally(free.getViewTally())
                .memberId(free.getMember().getId())
                .build();
    }

    private InformationDto convertInformationToInformationDto(Information information) {
        return InformationDto.builder()
                .id(information.getId())
                .title(information.getTitle())
                .contents(information.getContents())
                .createdDate(information.getCreatedDate())
                .commentAllow(information.getCommentAllow())
                .scrapAllow(information.getScrapAllow())
                .commentTally(information.getCommentTally())
                .starTally(information.getStarTally())
                .scrapTally(information.getScrapTally())
                .viewTally(information.getViewTally())
                .visibility(information.getVisibility())
                .memberId(information.getMember().getId())
                .build();
    }

    private RecruitmentDto convertRecruitmentToRecruitmentDto(Recruitment recruitment) {
        return RecruitmentDto.builder()
                .id(recruitment.getId())
                .title(recruitment.getTitle())
                .contents(recruitment.getContents())
                .createdDate(recruitment.getCreatedDate())
                .commentAllow(recruitment.getCommentAllow())
                .scrapAllow(recruitment.getScrapAllow())
                .commentTally(recruitment.getCommentTally())
                .starTally(recruitment.getStarTally())
                .scrapTally(recruitment.getScrapTally())
                .viewTally(recruitment.getViewTally())
                .visibility(recruitment.getVisibility())
                .memberId(recruitment.getMember().getId())
                .autoMatchingStatus(recruitment.getAutoMatchingStatus())
                .deadlineStatus(recruitment.getDeadlineStatus())
                .deadlineDate(recruitment.getDeadlineDate())
                .memberTally(recruitment.getMemberTally())
                .memberTotal(recruitment.getMemberTotal())
                .projectSubject(recruitment.getProjectSubject())
                .projectField(recruitment.getProjectField())
                .projectLevel(recruitment.getProjectLevel())
                .requiredMemberLevel(recruitment.getRequiredMemberLevel())
                .expectedPeriod(recruitment.getExpectedPeriod())
                .build();
    }
}
