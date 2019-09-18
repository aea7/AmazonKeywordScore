package com.keywordscore.services;

import com.keywordscore.models.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * The rest service for the task, specified for estimating the keyword score
 */
@RequiredArgsConstructor
@Service
public class EstimationService {

    public Response findScore(String keyword) throws IOException {
        String uri = "https://completion.amazon.com/api/2017/suggestions?mid=ATVPDKIKX0DER&alias=aps&event=onKeyPress&" +
                "limit=11&suggestion-type=KEYWORD&prefix=";
        double finalScore = 0;
        char[] keywordLetters = keyword.toCharArray();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        double totalAttainableScore = 0;

        for (int i = 0; i < keywordLetters.length; i++) {
            uri += keywordLetters[i];
            int score = keywordLetters.length / (i + 1);
            totalAttainableScore += score * 10;
            String result = restTemplate.getForObject(uri, String.class);
            JsonNode root = mapper.readTree(result);
            ArrayNode suggestions = (ArrayNode) root.path("suggestions");
            for (int y = 0; y < suggestions.size(); y++) {
                String suggestionKeyword = suggestions.get(y).get("value").textValue();
                if (suggestionKeyword.contains(keyword)) {
                    finalScore += score;
                }
            }
        }
        double finalScoreDouble = 100 * (finalScore / totalAttainableScore);
        return new Response(keyword, (int) Math.floor(finalScoreDouble));
    }
}
