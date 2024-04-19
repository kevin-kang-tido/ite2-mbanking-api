package co.istad.ite2mbanking.feature.mail;

import co.istad.ite2mbanking.feature.mail.dto.MailRequest;
import co.istad.ite2mbanking.feature.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    // use for sending mail
    private  final JavaMailSender javaMailSender;
    private  final TemplateEngine templateEngine;

    @Override
    public MailResponse send(MailRequest mailRequest) {
//        javaMailSender.send(new );
        // catch message that user want to send
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // college all item or message that user has been put on
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("name","istad");
       String htmlTemple =  templateEngine.process("mail/simple",context);
        try {
            mimeMessageHelper.setTo(mailRequest.to());
            mimeMessageHelper.setSubject(mailRequest.subject());
            mimeMessageHelper.setText(htmlTemple,true);
        }catch (MessagingException e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong. Please Check again!"
            );
        }
        javaMailSender.send(mimeMessage);
        return new MailResponse("Mail has been Sent !!!");
    }
}
