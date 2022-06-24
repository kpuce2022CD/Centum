package centum.boxfolio.repository.project;

import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectMember;
import centum.boxfolio.entity.project.ProjectPlan;
import centum.boxfolio.entity.project.ProjectRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository{

    private final EntityManager em;

    @Override
    public Project save(Project project) {
        em.persist(project);
        return project;
    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return findAll().stream()
                .filter(project -> project.getId() == id)
                .findAny();
    }

    @Override
    public List<Project> findProjectsByPortfolioId(Long portfolioId) {
        return findAll().stream()
                .filter(p -> p.getPortfolio() != null)
                .filter(p -> p.getPortfolio().getId() == portfolioId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findProjectsByMemberId(Long memberId) {
        return findAll().stream()
                .filter(p -> p.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public Project update(Project project, Project changedProject) {
        project.setModifiableProject(changedProject.getTitle(), changedProject.getProjectField(), changedProject.getProjectPreview(), LocalDateTime.now(),
                changedProject.getMemberTally(), changedProject.getFromRepository(), changedProject.getRepositoryName(), changedProject.getIsCompleted());
        return project;
    }

    @Override
    public void delete(Project project) {
        em.remove(project);
    }

    @Override
    public ProjectPlan saveProjectPlan(ProjectPlan projectPlan) {
        em.persist(projectPlan);
        return projectPlan;
    }

    @Override
    public List<ProjectPlan> findAllProjectPlan() {
        return em.createQuery("SELECT pp FROM ProjectPlan pp", ProjectPlan.class).getResultList();
    }

    @Override
    public Optional<ProjectPlan> findProjectPlanById(Long id) {
        return findAllProjectPlan().stream()
                .filter(projectPlan -> projectPlan.getId() == id)
                .findAny();
    }

    @Override
    public List<ProjectPlan> findProjectPlansByProjectId(Long projectId) {
        return findAllProjectPlan().stream()
                .filter(projectPlan -> projectPlan.getProject().getId() == projectId)
                .sorted(Comparator.comparing(projectPlan -> projectPlan.getStartDate()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProjectPlan(ProjectPlan projectPlan) {
        em.remove(projectPlan);
    }

    @Override
    public ProjectRule saveProjectRule(ProjectRule projectRule) {
        em.persist(projectRule);
        return projectRule;
    }

    @Override
    public List<ProjectRule> findAllProjectRule() {
        return em.createQuery("SELECT pr FROM ProjectRule pr", ProjectRule.class).getResultList();
    }

    @Override
    public Optional<ProjectRule> findProjectRuleById(Long id) {
        return findAllProjectRule().stream()
                .filter(projectRule -> projectRule.getId() == id)
                .findAny();
    }

    @Override
    public List<ProjectRule> findProjectRulesByProjectId(Long projectId) {
        return findAllProjectRule().stream()
                .filter(projectRule -> projectRule.getProject().getId() == projectId)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProjectRule(ProjectRule projectRule) {
        em.remove(projectRule);
    }

    @Override
    public ProjectMember saveProjectMember(ProjectMember projectMember, Boolean useMemberCount) {
        em.persist(projectMember);
        if (useMemberCount) {
            Project project = projectMember.getProject();
            project.setMemberTally(project.getMemberTally() + 1L);
        }
        return projectMember;
    }

    @Override
    public List<ProjectMember> findAllProjectMember() {
        return em.createQuery("SELECT pm FROM ProjectMember pm", ProjectMember.class).getResultList();
    }

    @Override
    public List<ProjectMember> findProjectMembersByProjectId(Long projectId) {
        return findAllProjectMember().stream()
                .filter(projectMember -> projectMember.getProject().getId() == projectId)
                .collect(Collectors.toList());
    }
}
