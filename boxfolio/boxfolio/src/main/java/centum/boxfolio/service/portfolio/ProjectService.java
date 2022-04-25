package centum.boxfolio.service.portfolio;

import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.portfolio.Project;

import java.util.List;

public interface ProjectService {
    List<Project> addProjectByRecruitment(Recruitment recruitment);
    List<Project> readCompleteProjectByPage(Integer page, Long memberId);
    Integer findLastCompleteProjectPage(Long memberId);
}