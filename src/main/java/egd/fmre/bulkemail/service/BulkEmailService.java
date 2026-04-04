package egd.fmre.bulkemail.service;

import egd.fmre.bulkemail.service.exception.BulkemailServiceException;

public interface BulkEmailService {
    void sendBulkEmail() throws BulkemailServiceException, InterruptedException;
}
