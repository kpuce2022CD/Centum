package com.example.demo;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHGistFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LoginApplication.class)
@SpringBootTest
public class GitHubApiTest {
	
	
	public void downloadDirectory(GHContent content, String path) {
		PagedIterable<GHContent> temp;
		if (content.isDirectory()) {
			try {
				temp = content.listDirectoryContent();
				for (GHContent i : temp) {
	            	if (i.getName().equals(".gitignore")) {
	            		continue;
	            	}
	            	if (i.getName().equals("README.md")) {
	            		continue;
	            	}
	            	if (i.getName().equals("LICENSE")) {
	            		continue;
	            	}
	            	
	            	downloadDirectory(i, path + "/" + i.getName());
	            	
	            }
			} catch (IOException e) {
				System.out.print("download directory error\n");
			}
		}
		else if (content.isFile()) {
			downloadFile(content);
			System.out.print("download success: " + content.getPath() + "\n");
			return;
		}
	}
	
	public void downloadFile(GHContent content) {	}
	
	public void downladWithURL(String URL) {
		
		File file = new File(URL);
		byte b[] = new byte[(int)file.length()];
	}
	
	@Test
	public void gittest() {
		GitHub github = null;
	    GHRepository repo;
	    GHUser guser;
	    

	    
	    final String personalToken = "personal token";
		final String repoName = "KPUCE2021SP/HaneulBori";
	    final Logger LOG = Logger.getGlobal();
	    PagedIterable<GHRepository> temp;
	    
	    List<GHContent> tempFile;
	    
	    String dURL;
	    
		try {
            github = new GitHubBuilder().withOAuthToken(personalToken).build();
            guser = github.getMyself();
            
            temp = guser.listRepositories();
            LOG.info("git account access success");
            
            for (GHRepository i : temp) {
            	
            	LOG.info(i.getFullName());
            }
            
        } catch (Exception e) {
            LOG.info("git account access fail");
        }
		
		try {
			repo = github.getRepository(repoName);
			LOG.info("repo access success" + " " + repo.getName());
			
			tempFile = repo.getDirectoryContent("");
			
			for (GHContent i : tempFile) {
            	//downloadDirectory(i, i.getName());
            	dURL = i.getDownloadUrl();
            	
            	
            }
			
			LOG.info(repo.getLanguage());
			
		} catch (Exception e) {
			LOG.info("repo access fail");
			LOG.info(e.toString());
		}
	}
}
