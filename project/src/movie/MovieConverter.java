package movie;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieConverter {

    public static String[] parseJsonMovies(String json) {
        Pattern pattern = Pattern.compile("\\{\"items\":\\[\\{(.*)\\}\\].*\\}");
        Matcher matcher = pattern.matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("No match in " + json);
        }

        return matcher.group(1).split("\\},\\{");
    }

    public static List<String> parseTitles(String[] moviesArray) {
        return parseAttributes(moviesArray, 2);
    }

    public static List<String> parseImagesUrl(String[] moviesArray) {
        return parseAttributes(moviesArray, 5);
    }

    public static List<String> parseRatings(String[] moviesArray) {
        return parseAttributes(moviesArray, 7);
    }

    public static List<String> parseYears(String[] moviesArray) {
        return parseAttributes(moviesArray, 4);
    }

    public static List<String> parseAttributes(String[] moviesArray, int position) {
        return Stream.of(moviesArray)
                .map(element -> element.split("\",\"")[position])
                .map(element -> element.split("\":\"")[1])
                .collect(Collectors.toList());
    }

    public static List<Movie> getMovies(String[] moviesArray) {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < moviesArray.length; i++) {
            Movie movie = new Movie(parseTitles(moviesArray).get(i),
                    parseImagesUrl(moviesArray).get(i),
                    parseRatings(moviesArray).get(i),
                    parseYears(moviesArray).get(i));
            movies.add(movie);
        }

        return movies;
    }
}
