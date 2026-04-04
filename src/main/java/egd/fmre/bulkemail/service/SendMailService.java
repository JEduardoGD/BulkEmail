package egd.fmre.bulkemail.service;

import egd.fmre.bulkemail.dto.Maildata;
import egd.fmre.bulkemail.service.exception.SendMailServiceException;

public interface SendMailService {
    void sendMail(Maildata maildata) throws SendMailServiceException;
}
