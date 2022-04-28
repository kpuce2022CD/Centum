package centum.boxfolio.entity.skill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class SkillType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeName;

    @OneToMany(mappedBy = "skillType")
    private List<Skill> skills = new ArrayList<Skill>();

    public SkillType(String typeName) {
        this.typeName = typeName;
    }
}
