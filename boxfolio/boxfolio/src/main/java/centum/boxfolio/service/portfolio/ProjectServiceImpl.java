package centum.boxfolio.service.portfolio;

import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.Project;
import centum.boxfolio.repository.portfolio.ProjectRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepositoryImpl projectRepository;

    @Override
    public Project upload(Project project, Portfolio portfolio) {
        if (projectRepository.saveProject(project, portfolio).isPresent()){
            return projectRepository.saveProject(project, portfolio).get();
        }
        return null;
    }
}
