package br.com.loudness.msemail.services;

import br.com.loudness.msemail.domain.entities.Email;
import br.com.loudness.msemail.domain.enums.Status;
import br.com.loudness.msemail.domain.repositories.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired // sent
    private JavaMailSender emailSender;


    public Email sendEmail(Email emailModel) throws MessagingException {

        // Save and sent email
        // Current date
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {

            MimeMessage msg = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(), true);

            emailSender.send(msg);
            // STATUS
            emailModel.setStatusEmail(Status.SENT);

        } catch (MailException e) {
            emailModel.setStatusEmail(Status.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }

    }
}
