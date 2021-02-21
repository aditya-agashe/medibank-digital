package au.com.medibank.medibankdigital.service;

import au.com.medibank.medibankdigital.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ArticleService {

    private final LocalDateTime localDateTime = LocalDateTime.now();

    private Article article;

    private final RestTemplateHelper restTemplateHelper;

    private final AlgoliaResponseParser algoliaResponseParser;

    private final UnsplashResponseParser unsplashResponseParser;

    @Autowired
    public ArticleService(RestTemplateHelper restTemplateHelper, AlgoliaResponseParser algoliaResponseParser, UnsplashResponseParser unsplashResponseParser) {
        this.restTemplateHelper = restTemplateHelper;
        this.algoliaResponseParser = algoliaResponseParser;
        this.unsplashResponseParser = unsplashResponseParser;
    }

    public Article getArticle() throws JsonProcessingException {
        if(article == null || anHourHasPassed()) {
            article = new Article();
            String response = restTemplateHelper.fetchAlgoliaResponse();
            article.setTitle(algoliaResponseParser.getTitle(response));
            article.setAuthor(algoliaResponseParser.getAuthor(response));
            article.setUrl(algoliaResponseParser.getURL(response));

            response = restTemplateHelper.fetchUnsplashResponse();
            article.setImageUrl(unsplashResponseParser.getImageURL(response));
        }
        return article;
    }

    private boolean anHourHasPassed() {
        return Duration.between(localDateTime, LocalDateTime.now()).toHours() > 0;
    }

    // For unit testing only!
    void setArticle(Article article)  {
        this.article = article;
    }
}
