package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Getter
@Setter
public class PortfolioSaveForm {


    private String title;

    private String contents;
    private Boolean visibility;
    private Member member;

    private List<MultipartFile> files;

    public Portfolio toPortfolio() throws ParseException {
        //boolean result;
        //result = visibility.equals("true");

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(contents);

        if (jsonObj.get("view").toString().equals("public")) {
            this.visibility = true;
        } else {
            this.visibility = false;
        }
        return new Portfolio( jsonObj.get("title").toString(), contents, visibility, member, null, null, null);
    }
}