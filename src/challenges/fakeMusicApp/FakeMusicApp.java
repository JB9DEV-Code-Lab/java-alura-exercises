package challenges.fakeMusicApp;

import challenges.fakeMusicApp.models.Favorite;
import challenges.fakeMusicApp.models.Music;
import challenges.fakeMusicApp.models.Podcast;
import org.ietf.jgss.GSSCredential;
import utils.RunnableMenuOption;

public class FakeMusicApp extends RunnableMenuOption {
    public FakeMusicApp() {
        super("Fake Music App");
    }

    // region overridings
    public void run() {
        Music scrivimi = new Music();
        scrivimi.setAlbum("Equilíbrio Distante");
        scrivimi.setArtist("Renato Russo");
        scrivimi.setTitle("Scrivimi");
        for (int i = 0; i < 1000; i++) {
            scrivimi.play();
        }

        for (int i = 0; i < 300; i++) {
            scrivimi.like();
        }

        Podcast scicast = new Podcast();
        scicast.setHost("Fencas");
        scicast.setTitle("What if a zumbi Apocalypse really happened?");
        scicast.setDescription(
            "In this episode we'll have 30 minutes to imagine how it could be if a zumbi Apocalypse really happened."
        );
        for (int i = 0; i < 100; i++) {
            scicast.play();
        }

        for (int i = 0; i < 50; i++) {
            scicast.like();
        }

        Favorite favoriteAudios = new Favorite();
        favoriteAudios.add(scrivimi);
        favoriteAudios.add(scicast);
    }
    // endregion overridings
}
