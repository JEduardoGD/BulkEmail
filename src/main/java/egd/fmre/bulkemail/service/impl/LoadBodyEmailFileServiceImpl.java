package egd.fmre.bulkemail.service.impl;

import egd.fmre.bulkemail.service.LoadBodyEmailFileService;
import egd.fmre.bulkemail.service.exception.BulkemailServiceException;
import egd.fmre.bulkemail.service.exception.LoadBodyEmailFileException;
import egd.fmre.bulkemail.service.util.FileActions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LoadBodyEmailFileServiceImpl extends FileActions implements LoadBodyEmailFileService {
    @Override
    public String loadBodyEmailFile(String pathString) throws BulkemailServiceException {
        validateFile(pathString);
        try {
            return Files.readString(Path.of(pathString));
        } catch (IOException e) {
            throw new BulkemailServiceException(e.getMessage());
        }
    }
}
