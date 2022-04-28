package centum.boxfolio.service.portfolio;

import centum.boxfolio.controller.portfolio.ProjectSaveForm;
import centum.boxfolio.entity.board.ProjectMember;
import centum.boxfolio.entity.board.Recruitment;
import centum.boxfolio.entity.portfolio.Project;
import centum.boxfolio.entity.portfolio.ProjectAnalysis;
import centum.boxfolio.repository.board.BoardRepository;
import centum.boxfolio.repository.portfolio.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import centum.boxfolio.entity.portfolio.Portfolio;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final BoardRepository boardRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> addProjectByRecruitment(Recruitment recruitment) {
        List<ProjectMember> projectMembers = boardRepository.findProjectMemberByBoardId(recruitment.getId());
        List<Project> projects = new ArrayList<>();

        for (ProjectMember projectMember: projectMembers) {
            Project project = new Project(recruitment.getProjectSubject(), recruitment.getProjectField(), LocalDateTime.now(), recruitment.getMemberTally(), false, "", projectMember.getMember(), new ProjectAnalysis());
            projects.add(project);
            projectRepository.save(project);
        }
        return projects;
    }

    @Override
    public List<Project> readCompleteProjectByPage(Integer page, Long memberId) {
        Integer lastProject = page * 10;
        List<Project> projects = projectRepository.findProjectsByMemberId(memberId).stream()
                .collect(Collectors.toList());
        if (projects.size() < lastProject) {
            lastProject = projects.size();
        }
        return projects.subList(page * 10 - 10, lastProject);
    }

    @Override
    public Integer findLastCompleteProjectPage(Long memberId) {
        Long projectCount = projectRepository.findProjectsByMemberId(memberId).stream()
                .count();
        if (projectCount == 0L) {
            projectCount = 1L;
        }

        Long lastPage = projectCount / 10L;
        if (projectCount % 10L == 0L) {
            return lastPage.intValue();
        }
        return lastPage.intValue() + 1;
    }

    @Override
    public Project uploadProjectFromPortfolio(ProjectSaveForm projectSaveForm, Portfolio portfolio, String repoURL) {
        Project project = new Project(projectSaveForm.getRepoName(), "", LocalDateTime.now(), 1L, true, repoURL, portfolio.getMember(), new ProjectAnalysis());
        return projectRepository.save(project);
    }
}