package com.lorenzovendrame.identityservice.domain;

import java.util.Objects;
import java.util.UUID;

public class Address {
    private UUID addressId;
    private String city;
    private String state;
    private String district;
    private String cep;
    private String streetName;
    private String buildingNumber;
    private String details;

    public Address() {}

    public UUID getAddressId() {
        return addressId;
    }
    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}