package domainmodel;

/**
 * The java bean class that handles the Address object attributes and getter/setter methods. 
 */
public class Address {
    /**
     * Contains the house number
     */
    private int houseNumber;
    
    /**
     * Contains the street
     */
    private String street;
    
    /**
     * Contains the city
     */
    private String city;
    
    /**
     * contains the province
     */
    private String province;
    
    /**
     * contains the country
     */
    private String country;
    
    /**
     * contains the postalCode
     */
    private String postalCode;

    /**
     * Constructor
     * @param houseNumber Contains the house number
     * @param street Contains the street
     * @param city Contains the city
     * @param province contains the province
     * @param country contains the country
     * @param postalCode contains the postalCode
     */
    public Address(int houseNumber, String street, String city, String province, String country, String postalCode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }
    
    public Address() {
        this.houseNumber = 0;
        this.street = "";
        this.city = "";
        this.province = "";
        this.country = "";
        this.postalCode = "";
    }

    /**
     * gets the houseNumber
     * @return the house number in integer format
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * sets the house number
     * @param houseNumber the house number in integer format
     */
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * gets the street
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * sets the street 
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * gets the city
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * sets the city
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * gets the province
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * sets the province
     * @param province the province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * gets the country
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the country
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * get the postal code
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    
    /**
     * sets the postal code
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
}
