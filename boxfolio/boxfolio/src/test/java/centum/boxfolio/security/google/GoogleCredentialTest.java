package centum.boxfolio.security.google;

import centum.boxfolio.service.mail.GmailHandler;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
class GoogleCredentialTest {

    @Autowired
    private GoogleCredential googleCredential;
    @Autowired
    private GmailHandler gmailHandler;
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Test
    void getGoogleCredential() throws GeneralSecurityException, IOException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credentials = googleCredential.getCredentials(HTTP_TRANSPORT);
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
                .setApplicationName("Boxfolio")
                .build();
    }

    @Test
    void createDraftMessage() throws MessagingException, GeneralSecurityException, IOException {
        Message message = gmailHandler.sendMessage("boxfolio100@gmail.com", "rt3310@naver.com", "테스트메시지", "테스트입니다.");
    }

}