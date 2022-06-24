package centum.boxfolio.service.skill;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.project.Project;
import centum.boxfolio.entity.project.ProjectSkill;
import centum.boxfolio.entity.skill.Skill;
import centum.boxfolio.entity.skill.SkillType;
import centum.boxfolio.repository.skill.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{

    private final SkillRepository skillRepository;

    @Override
    public List<ProjectSkill> uploadProjectSkillsAsLanguage(Project project, Map<String, Long> languages) {
        List<ProjectSkill> projectSkills = new ArrayList<>();
        for (String v: languages.keySet()) {
            Skill skill = CheckExistenceSkill(v);
            projectSkills.add(skillRepository.saveProjectSkill(new ProjectSkill(languages.get(v), project, skill)));
        }
        return projectSkills;
    }

    @Override
    public List<MemberSkill> uploadMemberSkillsAsLanguage(Member member, Map<String, Long> languages) {
        List<MemberSkill> memberSkills = new ArrayList<>();
        for (String v: languages.keySet()) {
            Skill skill = CheckExistenceSkill(v);
            memberSkills.add(skillRepository.saveMemberSkill(new MemberSkill(languages.get(v), member, skill)));
        }
        return memberSkills;
    }

    @Override
    public List<ProjectSkill> findProjectSkillByProjectIdInLanguage(Long projectId) {
        return skillRepository.findProjectSkillsByProjectId(projectId).stream()
                .filter(ps -> ps.getSkill().getSkillType().getTypeName().equals("language"))
                .collect(Collectors.toList());
    }

    private Skill CheckExistenceSkill(String v) {
        Optional<Skill> skill = skillRepository.findSkillBySkillName(v);
        if (skill.isEmpty()) {
            Optional<SkillType> language = skillRepository.findSkillTypeByTypeName("language");
            if (language.isEmpty()) {
                language = Optional.ofNullable(skillRepository.saveSkillType(new SkillType("language")));
            }
            skill = Optional.ofNullable(skillRepository.saveSkill(new Skill(v, language.get())));
        }
        return skill.get();
    }
}
