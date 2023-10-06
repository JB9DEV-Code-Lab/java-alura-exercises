package challenges.addressSearcher.services;

import challenges.addressSearcher.dtos.AddressDTO;
import challenges.addressSearcher.models.Address;
import challenges.addressSearcher.repository.SearchedAddressRepository;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddressHttpService {
    public static void searchZipcode(String zipcode) {
        try {
            String addressZipcodeFoundTemplate = "%nThe address for this zip code is %s";
            String endpoint = String.format(
                    "https://viacep.com.br/ws/%s/json/",
                    zipcode.replaceAll("\\D", "")
            );

            SearchedAddressRepository searchedAddressRepository = new SearchedAddressRepository();
            Address addressBYZipcode = searchedAddressRepository.getSearchedByZipcode(zipcode);

            if (addressBYZipcode == null) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() >= 200 && response.statusCode() < 300) {
                    Gson gson = new Gson();
                    AddressDTO addressDTO = gson.fromJson(response.body(), AddressDTO.class);
                    Address address = new Address(addressDTO);
                    System.out.printf(addressZipcodeFoundTemplate, address);

                    searchedAddressRepository.saveAddress(address);
                } else {
                    System.out.printf("%nCouldn't find any address for %s", zipcode);
                }
            } else {
                System.out.printf(addressZipcodeFoundTemplate, addressBYZipcode);
            }

        } catch (IOException | InterruptedException exception) {
            System.out.printf("%nHad a problem to search for %s. Error message: %s", zipcode, exception.getMessage());
        }
    }
}
