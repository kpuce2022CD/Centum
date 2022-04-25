package centum.boxfolio.service.portfolio;

import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.Project;

public interface ProjectService {

    Project upload(Project project, Portfolio portfolio);
}
