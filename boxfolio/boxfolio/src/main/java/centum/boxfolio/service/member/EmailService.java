package centum.boxfolio.service.member;

public interface EmailService {
    boolean sendEmail(String email, String title, String text, boolean useHtml);
    boolean sendTokenByEmail(String email, String tokenId);
}
