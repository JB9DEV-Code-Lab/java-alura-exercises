package challenges.addressSearcher.services;

import challenges.addressSearcher.dtos.AddressDTO;
import challenges.addressSearcher.models.Address;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddressHttpService {
    public static void searchZipcode(String zipcode) {
        try {
            String endpoint = String.format(
                    "https://viacep.com.br/ws/%s/json/",
                    zipcode.replaceAll("\\D", "")
            );

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                Gson gson = new Gson();
                AddressDTO addressDTO = gson.fromJson(response.body(), AddressDTO.class);
                Address address = new Address(addressDTO);

                System.out.printf("The address for this zip code is %s", address);
            } else {
                System.out.printf("Couldn't find any address for %s", zipcode);
            }
        } catch (IOException | InterruptedException exception) {
            System.out.printf("Had a problem to search for %s. Error message: %s", zipcode, exception.getMessage());
        }
    }
}
