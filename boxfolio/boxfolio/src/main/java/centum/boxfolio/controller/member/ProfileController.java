package centum.boxfolio.controller.member;

import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.BoardComment;
import centum.boxfolio.entity.board.BoardScrap;
import centum.boxfolio.entity.board.ProjectMember;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.member.MemberTitle;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.board.BoardScrapRepository;
import centum.boxfolio.repository.board.CommentRepository;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.repository.member.MemberSkillRepository;
import centum.boxfolio.repository.member.MemberTitleRepository;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final MemberTitleRepository memberTitleRepository;
    private final BoardRepository boardRepository;
    private final BoardScrapRepository boardScrapRepository;
    private final PortfolioRepository portfolioRepository;
    private final CommentRepository commentRepository;
//    private final SkillService skillService;

    @GetMapping
    public String profilePage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Member member = memberRepository.findById(memberId).get();

        List<MemberSkill> memberSkills = memberSkillRepository.findMemberSkillsByMemberId(memberId);
        List<MemberTitle> memberTitles = memberTitleRepository.findMemberTitlesByMemberId(memberId);
        List<BoardScrap> boardScraps = boardScrapRepository.findByMemberId(memberId);
        List<PortfolioScrap> portfolioScraps = portfolioRepository.findScrapByMemberId(memberId);
        List<Board> board = boardRepository.findBoardByMemberId(memberId);
        List<ProjectMember> progressProjects = boardRepository.findProjectMemberByMemberId(memberId);
        List<BoardComment> comments = commentRepository.findCommentsByMemberId(memberId);

        model.addAttribute("member", member);
        model.addAttribute("memberSkills", memberSkills);
        model.addAttribute("memberTitles", memberTitles);
        model.addAttribute("boardScraps", boardScraps);
        model.addAttribute("portfolioScraps", portfolioScraps);
        model.addAttribute("board", board);
        model.addAttribute("progressProjects", progressProjects);
        model.addAttribute("comments", comments);
        return "member/profile";
    }
}
