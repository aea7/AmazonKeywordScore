package com.keywordscore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


/**
 * The Response model designed for our rest api JSON response
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Response {

    private String keyword;

    private int score;

    public Response(String keyword, int score) {
        this.keyword = keyword;
        this.score = score;
    }
}
