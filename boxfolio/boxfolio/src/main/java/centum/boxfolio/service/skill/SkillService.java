package centum.boxfolio.service.skill;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectSkill;

import java.util.List;
import java.util.Map;

public interface SkillService {

    List<ProjectSkill> uploadProjectSkillsAsLanguage(Project project, Map<String, Long> languages);
    List<MemberSkill> uploadMemberSkillsAsLanguage(Member member, Map<String, Long> languages);
    List<ProjectSkill> findProjectSkillByProjectIdInLanguage(Long projectId);
}
