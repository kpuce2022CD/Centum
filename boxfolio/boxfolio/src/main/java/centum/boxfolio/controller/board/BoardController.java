package centum.boxfolio.controller.board;

import centum.boxfolio.controller.member.SessionConst;
import centum.boxfolio.entity.board.Board;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/general")
    public String generalBoardPage(Model model) {
        model.addAttribute("boardType", BoardTypeConst.GENERAL);
        return "board/board";
    }

    @GetMapping("/info")
    public String infoBoardPage(Model model) {
        model.addAttribute("boardType", BoardTypeConst.INFO);
        return "board/board";
    }

    @GetMapping("/recruit")
    public String recruitmentBoardPage(Model model) {
        model.addAttribute("boardType", BoardTypeConst.RECRUITMENT);
        model.addAttribute("recruitBoards", boardService.readGeneralBoard());
        return "board/recruitment_board";
    }

    @GetMapping("/recruit/{boardId}")
    public String recruitmentBoardPage(@PathVariable long boardId, Model model) {
        Board post = boardService.readGeneralPost(boardId);
        model.addAttribute("recruitPost", post);
        return "board/recruitment_post";
    }

    @GetMapping("/star/{boardId}")
    public String pushStar(@PathVariable long boardId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession(false);
        if (referer != null) {
            Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
            boardService.countStar(boardId, memberId);
        }
        return "redirect:" + referer;
    }

    @GetMapping("/scrap/{boardId}")
    public String pushScrap(@PathVariable long boardId, HttpServletRequest request) {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession(false);
        if (referer != null) {
            Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
            boardService.countScrap(boardId, memberId);
        }
        return "redirect:" + referer;
    }

    @GetMapping("recruit/edit")
    public String recruitmentBoardEditorPage(Model model) {
        model.addAttribute("recruitBoardSaveForm", new RecruitBoardSaveForm());
        return "board/recruitment_edit";
    }

    @PostMapping("recruit/edit")
    public String recruitmentBoardEdit(@ModelAttribute RecruitBoardSaveForm recruitBoardSaveForm, BindingResult bindingResult,
                                       HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/board/recruitment_edit";
        }

        HttpSession session = request.getSession(false);
        long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        boardService.createRecruitPost(recruitBoardSaveForm, memberId);
        return "redirect:/board/recruit";
    }

    @GetMapping("/edit")
    public String boardEditorPage(@RequestParam String type, Model model) {
        model.addAttribute("boardType", type);
        return "board/edit";
    }
}
