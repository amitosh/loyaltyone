package com.loyaltyone.assignment.service;

import com.loyaltyone.assignment.entity.InputText;
import com.loyaltyone.assignment.entity.User;
import com.loyaltyone.assignment.repository.InputTextRepository;
import com.loyaltyone.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDataService {
    @Autowired
    private InputTextRepository inputTextRepository;
    @Autowired
    private UserRepository userRepository;

    public List<InputText> saveInputText(String text, String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
            user.setUserName(userName);
            user = userRepository.save(user);
        }

        InputText inputText = new InputText();
        inputText.setText(text);
        inputText.setUser(user);
        inputTextRepository.save(inputText);

        return getInputTextsByUser(userName);
    }

    public Map<String, List<String>> getAllInputTexts() {
        Map<String, List<String>> userInputTextsMap = new HashMap<>();
        for (InputText inputText : inputTextRepository.findAll()) {
            String userName = inputText.getUser().getUserName();
            if (!userInputTextsMap.containsKey(userName))
                userInputTextsMap.put(userName, new ArrayList<>());

            List<String> inputTexts = userInputTextsMap.get(userName);
            inputTexts.add(inputText.getText());
            userInputTextsMap.put(userName, inputTexts);
        }

        return userInputTextsMap;
    }

    public List<InputText> getInputTextsByUser(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        return userOptional.isPresent() ? userOptional.get().getInputTexts() : Collections.EMPTY_LIST;
    }
}
