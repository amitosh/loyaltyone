package com.loyaltyone.assignment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Service
public class RestService {
    public String[] getLatLongForCity(String city) {
        String[] str = new String[3];

        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=3c3273ac9d68b8a70cb8893dfe591bd9";
            ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            if(response != null)
            {
                JsonNode root = mapper.readTree(response.getBody());
                String latitude = root.path("coord").path("lat").asText();
                String longitude = root.path("coord").path("lon").asText();
                String temperature = (new DecimalFormat("#.##").format(root.path("main").path("temp").asDouble() - 273.15)) + " °C";
                str[0] = latitude;
                str[1] = longitude;
                str[2] = temperature;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
