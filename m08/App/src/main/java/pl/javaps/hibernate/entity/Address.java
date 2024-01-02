package pl.javaps.hibernate.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Address {

    @Enumerated
    @Column(name = "address_type")
    private AddressType addressType;
    private String street;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;

    public Address(){}

    public Address(AddressType addressType, String street, String postalCode, String city) {
        this.addressType = addressType;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
