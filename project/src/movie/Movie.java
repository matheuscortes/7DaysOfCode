package movie;

public class Movie {

    //Attributes

    private String title;
    private String imageUrl;
    private String rating;
    private String year;


    //All parameters constructor

    public Movie(String title, String imageUrl, String rating, String year) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String toString() {
        return "title: " + title
                + ", image-url: " + imageUrl
                + ", rating: " + rating
                + ", year: " + year;
    }

    //Getters and setters

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }
}
