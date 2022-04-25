package centum.boxfolio.repository.portfolio;

import centum.boxfolio.controller.member.ProjectPlanSaveForm;
import centum.boxfolio.controller.member.ProjectRuleSaveForm;
import centum.boxfolio.controller.member.ProjectSaveForm;
import centum.boxfolio.entity.board.ProjectPlan;
import centum.boxfolio.entity.board.ProjectRule;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.portfolio.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    Project save(Project project);
    List<Project> findAll();
    List<Project> findProjectsByPortfolioId(Long portfolioId);
    List<Project> findProjectsByMemberId(Long memberId);
}
