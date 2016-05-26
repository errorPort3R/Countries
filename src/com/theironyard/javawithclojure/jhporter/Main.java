package com.theironyard.javawithclojure.jhporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static ArrayList<Country> loadCountries(String filename) throws FileNotFoundException {
        ArrayList<Country> countries = new ArrayList<>();
        File f = new File(filename);
        Scanner fileScanner =  new Scanner(f);
        while (fileScanner.hasNext())
        {
            String line = fileScanner.nextLine();
            String[] fields = line.split("\\|");
            Country country = new Country(fields[0], fields[1]);
            countries.add(country);
        }
        return countries;
    }


    public static void saveFile(String fileLoc,  ArrayList<Country> countryList, String Letter)
    {

    }



    public static String getUserLetter()
    {
        String letter;
        Scanner input = new Scanner(System.in);
        System.out.printf("\nWhat is the first letter you want your list of countries to start with? ");
        letter = input.nextLine();
        if (letter.length() == 1 && letter == "[a-zA-Z]")
        {
            return letter;
        }
        else
        {
            System.out.printf("\nNot a valid letter!!!");
            return getUserLetter();
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
	    //declare variables
        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
        String letterChoice;
        String newFileName;
        ArrayList<Country> chosenCountries;


        //   read/parse file
        ArrayList<Country> countries = loadCountries("countries.txt");
        for(Country country : countries)
        {
            String firstLetter = (String.valueOf(country.getStartingInitial().charAt(0)));
            if (!countryMap.containsKey(firstLetter))
            {
                countryMap.put(firstLetter, new ArrayList<>());
            }
            ArrayList<Country> countryList = countryMap.get(firstLetter);
            countryList.add(country);
        }

        //get user input
        letterChoice = getUserLetter().toUpperCase();

        newFileName = String.format("%s_Countries.txt",letterChoice);
        System.out.printf("\nSaving to %s.", newFileName);

        chosenCountries =
        saveFile(newFileName, countryMap, letterChoice);

    }
}
