package centum.boxfolio.service.project;

import centum.boxfolio.dto.project.ProjectRuleDto;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectMember;
import centum.boxfolio.entity.project.ProjectPlan;
import centum.boxfolio.entity.project.ProjectRule;

import java.util.List;

public interface ProjectService {
    Project save(Project project);
    Project saveCompleteProjectByPortfolio(Portfolio portfolio, Member member, String gitRepository);
    List<Project> findAll();
    Project findById(Long id);
    List<Project> findProjectsByMemberId(Long memberId);
    Project modify(Project project, Project changedProject);
    void delete(Project project);

    ProjectPlan saveProjectPlan(ProjectPlan projectPlan);
    ProjectPlan findProjectPlanById(Long id);
    List<ProjectPlan> findProjectPlansByProjectId(Long projectId);
    void deleteProjectPlan(ProjectPlan projectPlan);

    ProjectRule saveProjectRule(ProjectRule projectRule);
    ProjectRule findProjectRuleById(Long id);
    List<ProjectRule> findProjectRulesByProjectId(Long projectId);
    void deleteProjectRule(ProjectRule projectRule);

    ProjectMember saveProjectMember(ProjectMember projectMember, Boolean useMemberCount);
    List<ProjectMember> saveProjectMembersInRecruit(Project project, Recruitment recruitment);
    List<ProjectMember> findAllProjectMember();
    List<ProjectMember> findProjectMembersByProjectId(Long projectId);
}
