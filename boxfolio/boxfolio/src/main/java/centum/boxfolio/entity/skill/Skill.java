package centum.boxfolio.entity.skill;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.member.MemberSkill;
import centum.boxfolio.entity.portfolio.ProjectSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_type_id")
    private SkillType skillType;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<ProjectSkill> projectSkills = new ArrayList<ProjectSkill>();

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<MemberSkill> memberSkills = new ArrayList<MemberSkill>();

    public Skill(String skillName, SkillType skillType) {
        this.skillName = skillName;
        setSkillType(skillType);
    }

    private void setSkillType(SkillType skillType) {
        if (this.skillType != null) {
            this.skillType.getSkills().remove(this);
        }
        this.skillType = skillType;
        skillType.getSkills().add(this);
    }
}
