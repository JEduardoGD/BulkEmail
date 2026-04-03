package egd.fmre.bulkemail.service.exception;

import java.io.IOException;

public class BulkemailServiceException extends Exception {
public BulkemailServiceException(String s) {
    super(s);
}

    public BulkemailServiceException(LoadBodyEmailFileException e) {
    super(e);
    }

    public BulkemailServiceException(FileActionsException e) {
    }

}
