import movie.HTMLGenerator;
import movie.MovieConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String apiKey = "k_o1ih6e18";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL) //Does not redirect https to http
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());

        String json = httpResponse.body();
        String[] moviesArray = MovieConverter.parseJsonMovies(json);

        HTMLGenerator html = new HTMLGenerator(new File("../html-css/index.html"),
                new File("../html-css/style.css"));
        html.generate(moviesArray);
    }
}
