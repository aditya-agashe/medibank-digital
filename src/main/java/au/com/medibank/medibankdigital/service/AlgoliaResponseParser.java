package au.com.medibank.medibankdigital.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class AlgoliaResponseParser {

    private int randomNumber = new Random().nextInt(10);

    private JsonNode getRandomHit(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(body);
        return root.path("hits").get(randomNumber);
    }

    String getTitle(String body) throws JsonProcessingException {
        return getRandomHit(body).get("title").asText();
    }

    String getAuthor(String body) throws JsonProcessingException {
        return getRandomHit(body).get("author").asText();
    }

    String getURL(String body) throws JsonProcessingException {
        return getRandomHit(body).get("url").asText();
    }

    // Only for testing!
    void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }
}
