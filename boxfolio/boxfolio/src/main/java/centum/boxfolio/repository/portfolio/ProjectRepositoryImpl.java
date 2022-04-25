package centum.boxfolio.repository.portfolio;


import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.Project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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
    public Optional<Project> saveProject(Project project, Portfolio portfolio) {
        LocalDateTime today = LocalDateTime.now();
        project.setPortfolio(portfolio);
        project.setUpdatedDate(today);

        em.persist(project);
        return Optional.of(project);
    }

}
