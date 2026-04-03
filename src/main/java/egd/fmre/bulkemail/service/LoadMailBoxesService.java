package egd.fmre.bulkemail.service;

import egd.fmre.bulkemail.dto.Mailbox;
import egd.fmre.bulkemail.service.exception.LoadMailBoxesServiceException;

import java.util.List;

public interface LoadMailBoxesService {
    List<Mailbox> loadMailboxes(String pathString) throws LoadMailBoxesServiceException;
}
