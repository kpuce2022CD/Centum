package centum.boxfolio.domain.skill;

import centum.boxfolio.domain.member.MemberSkill;
import centum.boxfolio.domain.portfolio.ProjectSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String skillName;

    @ManyToOne
    @JoinColumn(name = "skill_type_id")
    private SkillType skillType;

    @OneToMany(mappedBy = "skill")
    private List<ProjectSkill> projectSkills = new ArrayList<ProjectSkill>();

    @OneToMany(mappedBy = "skill")
    private List<MemberSkill> memberSkills = new ArrayList<MemberSkill>();
}
