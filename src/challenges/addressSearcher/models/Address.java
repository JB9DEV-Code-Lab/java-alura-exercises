package challenges.addressSearcher.models;

import challenges.addressSearcher.dtos.AddressDTO;

public class Address {
    private final String city;
    private final String complement;
    private final String neighborhood;
    private final String state;
    private final String street;
    private final String zipcode;

    public Address(AddressDTO dto) {
        this.city = dto.localidade();
        this.complement = dto.complemento();
        this.neighborhood = dto.bairro();
        this.state = dto.uf();
        this.street = dto.logradouro();
        this.zipcode = dto.cep();
    }

    @Override
    public String toString() {
        try {
            return zipcode + ": " + street + (!complement.isEmpty() ? ", " : "") + " - " + neighborhood + " - " + city
                    + " - " + state;
        } catch (NullPointerException exception) {
            return """
            Couldn't access some field(s), so probably this zipcode doesn't exist or something else happened.
            These are the exception details: %s
            """.formatted(exception.getMessage());
        }
    }
}
