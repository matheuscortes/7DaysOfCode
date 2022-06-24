import movie.*;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String apiKey = "k_o1ih6e18";
        ImdbApiClient apiClient = new ImdbApiClient(apiKey);

        String json = apiClient.getResponseBody();
        List<? extends Content> movies = new ImdbJsonParser(json).parseResponseBody();

        movies.sort(Comparator.comparing(Content::getYear));

        HtmlGenerator html = new HtmlGenerator(new File("../html-css/index.html"),
                new File("../html-css/style.css"));
        html.generate(movies);
    }
}
