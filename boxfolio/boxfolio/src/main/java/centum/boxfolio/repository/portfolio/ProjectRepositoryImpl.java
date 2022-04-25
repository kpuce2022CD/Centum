package centum.boxfolio.repository.portfolio;


import centum.boxfolio.controller.member.ProjectPlanSaveForm;
import centum.boxfolio.controller.member.ProjectRuleSaveForm;
import centum.boxfolio.controller.member.ProjectSaveForm;
import centum.boxfolio.entity.board.ProjectPlan;
import centum.boxfolio.entity.board.ProjectRule;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
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
    public List<Project> findProjectsByPortfolioId(Long portfolioId) {
        return findAll().stream()
                .filter(p -> p.getPortfolio().getId() == portfolioId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findProjectsByMemberId(Long memberId) {
        return findAll().stream()
                .filter(p -> p.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

//     @Override
//     public Optional<Project> saveProject(Project project, Portfolio portfolio) {
//         LocalDateTime today = LocalDateTime.now();
//         project.setPortfolio(portfolio);
//         project.setUpdatedDate(today);

//         em.persist(project);
//         return Optional.of(project);
//     }

}
