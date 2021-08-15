package com.bridgelab;


import java.util.*;
import java.util.stream.Collectors;

public class AddressBookManager {
    public ArrayList<AddressBookBluePrint> detailedEntries = new ArrayList<>();
    public Map<String, ArrayList<AddressBookBluePrint>> book = new HashMap<>();
    public Map<String, ArrayList<AddressBookBluePrint>> multiBook = new HashMap<>();
    public Map<String, ArrayList<AddressBookBluePrint>> cityList = new HashMap<>();
    public Map<String, ArrayList<AddressBookBluePrint>> stateList = new HashMap<>();
    public Map<Integer, ArrayList<AddressBookBluePrint>> zipList = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    public void callAddressBookBluePrint() {

        //Enter the Book name before detailed Entries
        System.out.println("Enter Address Book Name");
        String BookName = sc.next();

        //Enter the detailed Entries
        System.out.println("Enter you first name");
        String FirstName = sc.next();
        System.out.println("Enter you last name");
        String LastName = sc.next();
        sc.nextLine();
        System.out.println("Enter you Address name");
        String Address = sc.nextLine();
        System.out.println("Enter you zip ");
        int Zip = sc.nextInt();
        System.out.println("Enter you city name");
        String City = sc.next();
        System.out.println("Enter you state name");
        String State = sc.next();
        sc.nextLine();
        System.out.println("Enter you phone number");
        long PhoneNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter you email name");
        String Email = sc.nextLine();

        //Checking name of the person to avoid duplicate Entries
        if (check(FirstName)) {
            callAddressBookTemp(BookName, FirstName, LastName, Address, City, Zip, State, PhoneNumber, Email);
            System.out.println("AddressBookBluePrint{" + "firstName='" + FirstName + '\'' + ", lastName='" + LastName + '\'' + ", address='" + Address + '\'' + ", city='" + City + '\'' + ", state='" + State + '\'' + ", eMail='" + Email + '\'' + ", zip=" + Zip + ", mobileNum=" + PhoneNumber + '}');
        } else
            System.out.println("The " + FirstName + " already exist in contacts please use different name");
    }

    //Checking duplicate Entries
    private boolean check(String firstName) {
        Boolean check = true;
        if (detailedEntries.stream().anyMatch(person -> person.getFirstName().equals(firstName))) {
            check = false;
            return check;
        } else {
            return check;
        }
    }

    //Checking person in city
    public void viewPersonByCity() {

        System.out.println("Enter city");
        String location = sc.next();
        Boolean ch = false;
        try {
            ch = cityList.entrySet().stream().anyMatch(p -> p.getKey().equals(location));
            if (ch == true)
                cityList.entrySet().stream().filter(p -> p.getKey().equals(location)).forEach(System.out::println);
            else
                System.out.println("no records found");
        } catch (Exception e) {
            System.out.println("No persons in city");
        }
    }

    //Checking person in state
    public void viewPersonByState() {
        System.out.println("Enter State");
        String State = sc.next();
        Boolean ch = false;
        try {
            ch = stateList.entrySet().stream().anyMatch(p -> p.getKey().equals(State));
            if (ch == true)
                stateList.entrySet().stream().filter(p -> p.getKey().equals(State)).forEach(System.out::println);
            else
                System.out.println("no records found");
        } catch (Exception e) {
            System.out.println("No persons in state");
        }
    }

    //sorting and printing using api streams and lambda exp for each
    public void viewPersonsByCityState() {
        System.out.println("Enter 1:city 2:state");
        int opt = sc.nextInt();
        switch (opt){
            case 1:
                System.out.println("Enter city name");
                String location = sc.next();
                Map<String, ArrayList<AddressBookBluePrint>> cityL =
                        cityList.entrySet().stream().filter(p->p.getKey().equals(location)).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
                cityL.forEach((k, v)-> System.out.println((k+":"+v)));
                break;
            case 2:
                System.out.println("Enter state name");
                String state = sc.next();
                Map<String, ArrayList<AddressBookBluePrint>> stateL =
                        stateList.entrySet().stream().filter(s->s.getValue().equals(state)).collect(Collectors.toMap(entry->entry.getKey(), entry->entry.getValue()));
                stateL.entrySet().stream().forEach(s-> System.out.println(s.getKey()+":"+s.getValue().toString()));
                break;

        }
    }
    //Counting persons as per city's and states
    public void countPersonsByCityState(){
        System.out.println("Enter 1:city 2:state");
        int opt = sc.nextInt();
        switch (opt){
            case 1:
                System.out.println("Enter city name");
                String location = sc.next();
                Long count = cityList.entrySet().stream().filter(p->p.getKey().equals(location)).collect(Collectors.counting());
                System.out.println(location+" Persons : "+count);
                break;
            case 2:
                System.out.println("Enter state name");
                String state = sc.next();
                Long count1 = cityList.entrySet().stream().filter(p->p.getKey().equals(state)).collect(Collectors.counting());
                System.out.println(state+" Persons : "+count1);
                break;
        }
    }

