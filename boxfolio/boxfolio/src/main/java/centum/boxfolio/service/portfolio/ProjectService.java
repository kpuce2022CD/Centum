//package centum.boxfolio.service.portfolio;
//
//import centum.boxfolio.controller.portfolio.ProjectSaveForm;
//import centum.boxfolio.entity.board.Recruitment;
//import centum.boxfolio.entity.project.Project;
//import centum.boxfolio.entity.portfolio.Portfolio;
//
//import java.util.List;
//
//public interface ProjectService {
//    List<Project> addProjectByRecruitment(Recruitment recruitment);
//    List<Project> readCompleteProjectByPage(Integer page, Long memberId);
//    Integer findLastCompleteProjectPage(Long memberId);
//    Project uploadProjectFromPortfolio(ProjectSaveForm projectSaveForm, Portfolio portfolio, String repoURL);
//}