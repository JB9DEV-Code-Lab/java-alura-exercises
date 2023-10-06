package challenges.addressSearcher.repository;

import challenges.addressSearcher.models.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchedAddressRepository {
    private final File FILE = new File("src/challenges/addressSearcher/searched-addresses.json");
    private static final Type ADDRESS_TYPE = new TypeToken<List<Address>>() {}.getType();

    public List<Address> getAllSearched() {
        try (JsonReader jsonReader = new JsonReader(new FileReader(FILE))) {
            return new Gson().fromJson(jsonReader, ADDRESS_TYPE);
        } catch (IOException exception) {
            System.out.printf("%nCouldn't read %s due to %s. Considering empty search history.%n%n",
                    FILE.getName(), exception.getMessage());

            return new ArrayList<>();
        }
    }

    public Address getSearchedByZipcode(String zipcode) {
        return filterAddressByZipcode(zipcode, getAllSearched());
    }

    public Address getSearchedByZipcode(String zipcode, List<Address> searchHistory) {
        return filterAddressByZipcode(zipcode, searchHistory);
    }

    public void saveAddress(Address address) {
        List<Address> addressList = getAllSearched();
        Address savedAddressVersion = getSearchedByZipcode(address.getZipcode(), addressList);

        if (addressList.isEmpty() || savedAddressVersion == null) {
            addressList.add(address);

            try (FileWriter fileWriter = new FileWriter(FILE)) {
                fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(addressList));
                System.out.printf("%nSuccessfully saved to %s%n%n", FILE.getPath());
            } catch (IOException exception) {
                System.out.printf("%nCouldn't open file %s due to %s%n%n", FILE.getName(), exception.getMessage());
            }
        }
    }

    private Address filterAddressByZipcode(String zipcode, List<Address> addressList) {
        String regex = "\\D";
        return addressList.stream()
                .filter(address -> address.getZipcode().replaceAll(regex, "")
                        .equals(zipcode.replaceAll(regex, "")))
                .findFirst().orElse(null);
    }
}
