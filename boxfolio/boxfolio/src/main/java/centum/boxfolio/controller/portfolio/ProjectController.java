package centum.boxfolio.controller.portfolio;


import centum.boxfolio.controller.member.SessionConst;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.Project;
import centum.boxfolio.entity.portfolio.ProjectSkill;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.repository.portfolio.ProjectRepository;
import centum.boxfolio.service.gitapi.GitApiImpl;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.portfolio.ProjectService;
import centum.boxfolio.service.skill.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.json.simple.JSONObject;
import org.kohsuke.github.GHRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    private final ProjectService projectService;
    private final PortfolioService portfolioService;
    private final SkillService skillService;

    private final GitApiImpl gitApi;

    @GetMapping("/upload")
    public String addProject (Model model) {
        model.addAttribute("projectSaveForm", new ProjectSaveForm());
        return "/project/project_analysis";
    }

    @ResponseBody
    @PostMapping("/upload")
    public Mono<String> addProject (@Valid ProjectSaveForm projectSaveForm, BindingResult bindingResult, @RequestBody String data,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request){
        log.info("data: " + data);
        try {
            HttpSession session = request.getSession(false);
            Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
            Optional<Member> member = memberRepository.findById(memberId);


            GHRepository repository = gitApi.getCodeForRepo(projectSaveForm.getRepoName(), projectSaveForm.getPersonalToken());
            Project project = projectService.uploadProjectFromPortfolio(projectSaveForm, getPortfolioBySessionId(request), repository.getHttpTransportUrl());
            skillService.uploadProjectSkillsAsLanguage(project, repository.listLanguages());
            if (member.isPresent()) {
                skillService.uploadMemberSkillsAsLanguage(member.get(), repository.listLanguages());
            }
            WebClient webClient = WebClient.builder().baseUrl("http://127.0.0.1:5000")
                    .defaultCookie("JSESSON", request.getSession(false).getAttribute(SessionConst.LOGIN_MEMBER).toString())
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("apply", true);

            return webClient.post()
                    .uri("/api")
                    .body(Mono.just(jsonObject.toString()), String.class)
                    .retrieve()
                    .bodyToMono(String.class);

        } catch (IOException e){
//            return "/project/project_analysis_error";
            return null;
        }
//
//        if (getPortfolioBySessionId(request) == null) {
//            return "/project/project_analysis_error";
//        }

//        return "/project/project_analysis";
    }

    @GetMapping("/view/{id}")
    public String viewAnalysis(@PathVariable Long id, HttpServletRequest request, Model model){
        String referer = request.getHeader("referer");
        ProjectLoadForm projectLoadForm = new ProjectLoadForm();
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            return "redirect:/" + referer;
        }
        List<ProjectSkill> projectSkills = skillService.findProjectSkillByProjectIdInLanguage(project.get().getId());

        List<String> language = new ArrayList<>();
        List<Long> percent = new ArrayList<>();
        for (ProjectSkill ps : projectSkills) {
            language.add(ps.getSkill().getSkillName());
            percent.add(ps.getQuantity());
        }

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
        projectLoadForm.setLevel(level);
        projectLoadForm.setLibrary(library);

        model.addAttribute("projectLoadForm", projectLoadForm);
        return "project/project_view";
    }

    private Portfolio getPortfolioBySessionId(HttpServletRequest request){
        HttpSession session = request.getSession();
        long memberId = (long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return portfolioService.searchWithMember(memberRepository.findById(memberId).get());
    }
}
