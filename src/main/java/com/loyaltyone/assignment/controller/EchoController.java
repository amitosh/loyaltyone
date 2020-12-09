package com.loyaltyone.assignment.controller;

import com.loyaltyone.assignment.entity.InputText;
import com.loyaltyone.assignment.service.UserDataService;
import com.loyaltyone.assignment.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EchoController {

    @Autowired
    private UserDataService userDataService;
    @Autowired
    private RestService restService;

    @GetMapping("/echotext")
    public String echo(@RequestParam(name = "text") String text) {
        return text;
    }

    @PostMapping("/save")
    public String save(@RequestParam(name = "userName") String userName,
                       @RequestParam(name = "text") String text) {
        List<InputText> texts = userDataService.saveInputText(text, userName);
        StringBuilder sb = new StringBuilder("<ul>");
        for (InputText inputText : texts)
            sb.append("<li>").append(inputText.getText()).append("</li>");
        sb.append("</ul>");

        return sb.toString().replaceAll("\n", "<br/>");
    }

    @GetMapping("/latlong")
    public String getLatLong(@RequestParam(name = "city") String city) {
        StringBuilder sb = new StringBuilder();
        String[] latLongStr = restService.getLatLongForCity(city);
        sb.append("Latitude: ").append(latLongStr[0]).append("<br/>");
        sb.append("Longitude: ").append(latLongStr[1]).append("<br/>");
        sb.append("Temperature: ").append(latLongStr[2]).append("<br/>");

        return sb.toString();
    }

    @GetMapping("/userposts")
    public String getUserPosts(@RequestParam(name = "userName") String userName) {
        StringBuilder sb = new StringBuilder("<ul>");
        for (InputText inputText : userDataService.getInputTextsByUser(userName))
            sb.append("<li>").append(inputText.getText()).append("</li>");
        sb.append("</ul>");

        return sb.toString().replaceAll("\n", "<br/>");
    }

    @GetMapping("/posts")
    public String getAllPosts() {
        Map<String, List<String>> userInputTextsMap = userDataService.getAllInputTexts();
        StringBuilder sb = new StringBuilder("<ul>");
        for (String userName : userDataService.getAllInputTexts().keySet()) {
            sb.append("<ul>");
            sb.append("<li>").append(userName).append("</li>");
            sb.append("<ul>");
            for (String inputText : userInputTextsMap.get(userName))
                sb.append("<li>").append(inputText).append("</li>");
            sb.append("</ul>");
            sb.append("</ul>");
        }
        sb.append("</ul>");

        return sb.toString().replaceAll("\n", "<br/>");
    }
}
