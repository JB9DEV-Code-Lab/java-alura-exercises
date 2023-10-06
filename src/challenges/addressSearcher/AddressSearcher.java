package challenges.addressSearcher;

import challenges.addressSearcher.services.AddressHttpService;
import utils.Reader;
import utils.RunnableMenuOption;


public class AddressSearcher extends RunnableMenuOption {
    private final Reader reader = new Reader();

    public AddressSearcher() {
        super("Address searcher");
    }

    @Override
    public void run() {
        System.out.println("""
                ################################################################################
                Welcome to Address Searcher!
                        
                In this application you're going to be able to search brazilian addresses using
                zip code. It's straight and simple, just type the zip code and it's going to
                answer the found address or a message if it couldn't be found. Well, that's it,
                so let's start!
                ################################################################################
                """);

        String zipcode = reader.askForInput("What's the zip code?");

        if (zipcode != null && !zipcode.isEmpty()) {
            AddressHttpService.searchZipcode(zipcode);
        } else {
            System.out.println("""
                    Exiting from application since no zip code was provided.
                    If you want to search for a new one, restart the application.
                    """);

            System.exit(0);
        }
    }
}
