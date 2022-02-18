package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter  @Setter
public class PortfolioSaveForm {

    @NotBlank
    private String src;


    public Portfolio toPortfolio(){

        /*StringBuilder contents = new StringBuilder();

        contents.append("\\");

        for (String tempValue : src){
            contents.append(tempValue).append("\\");
        }*/

        return new Portfolio(null, src, false, null, null, null, null);
    }

}
