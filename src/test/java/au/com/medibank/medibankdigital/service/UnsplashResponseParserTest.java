package au.com.medibank.medibankdigital.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnsplashResponseParserTest {

    private UnsplashResponseParser unsplashResponseParser;

    @Before
    public void setup() {
        unsplashResponseParser = new UnsplashResponseParser();
    }

    @Test
    public void shouldReturnCorrectImageURL() throws URISyntaxException, IOException {
        Assert.assertEquals("https://images.unsplash.com/photo-1612203776012-b91651988ade?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwyMDc2Mzh8MHwxfHJhbmRvbXx8fHx8fHx8&ixlib=rb-1.2.1&q=80&w=200",
                unsplashResponseParser.getImageURL(readFile("ValidUnsplashResponse.txt")));
    }

    @Test
    public void shouldThrowIllegalArgumentException_IfResponseIsNull() throws URISyntaxException, IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {unsplashResponseParser.getImageURL(null);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "{}"})
    public void shouldThrowNullPointerException_IfResponseIsEmpty(String body) throws URISyntaxException, IOException {
        Assertions.assertThrows(NullPointerException.class, () -> {unsplashResponseParser.getImageURL(body);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidUnsplashResponse_ThumbNode_Absent.txt", "InvalidUnsplashResponse_URLSNode_Absent.txt"})
    public void shouldThrowNullPointerException_IfResponseIsUnparsable(String fileName) throws URISyntaxException, IOException {
        Assertions.assertThrows(NullPointerException.class, () -> {unsplashResponseParser.getImageURL(readFile(fileName));});
    }

    private String readFile(String fileName) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(fileName).toURI());
        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        return data;
    }

}