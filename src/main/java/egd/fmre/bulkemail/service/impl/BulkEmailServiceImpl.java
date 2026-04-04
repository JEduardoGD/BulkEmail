package egd.fmre.bulkemail.service.impl;

import egd.fmre.bulkemail.dto.Mailbox;
import egd.fmre.bulkemail.dto.Maildata;
import egd.fmre.bulkemail.service.BulkEmailService;
import egd.fmre.bulkemail.service.LoadBodyEmailFileService;
import egd.fmre.bulkemail.service.LoadMailBoxesService;
import egd.fmre.bulkemail.service.SendMailService;
import egd.fmre.bulkemail.service.LoadEmailPropertiesService;
import egd.fmre.bulkemail.service.exception.BulkemailServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class BulkEmailServiceImpl implements BulkEmailService {

    private final LoadBodyEmailFileService loadBodyEmailFileService;
    private final LoadMailBoxesService loadMailBoxesService;
    private final SendMailService sendMailService;
    private final LoadEmailPropertiesService loadEmailPropertiesService;

    @Value("${email.file.location}")
    private String emailFileLocation;

    @Value("${mailboxes.file.location}")
    private String mailboxesFileLocation;

    @Value("${email.properties.file.location}")
    private String emailPropertiesFileLocation;

    @Value("${email.properties.smtp.file.location}")
    private String emailPropertiesSmtpFileLocation;

    @Override
    public void sendBulkEmail() throws BulkemailServiceException, InterruptedException {
        String htmlEmail;
        List<Mailbox> mailboxes;
        Properties emailProperties;
        Properties smtpProperties;
        try {
            htmlEmail = loadBodyEmailFileService.loadBodyEmailFile(emailFileLocation);
            mailboxes = loadMailBoxesService.loadMailboxes(mailboxesFileLocation);
            emailProperties = loadEmailPropertiesService.loadEmailProperties(emailPropertiesFileLocation);
            smtpProperties = loadEmailPropertiesService.loadEmailProperties(emailPropertiesSmtpFileLocation);
        } catch (BulkemailServiceException e) {
            throw new BulkemailServiceException(e);
        }
        boolean isFirstMailbox = true;

        Maildata maildata = new Maildata();
        maildata.setProp(smtpProperties);
        maildata.setUsername(emailProperties.getProperty("email.username"));
        maildata.setPassword(emailProperties.getProperty("email.password"));
        maildata.setFrom(new Mailbox(emailProperties.getProperty("email.from.name"), emailProperties.getProperty("email.from.address")));
        maildata.setTo(null);
        maildata.setReplyTo(new Mailbox(emailProperties.getProperty("email.replyto.name"), emailProperties.getProperty("email.replyto.address")));
        maildata.setBcc(new Mailbox(emailProperties.getProperty("email.bcc.name"), emailProperties.getProperty("email.bcc.address")));
        maildata.setSubject(emailProperties.getProperty("email.subject"));
        maildata.setBody(null);

        int i = 1;

        for(Mailbox mailbox : mailboxes){
            log.info("Sending email to {} ({} of {})", mailbox.name(), i++, mailboxes.size());
            String content = htmlEmail.replace("[[NOMBRE]]", mailbox.name());
            maildata.setBody(content);
            maildata.setTo(new Mailbox(mailbox.name(), mailbox.address()));
            sendMailService.sendMail(maildata);
            if(isFirstMailbox){
                log.info("Sleeping for 60 seconds");
                Thread.sleep(60 * 1000l);
            } else {
                log.info("Sleeping for " + emailProperties.getProperty("email.sleeptime") + " seconds");
                Thread.sleep(Integer.valueOf(emailProperties.getProperty("email.sleeptime")) * 1000l);
            }
            isFirstMailbox = false;
        }
    }
}
