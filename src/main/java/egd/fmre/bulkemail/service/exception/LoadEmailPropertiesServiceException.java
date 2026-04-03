package egd.fmre.bulkemail.service.exception;

import java.io.IOException;

public class LoadEmailPropertiesServiceException extends BulkemailServiceException {
    public LoadEmailPropertiesServiceException(FileActionsException e) {
        super(e.getMessage());
    }

    public LoadEmailPropertiesServiceException(IOException e) {
        super(e.getMessage());
    }
}
