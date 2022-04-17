package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.portfolio.Project;

import java.util.List;

public interface ProjectRepository {

    List<Project> findAll();
    List<Project> findProjectsByPortfolioId(Long portfolioId);
}
