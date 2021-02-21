package au.com.medibank.medibankdigital.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
class UnsplashResponseParser {

    String getImageURL(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(body);
            return root.path("urls").get("thumb").asText();
        } catch (JsonProcessingException e) {
        }
        return "";
    }
}
