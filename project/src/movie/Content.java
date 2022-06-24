package movie;

public interface Content extends Comparable<Content> {

    String getTitle();
    String getImageUrl();
    String getRating();
    String getYear();
}
