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

    @NotNull
    private String contents;

    private String visibility;

    private Member member;

    private List<MultipartFile> files;

    public Portfolio toPortfolio() throws ParseException {
        //boolean result;
        //result = visibility.equals("true");

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(contents);
        JSONObject jsonObj = (JSONObject) obj;

        return new Portfolio((String) jsonObj.get("title"), contents, true, member, null, null, null);
    }

}

