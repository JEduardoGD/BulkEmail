package egd.fmre.bulkemail.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Properties;

@Data
public class Maildata implements Serializable {
    private Properties prop;
    private String username;
    private String password;
    private Mailbox from;
    private Mailbox to;
    private Mailbox bcc;
    private Mailbox replyTo;
    private String subject;
    private String body;
}
