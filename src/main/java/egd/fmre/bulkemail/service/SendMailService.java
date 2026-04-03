package egd.fmre.bulkemail.service;

import egd.fmre.bulkemail.dto.Maildata;

public interface SendMailService {
    void sendMail(Maildata maildata);
}
