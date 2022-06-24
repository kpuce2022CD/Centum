package centum.boxfolio.service.mail;

import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GmailHandler {

    public MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException;
    public Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException;
    public Message sendMessage(String fromEmailAddress, String toEmailAddress, String subject, String bodyText) throws MessagingException, IOException, GeneralSecurityException;
}
