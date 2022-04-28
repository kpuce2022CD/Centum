package centum.boxfolio.controller.portfolio;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ProjectLoadForm {

    List<String> language;
    List<Long> percent;
    List<String> library;
    List<Integer> level;
}
