package com.bridgelab;


public class AddressBookBluePrint {
    //contact required variables
    private String bookName;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String eMail;
    private int zip;
    private long mobileNum;

    //default constructor
    public AddressBookBluePrint(){};

    //constructor variables passing from main
    public AddressBookBluePrint(String bookName, String firstName, String lastName, String address, String city, int zip, String state, long phoneNumber, String email) {
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.eMail = eMail;
        this.zip = zip;
        this.mobileNum = mobileNum;
    }

    //printing instance person details
    @Override
    public String toString() {
        return "AddressBookBluePrint{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", eMail='" + eMail + '\'' +
                ", zip=" + zip +
                ", mobileNum=" + mobileNum +
                '}';
    }

    //gets and sets for passing and calling variables
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public long getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
    }

}