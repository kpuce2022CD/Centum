package centum.boxfolio.repository.skill;


import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.project.ProjectSkill;
import centum.boxfolio.entity.skill.Skill;
import centum.boxfolio.entity.skill.SkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class SkillRepositoryImpl implements SkillRepository{

    private final EntityManager em;

    @Override
    public SkillType saveSkillType(SkillType skillType) {
        em.persist(skillType);
        return skillType;
    }

    @Override
    public List<SkillType> findAllSkillType() {
        return em.createQuery("SELECT st FROM SkillType st", SkillType.class).getResultList();
    }

    @Override
    public Optional<SkillType> findSkillTypeByTypeName(String typeName) {
        return findAllSkillType().stream()
                .filter(st -> st.getTypeName().equals(typeName))
                .findAny();
    }

    @Override
    public Skill saveSkill(Skill skill) {
        em.persist(skill);
        return skill;
    }

    @Override
    public List<Skill> findAllSkill() {
        return em.createQuery("SELECT s FROM Skill s", Skill.class).getResultList();
    }

    @Override
    public List<Skill> findSkillsBySkillType(String typeName) {
        return findAllSkill().stream()
                .filter(s -> s.getSkillType().getTypeName().equals(typeName))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Skill> findSkillBySkillName(String skillName) {
        return findAllSkill().stream()
                .filter(s -> s.getSkillName().equals(skillName))
                .findAny();
    }

    @Override
    public ProjectSkill saveProjectSkill(ProjectSkill projectSkill) {
        em.persist(projectSkill);
        return projectSkill;
    }

    @Override
    public List<ProjectSkill> findAllProjectSkill() {
        return em.createQuery("SELECT ps FROM ProjectSkill ps", ProjectSkill.class).getResultList();
    }

    @Override
    public List<ProjectSkill> findProjectSkillsByProjectId(Long projectId) {
        return findAllProjectSkill().stream()
                .filter(ps -> ps.getProject().getId() == projectId)
                .collect(Collectors.toList());
    }

    @Override
    public MemberSkill saveMemberSkill(MemberSkill memberSkill) {
        em.persist(memberSkill);
        return memberSkill;
    }

    @Override
    public List<MemberSkill> findAllMemberSkill() {
        return em.createQuery("SELECT ms FROM MemberSkill ms", MemberSkill.class).getResultList();
    }

    @Override
    public List<MemberSkill> findMemberSkillsByMemberId(Long memberId) {
        return findAllMemberSkill().stream()
                .filter(ms -> ms.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }
}
