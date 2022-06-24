package centum.boxfolio.service.project;

import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.project.*;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.exception.ProjectException;
import centum.boxfolio.repository.project.ProjectRepository;
import centum.boxfolio.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final BoardService boardService;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project saveCompleteProjectByPortfolio(Portfolio portfolio, Member member, String gitRepository) {
        ProjectAnalysis projectAnalysis = new ProjectAnalysis();
        Project project = Project.builder()
                .title(gitRepository)
                .projectField("")
                .projectPreview("")
                .updatedDate(LocalDateTime.now())
                .memberTally(1L)
                .fromRepository(true)
                .repositoryName(gitRepository)
                .isCompleted(true)
                .portfolio(portfolio)
                .member(member)
                .projectAnalysis(projectAnalysis)
                .build();
        Project savedProject = projectRepository.save(project);
        ProjectMember projectMember = ProjectMember.builder()
                .project(savedProject)
                .member(member)
                .build();
        saveProjectMember(projectMember, false);
        return savedProject;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new IllegalArgumentException(ErrorType.PROJECT_NOT_EXISTS.getMessage());
        }
        return project.get();
    }

    @Override
    public List<Project> findProjectsByMemberId(Long memberId) {
        return projectRepository.findProjectsByMemberId(memberId);
    }

    @Override
    public Project modify(Project project, Project changedProject) {
        return projectRepository.update(project, changedProject);
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public ProjectPlan saveProjectPlan(ProjectPlan projectPlan) {
        return projectRepository.saveProjectPlan(projectPlan);
    }

    @Override
    public ProjectPlan findProjectPlanById(Long id) {
        Optional<ProjectPlan> projectPlan = projectRepository.findProjectPlanById(id);
        if (projectPlan.isEmpty()) {
            throw new ProjectException(ErrorType.PROJECT_PLAN_NOT_EXISTS);
        }
        return projectPlan.get();
    }

    @Override
    public List<ProjectPlan> findProjectPlansByProjectId(Long projectId) {
        return projectRepository.findProjectPlansByProjectId(projectId);
    }

    @Override
    public void deleteProjectPlan(ProjectPlan projectPlan) {
        projectRepository.deleteProjectPlan(projectPlan);
    }

    @Override
    public ProjectRule saveProjectRule(ProjectRule projectRule) {
        return projectRepository.saveProjectRule(projectRule);
    }

    @Override
    public ProjectRule findProjectRuleById(Long id) {
        Optional<ProjectRule> projectRule = projectRepository.findProjectRuleById(id);
        if (projectRule.isEmpty()) {
            throw new ProjectException(ErrorType.PROJECT_RULE_NOT_EXISTS);
        }
        return projectRule.get();
    }

    @Override
    public List<ProjectRule> findProjectRulesByProjectId(Long projectId) {
        return projectRepository.findProjectRulesByProjectId(projectId);
    }

    @Override
    public void deleteProjectRule(ProjectRule projectRule) {
        projectRepository.deleteProjectRule(projectRule);
    }

    @Override
    public ProjectMember saveProjectMember(ProjectMember projectMember, Boolean useMemberCount) {
        return projectRepository.saveProjectMember(projectMember, useMemberCount);
    }

    @Override
    public List<ProjectMember> saveProjectMembersInRecruit(Project project, Recruitment recruitment) {
        List<Member> members = boardService.findRecruitMembersByPostId(recruitment.getId());
        return members.stream()
                .map(member -> projectRepository.saveProjectMember(ProjectMember.builder()
                        .member(member)
                        .project(project)
                        .build(), false))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectMember> findAllProjectMember() {
        return projectRepository.findAllProjectMember();
    }

    @Override
    public List<ProjectMember> findProjectMembersByProjectId(Long projectId) {
        return projectRepository.findProjectMembersByProjectId(projectId);
    }
}
