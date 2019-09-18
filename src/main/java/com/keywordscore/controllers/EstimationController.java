package com.keywordscore.controllers;

import com.keywordscore.models.Response;
import com.keywordscore.services.EstimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/estimate")

/**
 * The rest controller for the task
 */
public class EstimationController {

    @Autowired
    private EstimationService estimationService;

    /**
     * All requests coming to /estimate go to findScore
     */
    @GetMapping("")
    public Response findScore(@RequestParam("keyword") String keyword){
        try {
            return estimationService.findScore(keyword);
        } catch (IOException e) {
            return(new Response(keyword, 0));
        }
    }

}
