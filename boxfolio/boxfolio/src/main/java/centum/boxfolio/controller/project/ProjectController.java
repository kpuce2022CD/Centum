package centum.boxfolio.controller.project;

import centum.boxfolio.dto.project.*;
import centum.boxfolio.entity.project.*;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.gitapi.GitApi;
import centum.boxfolio.service.gitapi.GitApiImpl;
import centum.boxfolio.service.project.ProjectService;
import centum.boxfolio.service.response.ResponseService;
import centum.boxfolio.service.skill.SkillService;
import centum.boxfolio.service.skill.SkillServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final SkillService skillService;
    private final ResponseService responseService;
    private final GitApi gitApi;

    @GetMapping
    public Response<ProjectDto> getProjects() {
        List<ProjectDto> projectDtos = projectService.findAll().stream()
                .map(project -> convertProjectToProjectDto(project))
                .collect(Collectors.toList());
        return responseService.getResult("projects", projectDtos);
    }

    @GetMapping("/{id}")
    public Response<ProjectDto> getProject(@PathVariable Long id) {
        Project project = projectService.findById(id);

        return responseService.getResult("project", convertProjectToProjectDto(project));
    }

    @PutMapping("/{id}")
    public Response<ProjectDto> modifyProject(@PathVariable Long id, @RequestBody @Validated ProjectDto projectDto) {
        Project project = projectService.findById(id);
        Project changedProject = new Project();
        changedProject.setModifiableProject(projectDto.getTitle(), projectDto.getProjectField(), projectDto.getProjectPreview(), projectDto.getUpdatedDate(),
                projectDto.getMemberTally(), projectDto.getFromRepository(), projectDto.getRepositoryName(), projectDto.getIsCompleted());

        return responseService.getResult("project", convertProjectToProjectDto(projectService.modify(project, changedProject)));
    }

    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable Long id) {
        projectService.delete(projectService.findById(id));
        return responseService.getSuccessResult();
    }

    @GetMapping("/{id}/plans")
    public Response<List<ProjectPlanDto>> getProjectPlans(@PathVariable Long id) {
        List<ProjectPlanDto> projectPlanDtos = projectService.findProjectPlansByProjectId(id).stream()
                .map(projectPlan -> convertProjectPlanToProjectPlanDto(projectPlan))
                .collect(Collectors.toList());

        return responseService.getResult("projectPlans", projectPlanDtos);
    }

    @PostMapping("/{id}/plans")
    public Response<ProjectPlanDto> createProjectPlan(@PathVariable Long id, @RequestBody @Validated ProjectPlanDto projectPlanDto) {
        ProjectPlan projectPlan = ProjectPlan.builder()
                .startDate(projectPlanDto.getStartDate())
                .endDate(projectPlanDto.getEndDate())
                .contents(projectPlanDto.getContents())
                .project(projectService.findById(id))
                .build();

        ProjectPlan savedProjectPlan = projectService.saveProjectPlan(projectPlan);

        return responseService.getResult("projectPlan", convertProjectPlanToProjectPlanDto(savedProjectPlan));
    }

    @DeleteMapping("/plans/{id}")
    public Response deleteProjectPlan(@PathVariable Long id) {
        projectService.deleteProjectPlan(projectService.findProjectPlanById(id));
        return responseService.getSuccessResult();
    }

    @GetMapping("/{id}/rules")
    public Response<List<ProjectRuleDto>> getProjectRules(@PathVariable Long id) {
        List<ProjectRuleDto> projectRuleDtos = projectService.findProjectRulesByProjectId(id).stream()
                .map(projectRule -> convertProjectRuleToProjectRuleDto(projectRule))
                .collect(Collectors.toList());

        return responseService.getResult("projectRules", projectRuleDtos);
    }

    @PostMapping("/{id}/rules")
    public Response<ProjectRuleDto> createProjectRule(@PathVariable Long id, @RequestBody @Validated ProjectRuleDto projectRuleDto) {
        ProjectRule projectRule = ProjectRule.builder()
                .ruleOrder(projectRuleDto.getRuleOrder())
                .contents(projectRuleDto.getContents())
                .project(projectService.findById(id))
                .build();

        ProjectRule savedProjectRule = projectService.saveProjectRule(projectRule);

        return responseService.getResult("projectRule", convertProjectRuleToProjectRuleDto(savedProjectRule));
    }

    @DeleteMapping("/rules/{id}")
    public Response deleteProjectRule(@PathVariable Long id) {
        projectService.deleteProjectRule(projectService.findProjectRuleById(id));
        return responseService.getSuccessResult();
    }

    @GetMapping("/{id}/projectMembers")
    public Response<List<ProjectMemberDto>> getProjectMembers(@PathVariable Long id) {
        List<ProjectMemberDto> projectMemberDtos = projectService.findProjectMembersByProjectId(id).stream()
                .map(projectMember -> ProjectMemberDto.builder()
                        .id(projectMember.getId())
                        .projectId(projectMember.getProject().getId())
                        .memberId(projectMember.getMember().getId())
                        .memberNickname(projectMember.getMember().getNickname())
                        .memberLevel(projectMember.getMember().getMemberAbility().getMemberLevel())
                        .build())
                .collect(Collectors.toList());

        return responseService.getResult("projectMembers", projectMemberDtos);
    }

    @GetMapping("/{id}/skills")
    public Response<List<ProjectSkillDto>> getProjectSkills(@PathVariable Long id) {
        List<ProjectSkillDto> projectSkillDtos = skillService.findProjectSkillByProjectIdInLanguage(id).stream()
                .map(projectSkill -> ProjectSkillDto.builder()
                        .id(projectSkill.getId())
                        .quantity(projectSkill.getQuantity())
                        .skillName(projectSkill.getSkill().getSkillName())
                        .skillType(projectSkill.getSkill().getSkillType().getTypeName())
                        .build())
                .collect(Collectors.toList());

        return responseService.getResult("projectSkills", projectSkillDtos);
    }

    @PostMapping("/{id}/analysis")
    public Response<List<ProjectSkillDto>> analysisProject(@PathVariable Long id, @RequestBody @Validated ProjectAnalysisDto projectAnalysisDto) throws IOException {
        Project project = projectService.findById(id);
        GHRepository repository = gitApi.getCodeForRepo(projectAnalysisDto.getRepositoryName(), projectAnalysisDto.getPersonalToken());
        List<ProjectSkillDto> projectSkillDtos = skillService.uploadProjectSkillsAsLanguage(project, repository.listLanguages()).stream()
                .map(projectSkill -> ProjectSkillDto.builder()
                        .quantity(projectSkill.getQuantity())
                        .skillName(projectSkill.getSkill().getSkillName())
                        .skillType(projectSkill.getSkill().getSkillType().getTypeName())
                        .build())
                .collect(Collectors.toList());
        for (ProjectMember projectMember : project.getProjectMembers()) {
            skillService.uploadMemberSkillsAsLanguage(projectMember.getMember(), repository.listLanguages());
        }
//        repository.listLanguages().entrySet().stream()
//                .map(language -> ProjectSkill);

        return responseService.getResult("projectSkills", projectSkillDtos);
    }

    private ProjectDto convertProjectToProjectDto(Project project) {
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
    }

    private ProjectPlanDto convertProjectPlanToProjectPlanDto(ProjectPlan savedProjectPlan) {
        return ProjectPlanDto.builder()
                .id(savedProjectPlan.getId())
                .startDate(savedProjectPlan.getStartDate())
                .endDate(savedProjectPlan.getEndDate())
                .contents(savedProjectPlan.getContents())
                .projectId(savedProjectPlan.getProject().getId())
                .build();
    }

    private ProjectRuleDto convertProjectRuleToProjectRuleDto(ProjectRule savedProjectRule) {
        return ProjectRuleDto.builder()
                .id(savedProjectRule.getId())
                .ruleOrder(savedProjectRule.getRuleOrder())
                .contents(savedProjectRule.getContents())
                .projectId(savedProjectRule.getProject().getId())
                .build();
    }
}
