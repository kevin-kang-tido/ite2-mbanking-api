package co.istad.ite2mbanking.feature.mail;

import co.istad.ite2mbanking.feature.mail.dto.MailRequest;
import co.istad.ite2mbanking.feature.mail.dto.MailResponse;

public interface MailService {
    MailResponse send(MailRequest mailRequest);
}
