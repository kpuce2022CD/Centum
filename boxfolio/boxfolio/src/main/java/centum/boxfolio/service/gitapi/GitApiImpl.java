package centum.boxfolio.service.gitapi;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.GHContent;

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
    public boolean getAllUsersRepo() {

        try {
            GHUser gitUser = github.getMyself();
            PagedIterable<GHRepository> userRepoList = gitUser.listRepositories();

            for (GHRepository i : userRepoList){
                try {
                    List<GHContent> tempFile = i.getDirectoryContent("");
                    for (GHContent j : tempFile){
                        GitApi.downloadDirectory(j, j.getName(), "");
                    }
                } catch (IOException e){
                    LOG.info("git download error at " + i.getName() + ": " + e);
                    return false;
                }
            }

        } catch(IOException e){
            LOG.info("user repository access denied : " + e);
        }
        return false;
    }

    @Override
    public boolean getAllPublicRepo(String path) {

        if (this.github != null){
            PagedIterable<GHRepository> repoList = this.github.listAllPublicRepositories();

            for (GHRepository i : repoList){
                try {
                    List<GHContent> tempFile = i.getDirectoryContent("");
                    for (GHContent j : tempFile){
                        GitApi.downloadDirectory(j, j.getName(), "");
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
