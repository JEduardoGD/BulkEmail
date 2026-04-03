package egd.fmre.bulkemail.service.impl;

import egd.fmre.bulkemail.service.LoadEmailPropertiesService;
import egd.fmre.bulkemail.service.exception.FileActionsException;
import egd.fmre.bulkemail.service.exception.LoadEmailPropertiesServiceException;
import egd.fmre.bulkemail.service.exception.LoadMailBoxesServiceException;
import egd.fmre.bulkemail.service.util.FileActions;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class LoadEmailPropertiesServiceImpl extends FileActions implements LoadEmailPropertiesService {
    @Override
    public Properties loadEmailProperties(String pathString) throws LoadEmailPropertiesServiceException {
        try {
            validateFile(pathString);
        } catch (FileActionsException e) {
            throw new LoadEmailPropertiesServiceException(e);
        }
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(pathString)) {
            prop.load(fis);
            return prop;
        } catch (IOException e) {
            throw new LoadEmailPropertiesServiceException(e);
        }
    }
}
