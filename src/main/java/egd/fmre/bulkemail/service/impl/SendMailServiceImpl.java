package egd.fmre.bulkemail.service.impl;

import egd.fmre.bulkemail.dto.Maildata;
import egd.fmre.bulkemail.service.SendMailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Override
    public void sendMail(Maildata maildata) {
        Session session = Session.getInstance(maildata.getProp(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(maildata.getUsername(), maildata.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(maildata.getFrom().address(), maildata.getFrom().name()));
            message.setReplyTo(
                    new InternetAddress[] {
                            new InternetAddress(maildata.getReplyTo().address(), maildata.getReplyTo().name())
                    });
            message.setRecipients(
                    Message.RecipientType.TO, new InternetAddress[]{ new InternetAddress(maildata.getTo().address(), maildata.getTo().name())});
            message.setRecipients(
                    Message.RecipientType.BCC, new InternetAddress[]{ new InternetAddress(maildata.getBcc().address(), maildata.getBcc().name())
                    });
            message.setSubject(maildata.getSubject());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(maildata.getBody(), "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
