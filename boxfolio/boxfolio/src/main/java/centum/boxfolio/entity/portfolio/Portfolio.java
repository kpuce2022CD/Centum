package centum.boxfolio.entity.portfolio;

import centum.boxfolio.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDate;
    private Boolean visibility;

    private Long starTally;
    private Long scrapTally;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PortfolioStar> portfolioStars = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

        this.starTally = 0L;
        this.scrapTally = 0L;
        this.updatedDate = LocalDateTime.now();
    }
    
}
