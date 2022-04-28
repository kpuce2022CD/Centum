package centum.boxfolio.controller.member;

import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.portfolio.Project;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.service.board.BoardService;
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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectViewController {

    private final BoardService boardService;
    private final ProjectService projectService;
    private final BoardRepository boardRepository;

    @GetMapping("/progress")
    public String progressProjectListPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Integer lastPage = boardService.findLastProgressProjectPage(memberId);
        if (page > lastPage || page < 1) {
            return "redirect:/project/progress?page=" + lastPage;
        }

        List<Recruitment> recruitments = boardService.readProgressProjectByPage(page, memberId);

        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("recruitmentBoard", recruitments);
        return "member/progress_project_list";
    }

    @GetMapping("/complete")
    public String completeProjectListPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Integer lastPage = projectService.findLastCompleteProjectPage(memberId);
        if (page > lastPage || page < 1) {
            return "redirect:/project/complete?page=" + lastPage;
        }

        List<Project> projects = projectService.readCompleteProjectByPage(page, memberId);

        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("projects", projects);
        return "member/complete_project_list";
    }

    @GetMapping("/progress/{id}")
    public String progressProjectPage(@PathVariable Long id, Model model) {
        model.addAttribute("recruitment", boardService.readRecruitPost(id));
        model.addAttribute("members", boardService.findMembersByBoardId(id));
        model.addAttribute("projectRules", boardRepository.findProjectRulesByBoardId(id));
        model.addAttribute("projectPlans", boardRepository.findProjectPlansByBoardId(id));
        return "member/progress_project";
    }

    @GetMapping("/progress/{id}/edit")
    public String progressProjectEditPage(@PathVariable Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Recruitment recruitment = boardService.readRecruitPost(id);
        if (memberId != recruitment.getMember().getId()) {
            return "redirect:/board/recruit";
        }
        model.addAttribute("progressProjectSaveForm", recruitment.toProgressProjectSaveForm());
        model.addAttribute("recruitment", recruitment);
        model.addAttribute("members", boardService.findMembersByBoardId(id));
        model.addAttribute("projectRules", boardRepository.findProjectRulesByBoardId(id));
        model.addAttribute("projectPlans", boardRepository.findProjectPlansByBoardId(id));
        return "member/progress_project_edit";
    }

    @PostMapping("/progress/{id}/edit")
    public String progressProjectSave(@PathVariable Long id, @ModelAttribute ProgressProjectSaveForm progressProjectSaveForm, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "member/progress_project_edit";
        }

        boardService.updateProjectSubjectAndPreview(progressProjectSaveForm, id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/project/progress/{id}";
    }

    @PostMapping("/progress/{id}/plan/save")
    public String progressProjectPlanSave(@PathVariable Long id, @ModelAttribute ProgressProjectSaveForm progressProjectSaveForm, BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "member/progress_project_edit";
        }

        boardService.createProjectPlan(progressProjectSaveForm, id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/project/progress/{id}/edit";
    }

    @PostMapping("/progress/{id}/rule/save")
    public String progressProjectRuleSave(@PathVariable Long id, @ModelAttribute ProgressProjectSaveForm progressProjectSaveForm, BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "member/progress_project_edit";
        }

        boardService.createProjectRule(progressProjectSaveForm, id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/project/progress/{id}/edit";
    }
}
