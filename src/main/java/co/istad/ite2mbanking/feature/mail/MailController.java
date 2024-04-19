package co.istad.ite2mbanking.feature.mail;

import co.istad.ite2mbanking.feature.mail.dto.MailRequest;
import co.istad.ite2mbanking.feature.mail.dto.MailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    @PostMapping
    MailResponse sendMail(@Valid @RequestBody MailRequest mailRequest) {
        return mailService.send(mailRequest);

    }
}
