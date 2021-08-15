package com.bridgelab;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class AddressBookFileIO {
    public void writeData(ArrayList<AddressBookBluePrint> addressBook){
        StringBuffer addressBookBuffer = new StringBuffer();
        addressBook.forEach(employee ->{
            String empDataString = employee.toString().concat("\n");
            addressBookBuffer.append(empDataString);
        });
        try {
            Files.write(Paths.get("AddressBook.txt"),addressBookBuffer.toString().getBytes());
        }catch (IOException e){
        }
    }

    public List<AddressBookBluePrint> readData() {
        List<AddressBookBluePrint> addressBookList = new ArrayList<>();

        try {
            Files.lines(new File("AddressBook.txt").toPath())
                    .map(line -> line.trim())
                    .forEach(line -> System.out.println(line));
        }catch (IOException e){
        }
        return addressBookList;
    }
}