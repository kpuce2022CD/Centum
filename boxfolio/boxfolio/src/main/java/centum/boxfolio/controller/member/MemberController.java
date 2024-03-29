package centum.boxfolio.controller.member;

import centum.boxfolio.SecurityConfig;
import centum.boxfolio.dto.board.PostCommentDto;
import centum.boxfolio.dto.board.PostScrapDto;
import centum.boxfolio.dto.board.SimplePostDto;
import centum.boxfolio.dto.member.*;
import centum.boxfolio.dto.portfolio.PortfolioDto;
import centum.boxfolio.dto.portfolio.PortfolioRowDto;
import centum.boxfolio.dto.portfolio.PortfolioScrapDto;
import centum.boxfolio.dto.project.ProjectDto;
import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostComment;
import centum.boxfolio.entity.board.PostStar;
import centum.boxfolio.entity.member.*;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.exception.AccountException;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import centum.boxfolio.security.JwtTokenProvider;
import centum.boxfolio.security.UserAuthentication;
import centum.boxfolio.service.board.BoardService;
import centum.boxfolio.service.board.CommentService;
import centum.boxfolio.service.member.ConfirmationTokenService;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.project.ProjectService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final ProjectService projectService;
    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final PortfolioService portfolioService;
    private final ResponseService responseService;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public Response<List<Member>> getMembers() {
        return responseService.getResult("members", memberService.findAll());
    }

    @GetMapping("/my")
    public Response<MemberDto> getMember(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        return responseService.getResult("member", convertMemberToMemberDto(member));
    }

    @PutMapping("/my")
    public Response<MemberDto> modifyMember(@RequestBody @Validated MemberDto memberDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        Member changedMember = new Member();
        changedMember.setModifiableMember(memberDto.getPasswd(), memberDto.getRealName(), memberDto.getNickname(), memberDto.getPhone(), memberDto.getBirth(),
                memberDto.getSex(), memberDto.getGithubId(), memberDto.getInterestField(), memberDto.getPersonalToken());

        return responseService.getResult("member", convertMemberToMemberDto(memberService.modifyMember(member, changedMember)));
    }

    @DeleteMapping("/my")
    public Response deleteMember(Principal principal) {
        memberService.delete(memberService.findByLoginId(principal.getName()));
        return responseService.getSuccessResult();
    }

    @GetMapping("/my/ability")
    public Response<MemberAbilityDto> getMemberAbility(Principal principal) {
        MemberAbility memberAbility = memberService.findByLoginId(principal.getName()).getMemberAbility();
        MemberAbilityDto memberAbilityDto = MemberAbilityDto.builder()
                .id(memberAbility.getId())
                .cohesion(memberAbility.getCohesion())
                .complexity(memberAbility.getComplexity())
                .coupling(memberAbility.getCoupling())
                .redundancy(memberAbility.getRedundancy())
                .standard(memberAbility.getStandard())
                .memberLevel(memberAbility.getMemberLevel())
                .build();
        return responseService.getResult("memberAbility", memberAbilityDto);
    }

    @GetMapping("/my/skills")
    public Response<List<MemberSkillDto>> getMemberSkills(Principal principal) {
        List<MemberSkill> memberSkills = memberService.findMemberSkillsByLoginId(principal.getName());
        List<MemberSkillDto> memberSkillDtos = memberSkills.stream()
                .map(memberSkill -> MemberSkillDto.builder()
                        .id(memberSkill.getId())
                        .skillName(memberSkill.getSkill().getSkillName())
                        .skillType(memberSkill.getSkill().getSkillType().getTypeName())
                        .quantity(memberSkill.getQuantity())
                        .memberId(memberSkill.getMember().getId())
                        .build())
                .collect(Collectors.toList());
        return responseService.getResult("memberSkills", memberSkillDtos);
    }

    @GetMapping("/my/titles")
    public Response<List<MemberTitleDto>> getMemberTitles(Principal principal) {
        List<MemberTitle> memberTitles = memberService.findMemberTitlesByLoginId(principal.getName());
        List<MemberTitleDto> memberTitleDtos = memberTitles.stream()
                .map(memberTitle -> MemberTitleDto.builder()
                        .id(memberTitle.getId())
                        .memberId(memberTitle.getMember().getId())
                        .titleName(memberTitle.getTitle().getTitleName())
                        .build())
                .collect(Collectors.toList());
        return responseService.getResult("memberTitles", memberTitleDtos);
    }

    @GetMapping("/my/projects")
    public Response<List<ProjectDto>> getProjects(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        List<ProjectDto> projectDtos = projectService.findProjectsByMemberId(member.getId()).stream()
                .map(project -> {
                    Long portfolioId = null;
                    if (project.getPortfolio() != null) {
                        portfolioId = project.getPortfolio().getId();
                    }
                    return ProjectDto.builder()
                            .id(project.getId())
                            .title(project.getTitle())
                            .projectField(project.getProjectField())
                            .projectPreview(project.getProjectPreview())
                            .updatedDate(project.getUpdatedDate())
                            .memberTally(project.getMemberTally())
                            .fromRepository(project.getFromRepository())
                            .repositoryName(project.getRepositoryName())
                            .isCompleted(project.getIsCompleted())
                            .portfolioId(portfolioId)
                            .memberId(project.getMember().getId())
                            .cohesion(project.getProjectAnalysis().getCohesion())
                            .coupling(project.getProjectAnalysis().getCoupling())
                            .complexity(project.getProjectAnalysis().getComplexity())
                            .redundancy(project.getProjectAnalysis().getRedundancy())
                            .standard(project.getProjectAnalysis().getStandard())
                            .build();
                })
                .collect(Collectors.toList());

        return responseService.getResult("projects", projectDtos);
    }

    @GetMapping("/my/postScraps")
    public Response<List<PostScrapDto>> getPostScraps(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        List<PostScrapDto> postScrapDtos = boardService.findScrapsByMemberId(member.getId()).stream()
                .map(postScrap -> PostScrapDto.builder()
                        .id(postScrap.getId())
                        .postId(postScrap.getPost().getId())
                        .postTitle(postScrap.getPost().getTitle())
                        .postType(postScrap.getPost().getPostType())
                        .postWriterNickname(postScrap.getPost().getMember().getNickname())
                        .memberId(postScrap.getMember().getId())
                        .build())
                .collect(Collectors.toList());

        return responseService.getResult("postScraps", postScrapDtos);
    }

    @GetMapping("/my/portfolioScraps")
    public Response<List<PortfolioScrapDto>> getPortfolioScraps(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        List<PortfolioScrapDto> portfolioScrapDtos = portfolioService.findScrapsByMemberId(member.getId()).stream()
                .map(portfolioScrap -> PortfolioScrapDto.builder()
                        .id(portfolioScrap.getId())
                        .portfolioId(portfolioScrap.getPortfolio().getId())
                        .portfolioTitle(portfolioScrap.getPortfolio().getTitle())
                        .portfolioWriterNickname(portfolioScrap.getPortfolio().getMember().getNickname())
                        .memberId(portfolioScrap.getMember().getId())
                        .build())
                .collect(Collectors.toList());

        return responseService.getResult("portfolioScraps", portfolioScrapDtos);
    }

    @GetMapping("/my/portfolio")
    public Response<PortfolioDto> getPortfolio(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());

        return responseService.getResult("portfolio", convertPortfolioToPortfolioDto(portfolioService.findByMemberId(member.getId())));
    }

    @GetMapping("/my/posts")
    public Response<List<Post>> getPosts(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        List<SimplePostDto> simplePostDtos = boardService.findPostsByMemberId(member.getId()).stream()
                .map(post -> SimplePostDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .createdDate(post.getCreatedDate())
                        .commentAllow(post.getCommentAllow())
                        .scrapAllow(post.getScrapAllow())
                        .visibility(post.getVisibility())
                        .postType(post.getPostType())
                        .build())
                .collect(Collectors.toList());

        return responseService.getResult("posts", simplePostDtos);
    }

    @GetMapping("/my/comments")
    public Response<List<PostComment>> getComments(Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());
        List<PostCommentDto> postCommentDtos = commentService.findByMemberId(member.getId()).stream()
                .map(postComment -> PostCommentDto.builder()
                        .id(postComment.getId())
                        .contents(postComment.getContents())
                        .createdDate(postComment.getCreatedDate())
                        .commentClass(postComment.getCommentClass())
                        .commentOrder(postComment.getCommentOrder())
                        .groupNum(postComment.getGroupNum())
                        .parentComment(postComment.getParentComment())
                        .postId(postComment.getPost().getId())
                        .postType(postComment.getPost().getPostType())
                        .memberLoginId(postComment.getMember().getLoginId())
                        .memberNickname(postComment.getMember().getNickname())
                        .build()
                )
                .collect(Collectors.toList());

        return responseService.getResult("comments", postCommentDtos);
    }

    @PostMapping("/my/personalToken")
    public Response<MemberDto> savePersonalToken(@RequestBody @Validated MemberDto memberDto, Principal principal) {
        Member member = memberService.setPersonalToken(memberService.findByLoginId(principal.getName()), memberDto.getPersonalToken());

        return responseService.getResult("member", convertMemberToMemberDto(member));
    }

    @PostMapping("/login")
    public Response<LoginResultDto> login(@RequestBody @Validated LoginDto loginDto, HttpServletResponse response) throws UnsupportedEncodingException {
        String loginId = loginDto.getLoginId();
        String passwd = loginDto.getPasswd();

        Member member = memberService.findByLoginId(loginId);
        if (!bCryptPasswordEncoder.matches(passwd, member.getPasswd())) {
            return responseService.getFailResult(ErrorType.PASSWORD_ERROR);
        }

        UserAuthentication userAuthentication = new UserAuthentication(loginId, passwd);
        Map<String, String> tokens = jwtTokenProvider.generateTokenSet(userAuthentication.getName(), new HashMap<>());
        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        Cookie cookie = new Cookie("token", "Bearer+" + accessToken);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 1);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        memberService.setRefreshToken(member, "Bearer+" + refreshToken);

        return responseService.getSuccessResult();
    }

    @GetMapping("/logout")
    public Response logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return responseService.getSuccessResult();
    }

    @PostMapping("/signup")
    public Response<Member> signup(@RequestBody @Validated MemberDto memberDto) throws MessagingException, GeneralSecurityException, IOException {
        Member member = Member.builder()
                .loginId(memberDto.getLoginId())
                .passwd(memberDto.getPasswd())
                .realName(memberDto.getRealName())
                .nickname(memberDto.getNickname())
                .phone(memberDto.getPhone())
                .email(memberDto.getEmail())
                .birth(memberDto.getBirth())
                .sex(memberDto.getSex())
                .githubId("")
                .interestField(memberDto.getInterestField())
                .progressField("")
                .role(Role.USER)
                .memberAbility(new MemberAbility())
                .emailVerified(false)
                .personalToken("")
                .build();
        member.encryptPassword(bCryptPasswordEncoder);
        Member savedMember = memberService.signup(member);
        confirmationTokenService.createEmailConfirmationToken(savedMember.getId(), savedMember.getEmail());
        return responseService.getResult("member", savedMember);
    }

    @PostMapping("/loginId")
    public Response<MemberDuplicationCheckDto> checkLoginIdDuplication(@RequestBody @Validated MemberDuplicationCheckDto memberDuplicationCheckDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResultByMessage("입력 형식이 맞지 않습니다.");
        }

        MemberDuplicationCheckDto duplicationCheckDto = MemberDuplicationCheckDto.builder()
                .loginId(memberDuplicationCheckDto.getLoginId())
                .isExist(memberService.checkLoginIdDuplication(memberDuplicationCheckDto.getLoginId()))
                .build();
        return responseService.getResult("memberDuplication", duplicationCheckDto);
    }

    @GetMapping("/token")
    public Response confirmToken(@RequestParam String token) {
        log.info("{}", token);
        Member member = memberService.confirmToken(token);
        if (member == null) {
            return responseService.getFailResult(ErrorType.TOKEN_VALIDATED_ERROR);
        }
        return responseService.getResult("member", member);
    }

    private MemberDto convertMemberToMemberDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .passwd(member.getPasswd())
                .realName(member.getRealName())
                .nickname(member.getNickname())
                .phone(member.getPhone())
                .email(member.getEmail())
                .birth(member.getBirth())
                .sex(member.getSex())
                .githubId(member.getGithubId())
                .interestField(member.getInterestField())
                .progressField(member.getProgressField())
                .personalToken(member.getPersonalToken())
                .build();
    }

    private PortfolioDto convertPortfolioToPortfolioDto(Portfolio portfolio) {
        List<PortfolioRowDto> portfolioRowDtos = portfolioService.findPortfolioRowsByPortfolioId(portfolio.getId()).stream()
                .map(portfolioRow -> PortfolioRowDto.builder()
                        .id(portfolioRow.getId())
                        .rowType(portfolioRow.getRowType())
                        .saveType(portfolioRow.getSaveType())
                        .contents(portfolioRow.getContents())
                        .rowOrder(portfolioRow.getRowOrder())
                        .build())
                .collect(Collectors.toList());
        PortfolioDto portfolioDto = PortfolioDto.builder()
                .id(portfolio.getId())
                .title(portfolio.getTitle())
                .updatedDate(portfolio.getUpdatedDate())
                .visibility(portfolio.getVisibility())
                .starTally(portfolio.getStarTally())
                .scrapTally(portfolio.getScrapTally())
                .member(portfolio.getMember())
                .portfolioRows(portfolioRowDtos)
                .build();
        return portfolioDto;
    }

}
