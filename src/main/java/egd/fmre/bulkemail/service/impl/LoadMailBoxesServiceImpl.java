package egd.fmre.bulkemail.service.impl;

import egd.fmre.bulkemail.dto.Mailbox;
import egd.fmre.bulkemail.service.LoadMailBoxesService;
import egd.fmre.bulkemail.service.exception.FileActionsException;
import egd.fmre.bulkemail.service.exception.LoadMailBoxesServiceException;
import egd.fmre.bulkemail.service.util.FileActions;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LoadMailBoxesServiceImpl extends FileActions implements LoadMailBoxesService {
    private static final String MAILBOX_STRUCTURE = "^.+\\|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    @Override
    public List<Mailbox> loadMailboxes(String pathString) throws LoadMailBoxesServiceException {
        try {
            validateFile(pathString);
        } catch (FileActionsException e) {
            throw new LoadMailBoxesServiceException(e);
        }
        List<String> lines;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathString))){
            lines = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new LoadMailBoxesServiceException(e.getMessage());
        }
        Pattern p = Pattern.compile(MAILBOX_STRUCTURE);
        Predicate<String> lineIsAMailboxPredicate = line -> {
            Matcher m = p.matcher(line);
            return m.matches();
        };

        if(!lines.stream().anyMatch(lineIsAMailboxPredicate)){
            throw new LoadMailBoxesServiceException("File does not have right structure");
        }

        return lines.stream().map(s -> {
            String[] arr = s.split("\\|");
            return new Mailbox(arr[0], arr[1]);
        }).toList();
    }
}
