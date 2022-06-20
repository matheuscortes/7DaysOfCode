package movie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ImdbApiClient implements ApiClient {

    private String apiKey;

    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getResponseBody() {
        HttpResponse<String> httpResponse = null;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

        } catch (IOException ex) {
            System.out.println("HTTP request failed");
        } catch (InterruptedException ex) {
            System.out.println("This thread was interrupted");
        }

        return httpResponse.body();
    }
}
