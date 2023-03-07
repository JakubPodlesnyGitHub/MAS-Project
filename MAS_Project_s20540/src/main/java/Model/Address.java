package Model;

import RequirementsInfo.StaticProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Address {

    @Column(name = "Address Street", nullable = true)
    private String street;
    @Column(name = "Building Number", nullable = true)
    private Integer buildingNumber;
    @Column(name = "Apartment Number", nullable = true)
    private Integer apartmentNumber;
    @Column(name = "Zip Code", nullable = true)
    private String zipCode;
    @Column(name = "City", nullable = true)
    private String city;

    public Address() {

    }

    public Address(String street, int buildingNumber, String zipCode, String city) throws Exception {
        setStreet(street);
        setBuildingNumber(buildingNumber);
        setZipCode(zipCode);
        setApartmentNumber(null);
        setCity(city);
    }

    public Address(String street, int buildingNumber, Integer apartmentNumber, String zipCode, String city) throws Exception {
        setStreet(street);
        setBuildingNumber(buildingNumber);
        setZipCode(zipCode);
        setApartmentNumber(apartmentNumber);
        setCity(city);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws Exception {
        if (street == null)
            throw new Exception("Employee street address cannot be null. This filed is mandatory");
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) throws Exception {
        if (zipCode == null)
            throw new Exception("Employee zip code address cannot be null. This filed is mandatory");
        Pattern pattern = Pattern.compile(StaticProperties.getStaticPropertiesInstance().ZIP_CODE_PATTERN);
        Matcher matcher = pattern.matcher(zipCode);
        if (!matcher.matches())
            throw new Exception("The zip code provided does not meet the requirements.\nThe zip code should be in format xx-xxx, where x is number.");
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws Exception {
        if (city == null)
            throw new Exception("Employee city address cannot be null. This filed is mandatory");
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStreet()).append(" ").append((apartmentNumber != null) ? buildingNumber + "/" + apartmentNumber : buildingNumber).append("\n");
        sb.append(zipCode).append(",").append(city);
        return sb.toString();
    }
}
