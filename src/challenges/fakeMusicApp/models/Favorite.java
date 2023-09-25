package challenges.fakeMusicApp.models;

public class Favorite {
    public void add(Audio audio) {
        int sorting = audio.getSorting();
        String audioTitle = audio.getTitle();

        // TODO: Improve it to have more messages based in a grade from 0 to 10
        if (sorting >= 9) {
            System.out.printf("%s is outstanding! It's the greatest success of the moment!%n", audioTitle);
        } else {
            System.out.printf("%s is pretty good, you should listen it!%n", audioTitle);
        }
    }
}
