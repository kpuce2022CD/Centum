package centum.boxfolio.entity.portfolio;

import centum.boxfolio.entity.member.Member;
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
    private long id;

    private String title;
    private String contents;

    private LocalDateTime updatedDate;
    private boolean visibility;

    private long starTally;
    private long scrapTally;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioStar> portfolioStars = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioScrap> portfolioScraps = new ArrayList<>();



    public Portfolio(String title, String contents, boolean visibility, Member member,
                     List<Project> projects, List<PortfolioStar> portfolioStars,List<PortfolioScrap> portfolioScraps){
        this.title = title;
        this.contents = contents;
        this.visibility = visibility;
        this.member = member;
        this.projects = projects;
        this.portfolioStars = portfolioStars;
        this.portfolioScraps = portfolioScraps;

        this.starTally = 0;
        this.scrapTally = 0;
        this.updatedDate = LocalDateTime.now();
    }
    
}
