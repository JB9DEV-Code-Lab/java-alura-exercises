package challenges.fakeMusicApp.models;

public class Music extends Audio {
    // region fields
    private String album;
    private String artist;
    private String gender;
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
        // TODO: Improve it to have more ranges
        if (getLikes() >= 200)  {
            return 10;
        }

        return 8;
    }
    // endregion overridings
}
