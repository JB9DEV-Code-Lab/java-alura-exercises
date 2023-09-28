package challenges.fakeMusicApp.models;

public class Music extends Audio {
    // region fields
    private String album;
    private String artist;
    private String gender;
    private static final int MINIMUM_LIKES_TO_MAKE_IT = 200;
    private static final int MAX_SORTING_GRADE = 10;
    private static final int STEP_TO_SORTING =  MINIMUM_LIKES_TO_MAKE_IT / MAX_SORTING_GRADE;
    // endregion fields

    // region getters and setters

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    // endregion getters and setters

    // region overridings
    @Override
    public int getSorting() {
        int likes = getLikes();
        if (likes >= MINIMUM_LIKES_TO_MAKE_IT)  {
            return MAX_SORTING_GRADE;
        }

        return likes / STEP_TO_SORTING;
    }
    // endregion overridings
}
