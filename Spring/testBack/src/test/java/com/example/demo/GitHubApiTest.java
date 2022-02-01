package com.example.demo;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHGistFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
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
			return;
		}
	}
	
	public void downloadFile(GHContent content) {
		final Logger LOG = Logger.getGlobal();
		LOG.info(content.getName() + " download start");
		
		try {
			String dURL = content.getDownloadUrl();
			String dir = "D:\\tttt";
			
			downloadToDir(new URL(dURL), new File(dir));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void downloadToDir(URL url, File dir) throws IOException {
		if (url==null) throw new IllegalArgumentException("url is null.");
		if (dir==null) throw new IllegalArgumentException("directory is null.");
		if (!dir.exists()) throw new IllegalArgumentException("directory is not existed.");
		if (!dir.isDirectory()) throw new IllegalArgumentException("directory is not a directory.");
		downloadTo(url, dir, true);
	}
	
	public void downloadTo(URL url, File targetFile, boolean isDirectory) throws IOException{            
    	
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
 
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            File saveFilePath=null;
            
            if (isDirectory) {
	            if (disposition != null) {
	                int index = disposition.indexOf("filename=");
	                if (index > 0) {
	                    fileName = disposition.substring(index + 10,
	                            disposition.length() - 1);
	                }
	            } else {
	            	String fileURL=url.toString();
	                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
	                int questionIdx=fileName.indexOf("?");
	                if (questionIdx>=0) {
	                	fileName=fileName.substring(0, questionIdx);
	                }
	                fileName=URLDecoder.decode(fileName);
	            }	 
	            saveFilePath = new File(targetFile, fileName);
            }
            else {
            	saveFilePath=targetFile;
            }
            
            InputStream inputStream = httpConn.getInputStream();
             
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
            System.out.println("File downloaded to " + saveFilePath);
        } else {
            System.err.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();	    
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
			LOG.info("repo down load success");
			
			for (GHContent i : tempFile) {
            	downloadDirectory(i, i.getName());
            }
			
			LOG.info(repo.getLanguage());
			
		} catch (Exception e) {
			LOG.info("repo access fail");
			LOG.info(e.toString());
		}
	}
}
