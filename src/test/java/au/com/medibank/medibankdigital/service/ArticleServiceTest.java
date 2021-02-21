package au.com.medibank.medibankdigital.service;

import au.com.medibank.medibankdigital.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ArticleServiceTest {

    private ArticleService articleService;

    @Mock
    private RestTemplateHelper restTemplateHelper;

    @Mock
    private AlgoliaResponseParser algoliaResponseParser;

    @Mock
    private UnsplashResponseParser unsplashResponseParser;

    @BeforeEach
    public void setup () throws JsonProcessingException {
        Mockito.when(restTemplateHelper.fetchAlgoliaResponse()).thenReturn("");
        Mockito.when(algoliaResponseParser.getTitle(Mockito.anyString())).thenReturn("Title");
        Mockito.when(algoliaResponseParser.getAuthor(Mockito.anyString())).thenReturn("Author");
        Mockito.when(algoliaResponseParser.getURL(Mockito.anyString())).thenReturn("URL");

        Mockito.when(restTemplateHelper.fetchUnsplashResponse()).thenReturn("");
        Mockito.when(unsplashResponseParser.getImageURL(Mockito.anyString())).thenReturn("ImageURL");

        articleService = new ArticleService(restTemplateHelper, algoliaResponseParser, unsplashResponseParser);
    }

    @Test
    public void shouldReturnCorrectTitle() throws JsonProcessingException {
        Assert.assertEquals("Title", articleService.getArticle().getTitle());
    }

    @Test
    public void shouldReturnCorrectAuthor() throws JsonProcessingException {
        Assert.assertEquals("Author", articleService.getArticle().getAuthor());
    }

    @Test
    public void shouldReturnCorrectURL() throws JsonProcessingException {
        Assert.assertEquals("URL", articleService.getArticle().getUrl());
    }

    @Test
    public void shouldReturnCorrectImageURL() throws JsonProcessingException {
        Assert.assertEquals("ImageURL", articleService.getArticle().getImageUrl());
    }

    @Test
    public void shouldReturnArticleWithinLastHour() throws JsonProcessingException {
        final Article article = new Article();
        articleService.setArticle(article);
        Assert.assertEquals(article, articleService.getArticle());
    }

}