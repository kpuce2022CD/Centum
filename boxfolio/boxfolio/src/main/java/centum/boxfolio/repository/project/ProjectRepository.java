package centum.boxfolio.repository.project;

import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectMember;
import centum.boxfolio.entity.project.ProjectPlan;
import centum.boxfolio.entity.project.ProjectRule;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(Long id);
    List<Project> findProjectsByPortfolioId(Long portfolioId);
    List<Project> findProjectsByMemberId(Long memberId);
    Project update(Project project, Project changedProject);
    void delete(Project project);

    ProjectPlan saveProjectPlan(ProjectPlan projectPlan);
    List<ProjectPlan> findAllProjectPlan();
    Optional<ProjectPlan> findProjectPlanById(Long id);
    List<ProjectPlan> findProjectPlansByProjectId(Long projectId);
    void deleteProjectPlan(ProjectPlan projectPlan);

    ProjectRule saveProjectRule(ProjectRule projectRule);
    List<ProjectRule> findAllProjectRule();
    Optional<ProjectRule> findProjectRuleById(Long id);
    List<ProjectRule> findProjectRulesByProjectId(Long projectId);
    void deleteProjectRule(ProjectRule projectRule);

    ProjectMember saveProjectMember(ProjectMember projectMember, Boolean useMemberCount);
    List<ProjectMember> findAllProjectMember();
    List<ProjectMember> findProjectMembersByProjectId(Long projectId);
}
