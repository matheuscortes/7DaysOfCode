package movie;

public class Movie implements Content {

    // Attributes

    private String title;
    private String imageUrl;
    private String rating;
    private String year;


    // All parameters constructor

    public Movie(String title, String imageUrl, String rating, String year) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "title: " + year
                + ", image-url: " + imageUrl
                + ", rating: " + rating
                + ", year: " + year;
    }

    @Override
    public int compareTo(Content content) {
        return -this.rating.compareTo(content.getRating());
    }
}
