package centum.boxfolio.controller.portfolio;


import centum.boxfolio.entity.portfolio.Project;
import centum.boxfolio.service.gitapi.GitApiImpl;
import centum.boxfolio.service.portfolio.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    GitApiImpl gitApi;
    ProjectServiceImpl projectService;

    @GetMapping("/upload")
    public String addProject (Model model) {
        model.addAttribute("projectLoadForm", new ProjectLoadForm());
        return "/project/project_analysis";
    }

    @PostMapping("/upload")
    public String addProject (@Valid ProjectLoadForm form,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request){

        try {
            gitApi.getCodeForRepo(form.getRepoName(), form.getPersonalToken());
        } catch (IOException e){
            return "/project/project_analysis_error";
        }

        Project project = new Project(form.getRepoName());

        projectService.upload(project, null);

        return "/project/project_analysis";
    }
}
