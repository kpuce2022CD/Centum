import org.kohsuke.github.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class GitApiImpl implements GitApi {

    final Logger LOG = Logger.getGlobal();
    private GitHub github = null;

    @Override
    public boolean gitAccess(String personalToken) {
        if (personalToken == null){
            LOG.info("personal token cannot be null");
            return false;
        }
        try {
            this.github = new GitHubBuilder().withOAuthToken(personalToken).build();
            return true;
        } catch (Exception e){
            LOG.info("access github error : " + e);
            return false;
        }

    }

    @Override
    public boolean getAllPublicRepo() {

        if (this.github != null){
            PagedIterable<GHRepository> repoList = this.github.listAllPublicRepositories("2019");

            for (GHRepository i : repoList){
                try {

                    if (!(i.listLanguages().containsKey("Python"))){
                        continue;
                    }
                    LOG.info(i.listLanguages().toString());
                    LOG.info(i.getName());


                    List<GHContent> tempFile = i.getDirectoryContent("");
                    for (GHContent j : tempFile){
                        GitApi.downloadDirectory(j, i.getFullName());
                    }
                } catch (IOException e){
                    LOG.info("git download error at " + i.getName() + ": " + e);
                    return false;
                }
            }

        }
        return false;
    }
}
