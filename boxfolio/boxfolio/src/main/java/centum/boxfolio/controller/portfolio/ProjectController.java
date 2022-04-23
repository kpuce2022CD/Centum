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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    GitApiImpl gitApi;
    ProjectServiceImpl projectService;

    @GetMapping("/upload")
    public String addProject (Model model) {
        model.addAttribute("projectSaveForm", new ProjectSaveForm());
        return "/project/project_analysis";
    }

    @PostMapping("/upload")
    public String addProject (@Valid ProjectSaveForm form,
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

    @GetMapping("/view")
    public String viewAnalysis(Model model){
        ProjectLoadForm projectLoadForm = new ProjectLoadForm();


        List<String> language = new ArrayList<>();
        language.add("JAVA");
        language.add("PYTHON");
        List<Integer> percent = new ArrayList<>();
        percent.add(50);
        percent.add(50);
        List<Integer> level = new ArrayList<>();
        level.add(25);
        level.add(50);
        level.add(100);
        level.add(30);
        level.add(80);
        List<String> library = new ArrayList<>();
        library.add("Spring");
        library.add("pycharm");
        library.add("JPA");

        projectLoadForm.setLanguage(language);
        projectLoadForm.setPercent(percent);
        projectLoadForm.setLanguages(hashMap);
        projectLoadForm.setLevel(level);
        projectLoadForm.setLibrary(library);

        model.addAttribute("projectLoadForm", projectLoadForm);
        return "project/project_view";
    }
}
