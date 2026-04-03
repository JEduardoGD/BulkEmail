package egd.fmre.bulkemail.service;

import egd.fmre.bulkemail.service.exception.LoadEmailPropertiesServiceException;

import java.util.Properties;

public interface LoadEmailPropertiesService {
    Properties loadEmailProperties(String pathString) throws LoadEmailPropertiesServiceException;
}
