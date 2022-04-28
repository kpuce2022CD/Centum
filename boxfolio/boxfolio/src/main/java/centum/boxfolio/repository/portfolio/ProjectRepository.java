package centum.boxfolio.repository.portfolio;
import java.util.List;
import java.util.Optional;

import centum.boxfolio.entity.portfolio.Project;

public interface ProjectRepository {

    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(Long projectId);
    List<Project> findProjectsByPortfolioId(Long portfolioId);
    List<Project> findProjectsByMemberId(Long memberId);
}
