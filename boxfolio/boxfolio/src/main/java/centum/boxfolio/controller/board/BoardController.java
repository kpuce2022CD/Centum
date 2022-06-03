package centum.boxfolio.controller.board;

import centum.boxfolio.controller.member.SessionConst;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.service.board.BoardService;
import centum.boxfolio.service.board.CommentService;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.portfolio.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final ProjectService projectService;
    private final MemberService memberService;

    @GetMapping("/free")
    public String generalBoardPage(Model model) {
        model.addAttribute("freeBoard", boardService.readFreeBoard());
        return "board/free_board";
    }

    @GetMapping("/info")
    public String infoBoardPage(Model model) {
        model.addAttribute("infoBoard", boardService.readInfoBoard());
        return "board/info_board";
    }

    @GetMapping("/recruit")
    public String recruitBoardPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Member member = memberService.findById(memberId);

        model.addAttribute("recommendRecruitBoard", boardService.recommendRecruitBoard(member));
        model.addAttribute("recruitBoard", boardService.readRecruitBoard());
        return "board/recruitment_board";
    }

    @GetMapping("/free/{boardId}")
    public String freeBoardPage(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        Free post = boardService.readFreePost(boardId);
        List<BoardComment> boardComments = commentService.readCommentsByBoardId(boardId);

        model.addAttribute("freePost", post);
        model.addAttribute("comments", boardComments);
        model.addAttribute("boardCommentSaveForm", new BoardCommentSaveForm());
        return "board/free_post";
    }

    @GetMapping("/info/{boardId}")
    public String infoBoardPage(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        Information post = boardService.readInfoPost(boardId);
        List<BoardComment> boardComments = commentService.readCommentsByBoardId(boardId);

        model.addAttribute("infoPost", post);
        model.addAttribute("comments", boardComments);
        model.addAttribute("boardCommentSaveForm", new BoardCommentSaveForm());
        return "board/info_post";
    }

    @GetMapping("/recruit/{boardId}")
    public String recruitBoardPage(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());

        Recruitment post = boardService.readRecruitPost(boardId);
        List<BoardComment> boardComments = commentService.readCommentsByBoardId(boardId);
        Boolean applyStatus = boardService.checkApplyStatus(boardId, memberId);

        model.addAttribute("recruitPost", post);
        model.addAttribute("comments", boardComments);
        model.addAttribute("boardCommentSaveForm", new BoardCommentSaveForm());
        model.addAttribute("applyStatus", applyStatus);
        return "board/recruitment_post";
    }

    @GetMapping("/free/edit")
    public String freeBoardEditorPage(Model model) {
        FreeBoardSaveForm freeBoardSaveForm = new FreeBoardSaveForm();
        freeBoardSaveForm.setVisibility("public");
        freeBoardSaveForm.setCommentAllow(true);
        model.addAttribute("freeBoardSaveForm", freeBoardSaveForm);
        return "board/free_edit";
    }

    @GetMapping("/info/edit")
    public String infoBoardEditorPage(Model model) {
        InfoBoardSaveForm infoBoardSaveForm = new InfoBoardSaveForm();
        infoBoardSaveForm.setVisibility("public");
        infoBoardSaveForm.setCommentAllow(true);
        model.addAttribute("infoBoardSaveForm", infoBoardSaveForm);
        return "board/info_edit";
    }

    @GetMapping("/recruit/edit")
    public String recruitBoardEditorPage(Model model) {
        RecruitBoardSaveForm recruitBoardSaveForm = new RecruitBoardSaveForm();
        recruitBoardSaveForm.setVisibility("public");
        recruitBoardSaveForm.setCommentAllow(true);
        recruitBoardSaveForm.setMemberTotal(1L);
        model.addAttribute("recruitBoardSaveForm", recruitBoardSaveForm);
        return "board/recruitment_edit";
    }

    @PostMapping("/free/edit")
    public String freeBoardEdit(@ModelAttribute FreeBoardSaveForm freeBoardSaveForm, BindingResult bindingResult,
                                HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/free_edit";
        }

        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Free freePost = boardService.createFreePost(freeBoardSaveForm, memberId);
        redirectAttributes.addAttribute("id", freePost.getId());
        return "redirect:/board/free/{id}";
    }

    @PostMapping("/info/edit")
    public String infoBoardEdit(@ModelAttribute InfoBoardSaveForm infoBoardSaveForm, BindingResult bindingResult,
                                HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/info_edit";
        }

        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Information infoPost = boardService.createInfoPost(infoBoardSaveForm, memberId);
        redirectAttributes.addAttribute("id", infoPost.getId());
        return "redirect:/board/info/{id}";
    }

    @PostMapping("/recruit/edit")
    public String recruitBoardEdit(@ModelAttribute RecruitBoardSaveForm recruitBoardSaveForm, BindingResult bindingResult,
                                   HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/recruitment_edit";
        }

        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitPost = boardService.createRecruitPost(recruitBoardSaveForm, memberId);
        redirectAttributes.addAttribute("id", recruitPost.getId());
        return "redirect:/board/recruit/{id}";
    }

    @GetMapping("/free/modify/{boardId}")
    public String modifyFreeBoardPage(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Free free = boardService.readFreePost(boardId);
        if (memberId != free.getMember().getId()) {
            return "redirect:/board/free";
        }
        model.addAttribute("freeBoardSaveForm", free.toFreeBoardSaveForm());
        return "board/free_modify";
    }

    @GetMapping("/info/modify/{boardId}")
    public String modifyInfoBoardPage(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Information information = boardService.readInfoPost(boardId);
        if (memberId != information.getMember().getId()) {
            return "redirect:/board/info";
        }
        model.addAttribute("infoBoardSaveForm", information.toInfoBoardSaveForm());
        return "board/info_modify";
    }

    @GetMapping("/recruit/modify/{boardId}")
    public String modifyRecruitBoardPage(@PathVariable Long boardId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitment = boardService.readRecruitPost(boardId);
        if (memberId != recruitment.getMember().getId()) {
            return "redirect:/board/recruit";
        }
        model.addAttribute("recruitBoardSaveForm", recruitment.toRecruitBoardSaveForm());
        return "board/recruitment_modify";
    }

    @PostMapping("/free/modify/{boardId}")
    public String modifyFreeBoard(@PathVariable Long boardId, @ModelAttribute FreeBoardSaveForm freeBoardSaveForm, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/free_modify";
        }

        Free freePost = boardService.updateFreePost(freeBoardSaveForm, boardId);
        redirectAttributes.addAttribute("id", freePost.getId());
        return "redirect:/board/free/{id}";
    }

    @PostMapping("/info/modify/{boardId}")
    public String modifyInfoBoard(@PathVariable Long boardId, @ModelAttribute InfoBoardSaveForm infoBoardSaveForm, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/info_modify";
        }

        Information freePost = boardService.updateInfoPost(infoBoardSaveForm, boardId);
        redirectAttributes.addAttribute("id", freePost.getId());
        return "redirect:/board/info/{id}";
    }

    @PostMapping("/recruit/modify/{boardId}")
    public String modifyRecruitBoard(@PathVariable Long boardId, @ModelAttribute RecruitBoardSaveForm recruitBoardSaveForm, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/recruitment_modify";
        }

        Recruitment freePost = boardService.updateRecruitPost(recruitBoardSaveForm, boardId);
        redirectAttributes.addAttribute("id", freePost.getId());
        return "redirect:/board/recruit/{id}";
    }

    @GetMapping("/free/delete/{boardId}")
    public String deleteFreeBoard(@PathVariable Long boardId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Free free = boardService.readFreePost(boardId);
        if (memberId == free.getMember().getId()) {
            boardService.deleteFreeBoard(boardId);
        }
        return "redirect:/board/free";
    }

    @GetMapping("/info/delete/{boardId}")
    public String deleteInfoBoard(@PathVariable Long boardId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Information information = boardService.readInfoPost(boardId);
        if (memberId == information.getMember().getId()) {
            boardService.deleteInfoBoard(boardId);
        }
        return "redirect:/board/info";
    }

    @GetMapping("/recruit/delete/{boardId}")
    public String deleteRecruitBoard(@PathVariable Long boardId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitment = boardService.readRecruitPost(boardId);
        if (memberId == recruitment.getMember().getId()) {
            boardService.deleteRecruitBoard(boardId);
        }
        return "redirect:/board/recruit";
    }

    @GetMapping("/recruit/end/{boardId}")
    public String endRecruit(@PathVariable Long boardId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitment = boardService.readRecruitPost(boardId);
        if (memberId == recruitment.getMember().getId()) {
            boardService.endRecruit(boardId);
        }
        redirectAttributes.addAttribute("id", recruitment.getId());
        return "redirect:/board/recruit/{id}";
    }

    @GetMapping("/recruit/restart/{boardId}")
    public String restartRecruit(@PathVariable Long boardId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitment = boardService.readRecruitPost(boardId);
        if (memberId == recruitment.getMember().getId()) {
            boardService.restartRecruit(boardId);
        }
        redirectAttributes.addAttribute("id", recruitment.getId());
        return "redirect:/board/recruit/{id}";
    }

    @GetMapping("recruit/apply/{boardId}")
    public String applyRecruit(@PathVariable Long boardId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        boardService.applyRecruit(boardId, memberId);
        redirectAttributes.addAttribute("id", boardId);
        return "redirect:/board/recruit/{id}";
    }

    @GetMapping("/star/{boardId}")
    public String pushStar(@PathVariable Long boardId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession(false);
        if (referer != null) {
            Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
            boardService.countStar(boardId, memberId);
        }
        return "redirect:" + referer;
    }

    @GetMapping("/scrap/{boardId}")
    public String pushScrap(@PathVariable Long boardId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession(false);
        if (referer != null) {
            Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
            boardService.countScrap(boardId, memberId);
        }

        return "redirect:" + referer;
    }

    @PostMapping("/comment/{boardId}")
    public String postComment(@ModelAttribute BoardCommentSaveForm boardCommentSaveForm, BindingResult bindingResult,
                              @PathVariable Long boardId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (bindingResult.hasErrors()) {
            return referer;
        }
        HttpSession session = request.getSession(false);
        long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        commentService.createComment(boardCommentSaveForm, boardId, memberId);

        return "redirect:" + referer;
    }

    @GetMapping("/comment/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (referer != null) {
            commentService.deleteComment(commentId);
        }
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());

        return "redirect:" + referer;
    }

    @PostMapping("/reply/{commentId}")
    public String postReply(@ModelAttribute BoardCommentSaveForm boardCommentSaveForm, BindingResult bindingResult,
                            @PathVariable Long commentId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (bindingResult.hasErrors()) {
            return referer;
        }
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        BoardComment reply = commentService.createReply(boardCommentSaveForm, commentId, memberId);
        log.info("{}", reply.getGroupNum());

        return "redirect:" + referer;
    }
}
