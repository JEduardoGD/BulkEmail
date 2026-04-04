package egd.fmre.bulkemail.service.exception;

public class BulkemailServiceException extends Exception {
    public BulkemailServiceException(String s) {
        super(s);
    }

    public BulkemailServiceException(FileActionsException e) {
        super(e);
    }

    public BulkemailServiceException(BulkemailServiceException e) {
        super(e);
    }

    public BulkemailServiceException(Exception e) {
        super(e);
    }
}
