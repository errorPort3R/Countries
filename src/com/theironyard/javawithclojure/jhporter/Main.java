package com.theironyard.javawithclojure.jhporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static ArrayList<Country> parseCountries(String filename) throws FileNotFoundException {
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

    public static HashMap<String, ArrayList<Country>> loadCountriesIntoMap (ArrayList<Country> countries)
    {
        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
        for(Country country : countries)
        {
            String firstLetter = (String.valueOf(country.getInitials().charAt(0)));
            if (!countryMap.containsKey(firstLetter))
            {
                countryMap.put(firstLetter, new ArrayList<>());
            }
            ArrayList<Country> countryList = countryMap.get(firstLetter);
            countryList.add(country);
        }
        return countryMap;
    }


    public static void saveFile(String fileLoc,  ArrayList<Country> countryList, String Letter)
    {
        try
        {
            PrintWriter output = new PrintWriter(fileLoc, "UTF-8");
            for (int i = 0; i < countryList.size(); i++)
            {
                output.printf("%s|%s\n", countryList.get(i).getInitials(), countryList.get(i).getCountryName());
            }
            output.close();
        }
        catch(Exception e)
        {
            System.err.printf("\nNot a Valid File!");
        }

    }

    public static String getUserLetter()
    {
        String letter;
        Scanner input = new Scanner(System.in);
        System.out.printf("\nWhat is the first letter you want your list of countries to start with? ");
        letter = input.nextLine();
        if (letter.length() == 1 && letter.matches("[a-zA-Z]+"))
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
        HashMap<String, ArrayList<Country>> countryMap;
        String letterChoice;
        String newFileName;
        ArrayList<Country> chosenCountries;


        //   read/parse file
        countryMap = loadCountriesIntoMap(parseCountries("countries.txt"));

        //get user input
        letterChoice = getUserLetter().toUpperCase();

        newFileName = String.format("%s_Countries.txt",letterChoice);
        chosenCountries = countryMap.get(letterChoice);

        //save file
        System.out.printf("\nSaving to %s.", newFileName);
        saveFile(newFileName, chosenCountries, letterChoice);

    }
}
