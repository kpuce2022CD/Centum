package centum.boxfolio.controller.board;

import centum.boxfolio.controller.member.SessionConst;
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
        return "board/recruitment_board";
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

        log.info("{}", recruitBoardSaveForm.getTitle());
        log.info("{}", recruitBoardSaveForm.getContents());
        log.info("{}", recruitBoardSaveForm.getDeadlineYear());
        log.info("{}", recruitBoardSaveForm.getDeadlineMonth());
        log.info("{}", recruitBoardSaveForm.getDeadlineDay());
        log.info("{}", recruitBoardSaveForm.getMemberTotal());
        log.info("{}", recruitBoardSaveForm.getVisibility());
        log.info("{}", recruitBoardSaveForm.isCommentAllow());
        log.info("{}", recruitBoardSaveForm.isScrapAllow());
        log.info("{}", recruitBoardSaveForm.isAutoMatchingStatus());

        HttpSession session = request.getSession(false);
        long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        boardService.createRecruitBoard(recruitBoardSaveForm, memberId);
        return "redirect:/board/recruit";
    }

    @GetMapping("/edit")
    public String boardEditorPage(@RequestParam String type, Model model) {
        model.addAttribute("boardType", type);
        return "board/edit";
    }
}
