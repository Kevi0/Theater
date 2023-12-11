package bonfiglio.scozzari.ing_soft.theatersoftware.service.mail;

import freemarker.template.*;
import jakarta.annotation.Nullable;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    public void sendRegistrationEmail(
            String toAddress,
            String subject,
            @Nullable String cc,
            @Nullable String bcc,
            String templateName,
            Map<String, Object> model){

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(toAddress);
            if (cc != null) {
                mimeMessageHelper.setCc(cc);
            }
            if (bcc != null){
                mimeMessageHelper.setBcc(bcc);
            }
            mimeMessageHelper.setSubject(subject);

            Template template = configuration.getTemplate(templateName + ".ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setText(content, true);

            ClassPathResource logo = new ClassPathResource("static/images/logo.png");
            mimeMessageHelper.addInline("logo", logo);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
