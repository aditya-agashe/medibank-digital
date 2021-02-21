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

public class AlgoliaResponseParserTest {

    private AlgoliaResponseParser algoliaResponseParser;

    @Before
    public void setup() {
        algoliaResponseParser = new AlgoliaResponseParser();
        algoliaResponseParser.setRandomNumber(0);
    }

    @Test
    public void shouldReturnCorrectTitle() throws URISyntaxException, IOException {
        Assert.assertEquals("American Academy of Sleep Medicine calls for elimination of daylight saving time",
                algoliaResponseParser.getTitle(readFile("ValidAlgoliaResponse.txt")));
    }

    @Test
    public void shouldReturnCorrectURL() throws URISyntaxException, IOException {
        Assert.assertEquals("https://aasm.org/american-academy-of-sleep-medicine-calls-for-elimination-of-daylight-saving-time/",
                algoliaResponseParser.getURL(readFile("ValidAlgoliaResponse.txt")));
    }

    @Test
    public void shouldReturnCorrectAuthor() throws URISyntaxException, IOException {
        Assert.assertEquals("oftenwrong",
                algoliaResponseParser.getAuthor(readFile("ValidAlgoliaResponse.txt")));
    }

    @Test
    public void shouldThrowIllegalArgumentException_IfResponseIsNull() throws URISyntaxException, IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {algoliaResponseParser.getTitle(null);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "{}"})
    public void shouldThrowNullPointerException_IfResponseIsEmpty(String body) throws URISyntaxException, IOException {
        Assertions.assertThrows(NullPointerException.class, () -> {algoliaResponseParser.getTitle(body);});
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidAlgoliaResponse_HitsNode_Absent.txt", "InvalidAlgoliaResponse_TitleNode_Absent.txt"})
    public void shouldThrowNullPointerException_IfResponseIsUnparsable(String fileName) throws URISyntaxException, IOException {
        Assertions.assertThrows(NullPointerException.class, () -> {algoliaResponseParser.getTitle(readFile(fileName));});
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