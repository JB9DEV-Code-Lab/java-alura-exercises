package challenges.fakeMusicApp.models;


public class Audio {
    // region fields
    private String title;
    private int durationInMinutes = 0;
    private int totalPlayed = 0;
    private int likes = 0;
    private int sorting;
    // endregion fields

    // region getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getTotalPlayed() {
        return totalPlayed;
    }

    public int getLikes() {
        return likes;
    }

    public int getSorting() {
        return sorting;
    }

    // endregion getters and setters

    // region implementations
    public void like() {
        likes++;
    }

    public void play() {
        totalPlayed++;
    }
    // endregion implementations
}
