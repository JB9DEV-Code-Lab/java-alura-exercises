package challenges.fakeMusicApp.models;

public class Podcast extends Audio {
    // region fields
    private String host;
    private String description;
    // endregion fields

    // region getters and setters

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // endregion getters and setters

    // region overridings
    @Override
    public int getSorting() {
        // TODO: Improve it to have more ranges
        if (getTotalPlayed() > 100) {
            return 10;
        }

        return 7;
    }
    // endregion overridings
}
