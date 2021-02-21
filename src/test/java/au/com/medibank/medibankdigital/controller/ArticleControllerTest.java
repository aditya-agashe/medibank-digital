package au.com.medibank.medibankdigital.controller;

import au.com.medibank.medibankdigital.model.Article;
import au.com.medibank.medibankdigital.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ArticleController.class)
public class ArticleControllerTest {

    @MockBean
    private ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOKResponseIfArticleRetrievedSuccessfully() throws Exception {
        Mockito.when(articleService.getArticle()).thenReturn(new Article());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/article"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnINTERNAL_SERVER_ERRORResponseIfJsonProcessingExceptionOccurred() throws Exception {
        Mockito.when(articleService.getArticle()).thenThrow(JsonProcessingException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/article"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    public void shouldReturnINTERNAL_SERVER_ERRORResponseIfRuntimeExceptionOccurred() throws Exception {
        Mockito.when(articleService.getArticle()).thenThrow(RuntimeException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/article"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}