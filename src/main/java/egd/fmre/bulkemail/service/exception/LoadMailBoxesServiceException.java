package egd.fmre.bulkemail.service.exception;

public class LoadMailBoxesServiceException extends BulkemailServiceException {
    public LoadMailBoxesServiceException(FileActionsException e) {
        super(e);
    }

    public LoadMailBoxesServiceException(String s) {
        super(s);
    }
}
