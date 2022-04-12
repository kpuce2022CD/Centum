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

    @Override
    public GitHub gitAccess(String personalToken) {

        GitHub gitHub;

        if (personalToken == null){
            LOG.info("personal token cannot be null");
            return null;
        }
        try {
            gitHub = new GitHubBuilder().withOAuthToken(personalToken).build();
            return gitHub;
        } catch (Exception e){
            LOG.info("access github error : " + e);
            return null;
        }

    }

    @Override
    public boolean getAllUsersRepo(String personalToken) {
        GitHub github = gitAccess(personalToken);

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
    public boolean getAllPublicRepo(String path, String personalToken) {

        GitHub github = gitAccess(personalToken);
        if (github != null){
            PagedIterable<GHRepository> repoList = github.listAllPublicRepositories();

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

    @Override
    public void getCodeForRepo(String repoName, String personalToken) throws IOException {
        GitHub github = gitAccess(personalToken);
        GHUser gitUser = github.getMyself();

        GHRepository repository = gitUser.getRepository(repoName);

        String language = repository.getLanguage();

        List<GHContent> ghContentList = repository.getDirectoryContent("");

        for (GHContent c : ghContentList){
            GitApi.downloadDirectory(c, c.getName(), "");
        }

    }
}