    //sort as per person first name alphabetically
    public void sortAlphabetically(){
        book.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }

    //sort as per City State Zip
    public void sortCityStateZip(){
        System.out.println("sort by 1:city 2:state 3:zip");
        int opt = sc.nextInt();
        switch (opt){
            case 1:
                cityList.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
                break;
            case 2:
                stateList.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
                break;
            case 3:

        }
    }

    //here is adding all the all collections
    private void callAddressBookTemp(String bookName,
                                     String firstName, String lastName,
                                     String address, String city, int zip,
                                     String state, long phoneNumber, String email) {
        AddressBookBluePrint adder = new AddressBookBluePrint(bookName, firstName, lastName, address, city, zip, state, phoneNumber, email);
        //adder.toString();
        detailedEntries.add(adder);
        book.put(firstName, detailedEntries);
        multiBook.put(bookName, detailedEntries);
        cityList.put(city, detailedEntries);
        stateList.put(state, detailedEntries);
        zipList.put(zip, detailedEntries);
    }

    //write data in file
    public void writeAddressBookInFiles(){
            new AddressBookFileIO().writeData(detailedEntries);
            System.out.println("Data stored successfully in AddressBook.txt");
    }

    //read data from file
    public void readAddressBookInFiles(){
            new AddressBookFileIO().readData();
            System.out.println("Data Read successfully From AddressBook.txt");
    }


    //edit contact as per parameter
    public void editContact() {
        System.out.println("enter your book name");
        String bookName = sc.next();
        ArrayList<AddressBookBluePrint> tempContact = multiBook.get(bookName);
        System.out.println("enter your name");
        String name = sc.next();
        for (AddressBookBluePrint eachDetails : tempContact) {
            if (eachDetails.getFirstName().equals(name)) {
                boolean conditon = true;
                while (conditon) {
                    System.out.println("enter number  1:first_name 2:last_name 3:address 4:City 5:zip 6:state 7:phone_number 8:email 0:quit");
                    int opt = sc.nextInt();
                    switch (opt) {
                        case 1:
                            System.out.println("Enter you first name");
                            String firstname = sc.next();
                            eachDetails.setFirstName(firstname);
                            System.out.println(book);
                            break;
                        case 2:
                            System.out.println("Enter you last name");
                            String lastname = sc.next();
                            eachDetails.setLastName(lastname);
                            System.out.println(book);
                            break;
                        case 3:
                            System.out.println("Enter you address ");
                            String addressname = sc.next();
                            eachDetails.setAddress(addressname);
                            System.out.println(book);
                            break;
                        case 4:
                            System.out.println("Enter you City name");
                            String cityname = sc.next();
                            eachDetails.setCity(cityname);
                            System.out.println(book);
                            break;
                        case 5:
                            System.out.println("Enter you Zip name");
                            int zipname = sc.nextInt();
                            eachDetails.setZip(zipname);
                            System.out.println(book);
                            break;
                        case 6:
                            System.out.println("Enter you State name");
                            String statename = sc.next();
                            eachDetails.setState(statename);
                            System.out.println(book);
                            break;
                        case 7:
                            System.out.println("Enter you Phone number");
                            long phonenumber = sc.nextLong();
                            sc.nextLine();
                            eachDetails.setMobileNum(phonenumber);
                            System.out.println(book);
                            break;
                        case 8:
                            System.out.println("Enter you email");
                            String emailname = sc.next();
                            eachDetails.seteMail(emailname);
                            System.out.println(book);
                            break;
                        case 0:
                            conditon = false;
                            break;
                        default:
                            System.out.println("invalid input");
                    }
                }
            }
        }
    }
    public boolean takeOption() {
        boolean conditon = true;
        while (conditon) {
            System.out.println("enter 1:addContact 2:editContact 3:viewPersonByCity 4:viewPersonByState 5:viewPersonsByCityOrState " +
                    "6:countPersonsByCityState 7:sortAlphabetically 8:sortCityStateZip 9:Write address in system file 10:Read address in system file or 0 to quit");
            int opt = sc.nextInt();
            switch (opt) {
                case 1:
                    callAddressBookBluePrint();
                    break;
                case 2:
                    editContact();
                    break;
                case 3:
                    viewPersonByCity();
                    break;
                case 4:
                    viewPersonByState();
                    break;
                case 5:
                    viewPersonsByCityState();
                    break;
                case 6:
                    countPersonsByCityState();
                    break;
                case 7:
                    sortAlphabetically();
                    break;
                case 8:
                    sortCityStateZip();
                    break;
                case 9:
                    writeAddressBookInFiles();
                    break;
                case 10:
                    readAddressBookInFiles();
                    break;
                case 0:
                    conditon = false;
                    break;
                default:
                    System.out.println("invalid input");
            }
        }
        return conditon;
    }


}