package centum.boxfolio.service.member;

import centum.boxfolio.service.MailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String email, String title, String text, boolean useHtml) {
        try {
            MailHandler mailHandler = new MailHandler(javaMailSender);

            mailHandler.setTo(email);
            mailHandler.setFrom(EmailConst.FROM_ADDRESS);
            mailHandler.setSubject(title);
            mailHandler.setText(text, useHtml);
            mailHandler.send();

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean sendTokenByEmail(String email, String tokenId) {
        try {
            MailHandler mailHandler = new MailHandler(javaMailSender);

            mailHandler.setTo(email);
            mailHandler.setFrom(EmailConst.FROM_ADDRESS);
            mailHandler.setSubject(EmailConst.EMAIL_TITLE);
            mailHandler.setText("<a href='http://localhost:8080/signup/result?token=" + tokenId + "'>회원가입 완료하기</a>", true);
            mailHandler.send();

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }
}
