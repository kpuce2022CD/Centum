package centum.boxfolio.repository.skill;

import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.portfolio.ProjectSkill;
import centum.boxfolio.entity.skill.Skill;
import centum.boxfolio.entity.skill.SkillType;

import java.util.List;
import java.util.Optional;

public interface SkillRepository {

    SkillType saveSkillType(SkillType skillType);
    List<SkillType> findAllSkillType();
    Optional<SkillType> findSkillTypeByTypeName(String typeName);

    Skill saveSkill(Skill skill);
    List<Skill> findAllSkill();
    List<Skill> findSkillsBySkillType(String typeName);
    Optional<Skill> findSkillBySkillName(String skillName);

    ProjectSkill saveProjectSkill(ProjectSkill projectSkill);
    List<ProjectSkill> findAllProjectSkill();
    List<ProjectSkill> findProjectSkillsByProjectId(Long projectId);

    MemberSkill saveMemberSkill(MemberSkill memberSkill);
    List<MemberSkill> findAllMemberSkill();
    List<MemberSkill> findMemberSkillsByMemberId(Long memberId);
}
