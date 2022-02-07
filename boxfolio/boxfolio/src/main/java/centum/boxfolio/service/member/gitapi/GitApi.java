package centum.boxfolio.service.member.gitapi;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.PagedIterable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Logger;


public interface GitApi {

    String DEFAULT_PATH = "";
    boolean gitAccess(String personalToken);
    boolean getAllUsersRepo();
    boolean getAllPublicRepo(String path);

    static void downloadDirectory(GHContent content, String path, String parentPath) {
        final Logger LOG = Logger.getGlobal();
        PagedIterable<GHContent> temp;
        if (content.isDirectory()) {
            try {
                temp = content.listDirectoryContent();
                for (GHContent i : temp) {
                    downloadDirectory(i, path + "/" + i.getName(), path);
                }
            } catch (IOException e) {
                LOG.info("download directory error : " + e + "\n");
            }
        }
        else if (content.isFile()) {
            downloadFile(content, parentPath);
        }
    }

    static void downloadFile(GHContent content, String path) {
        final Logger LOG = Logger.getGlobal();

        LOG.info(content.getName() + " download start");

        try {
            String dURL = content.getDownloadUrl();
            String dir = DEFAULT_PATH + path.replace("/","\\");
            LOG.info(dir);
            File folder = new File(dir);

            if (!folder.exists()){
                if (folder.mkdirs()){
                    LOG.info("make"  + dir + "success");
                }
            }

            downloadToDir(new URL(dURL), new File(dir));

        } catch (IOException e) {
            LOG.info("download file error : " + e);
        }

    }

    static void downloadToDir(URL url, File dir) throws IOException {
        if (url==null) throw new IllegalArgumentException("url is null.");
        if (dir==null) throw new IllegalArgumentException("directory is null.");
        if (!dir.exists()) throw new IllegalArgumentException("directory is not existed.");
        if (!dir.isDirectory()) throw new IllegalArgumentException("directory is not a directory.");
        downloadTo(url, dir, true);
    }

    static void downloadTo(URL url, File targetFile, boolean isDirectory) throws IOException{
        final Logger LOG = Logger.getGlobal();
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
                    fileName= URLDecoder.decode(fileName);
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
            LOG.info("File downloaded to " + saveFilePath);
        } else {
            LOG.info("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }


}
