package centum.boxfolio.entity.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.project.Project;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Portfolio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;
    private Boolean visibility;

    private Long starTally;
    private Long scrapTally;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioStar> portfolioStars = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioScrap> portfolioScraps = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioRow> portfolioRows = new ArrayList<>();

    @Builder
    public Portfolio(String title, boolean visibility, Member member){
        this.title = title;
        this.visibility = visibility;
        setMember(member);
        this.starTally = 0L;
        this.scrapTally = 0L;
        this.updatedDate = LocalDateTime.now();
    }

    private void setMember(Member member) {
        if (this.member != null) {
            this.member.getPortfolios().remove(this);
        }
        this.member = member;
        member.getPortfolios().add(this);
    }
    
}
