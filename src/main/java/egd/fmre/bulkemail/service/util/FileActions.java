package egd.fmre.bulkemail.service.util;

import egd.fmre.bulkemail.service.exception.FileActionsException;

import java.io.File;
import java.nio.file.Paths;

public abstract class FileActions {
    protected void validateFile(String pathString) throws FileActionsException {
        if(pathString == null){
            throw new FileActionsException("Path string is null");
        }
        File file = Paths.get(pathString).toFile();
        if(!file.exists()){
            throw new FileActionsException("File not found");
        }
        if(!file.canRead()){
            throw new FileActionsException("File not readable");
        }
    }
}
