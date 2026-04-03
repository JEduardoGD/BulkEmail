package egd.fmre.bulkemail.service;

import egd.fmre.bulkemail.service.exception.BulkemailServiceException;

public interface LoadBodyEmailFileService {
    String loadBodyEmailFile(String pathString) throws BulkemailServiceException;
}
