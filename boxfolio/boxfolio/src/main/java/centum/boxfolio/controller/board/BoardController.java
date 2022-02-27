package centum.boxfolio.controller.board;

import centum.boxfolio.controller.member.SessionConst;
import centum.boxfolio.entity.board.*;
import centum.boxfolio.service.board.BoardService;
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

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

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
    public String recruitBoardPage(Model model) {
        model.addAttribute("recruitBoard", boardService.readRecruitBoard());
        return "board/recruitment_board";
    }

    @GetMapping("/free/{boardId}")
    public String freeBoardPage(@PathVariable Long boardId, Model model) {
        Free post = boardService.readFreePost(boardId);
        List<BoardComment> boardComments = boardService.readCommentsByBoardId(boardId);
        model.addAttribute("freePost", post);
        model.addAttribute("comments", boardComments);
        model.addAttribute("boardCommentSaveForm", new BoardCommentSaveForm());
        return "board/free_post";
    }

    @GetMapping("/info/{boardId}")
    public String infoBoardPage(@PathVariable Long boardId, Model model) {
        Information post = boardService.readInfoPost(boardId);
        List<BoardComment> boardComments = boardService.readCommentsByBoardId(boardId);
        model.addAttribute("infoPost", post);
        model.addAttribute("comments", boardComments);
        model.addAttribute("boardCommentSaveForm", new BoardCommentSaveForm());
        return "board/info_post";
    }

    @GetMapping("/recruit/{boardId}")
    public String recruitBoardPage(@PathVariable Long boardId, Model model) {
        Recruitment post = boardService.readRecruitPost(boardId);
        List<BoardComment> boardComments = boardService.readCommentsByBoardId(boardId);
        model.addAttribute("recruitPost", post);
        model.addAttribute("comments", boardComments);
        model.addAttribute("boardCommentSaveForm", new BoardCommentSaveForm());
        return "board/recruitment_post";
    }

    @GetMapping("/free/edit")
    public String freeBoardEditorPage(Model model) {
        model.addAttribute("freeBoardSaveForm", new FreeBoardSaveForm());
        return "board/free_edit";
    }

    @GetMapping("/info/edit")
    public String infoBoardEditorPage(Model model) {
        model.addAttribute("infoBoardSaveForm", new InfoBoardSaveForm());
        return "board/info_edit";
    }

    @GetMapping("/recruit/edit")
    public String recruitBoardEditorPage(Model model) {
        model.addAttribute("recruitBoardSaveForm", new RecruitBoardSaveForm());
        return "board/recruitment_edit";
    }

    @PostMapping("/free/edit")
    public String freeBoardEdit(@ModelAttribute FreeBoardSaveForm freeBoardSaveForm, BindingResult bindingResult,
                                HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/board/free_edit";
        }

        HttpSession session = request.getSession(false);
        long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
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
        long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
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
        long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitPost = boardService.createRecruitPost(recruitBoardSaveForm, memberId);
        redirectAttributes.addAttribute("id", recruitPost.getId());
        return "redirect:/board/recruit/{id}";
    }

    @GetMapping("/free/delete/{boardId}")
    public String deleteFreeBoard(@PathVariable Long boardId) {
        boardService.deleteFreeBoard(boardId);
        return "redirect:/board/free";
    }

    @GetMapping("/info/delete/{boardId}")
    public String deleteInfoBoard(@PathVariable Long boardId) {
        boardService.deleteInfoBoard(boardId);
        return "redirect:/board/info";
    }

    @GetMapping("/recruit/delete/{boardId}")
    public String deleteRecruitBoard(@PathVariable Long boardId) {
        boardService.deleteRecruitBoard(boardId);
        return "redirect:/board/recruit";
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
        boardService.createComment(boardCommentSaveForm, boardId, memberId);

        return "redirect:" + referer;
    }

    @GetMapping("/comment/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession(false);
        if (referer != null) {
            boardService.deleteComment(commentId);
        }

        return "redirect:" + referer;
    }
}
