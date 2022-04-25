package centum.boxfolio.repository.portfolio;
import java.util.List;

import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.Project;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> saveProject(Project project, Portfolio portfolio);

    // List<Project> findAll();
    // List<Project> findProjectsByPortfolioId(Long portfolioId);

}
