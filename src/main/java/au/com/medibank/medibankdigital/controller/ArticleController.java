package au.com.medibank.medibankdigital.controller;

import au.com.medibank.medibankdigital.model.Article;
import au.com.medibank.medibankdigital.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article")
    public Article getArticle() throws JsonProcessingException {
        return articleService.getArticle();
    }

}
