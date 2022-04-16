import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String apiKey = "k_o1ih6e18";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
                .timeout(Duration.ofSeconds(2))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .followRedirects(HttpClient.Redirect.NORMAL) //Does not redirect https to http
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());

        String json = httpResponse.body();

        String[] moviesArray = parseJsonMovies(json);

        List<String> titles = parseTitles(moviesArray);
        titles.forEach(title -> System.out.println(title));

        List<String> imagesUrl = parseImagesUrl(moviesArray); 
        imagesUrl.forEach(url -> System.out.println(url));
    }

    private static String[] parseJsonMovies(String json) {
        Pattern pattern = Pattern.compile("\\{\"items\":\\[\\{(.*)\\}\\].*\\}");
        Matcher matcher = pattern.matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("No match in " + json);
        }

        return matcher.group(1).split("\\},\\{");
    }

    private static List<String> parseTitles(String[] moviesArray) {
        return parseAttributes(moviesArray, 2);
    }

    private static List<String> parseImagesUrl(String[] moviesArray) {
        return parseAttributes(moviesArray, 5);
    }

    private static List<String> parseAttributes(String[] moviesArray, int position) {
        return Stream.of(moviesArray)
                .map(element -> element.split("\",\"")[position])
                .map(element -> element.split("\":\"")[1])
                .collect(Collectors.toList());
    }
}
