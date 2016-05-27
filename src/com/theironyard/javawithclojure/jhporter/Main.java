package com.theironyard.javawithclojure.jhporter;

import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    //parse file
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

    //load file into hashmap
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

    //select save method
    public static void saveFile(String newFileName, ArrayList<Country>chosenCountries)
    {
        Scanner input = new Scanner(System.in);
        System.out.printf("\nWould you rather save this as a pipe-separated file, or a json file?\n1. pipe-separated\n2. JSON\n");
        String choice = input.nextLine();

        switch (choice)
        {
            case "1":
                manualSaveFile(newFileName, chosenCountries);
                break;
            case "2":
            jsonSaveFile(newFileName, chosenCountries);
                break;
            default:
                System.out.printf("\nNot a valid selection!");
                saveFile(newFileName, chosenCountries);
        }
    }

    //save file as pipe-separated values
    public static void manualSaveFile(String fileLoc,  ArrayList<Country> countryList)
    {
        try
        {
            PrintWriter output = new PrintWriter(fileLoc, "UTF-8");
            for (int i = 0; i < countryList.size(); i++)
            {
                output.printf("%s|%s\n", countryList.get(i).getInitials(), countryList.get(i).getCountryName());
            }
            output.close();
            System.out.printf("\nSaving to %s.", fileLoc);
        }
        catch(Exception e)
        {
            System.err.printf("\nNot a Valid File!");
        }

    }

    //save as json file
    public static void jsonSaveFile(String fileLoc,  ArrayList<Country> countryList)
    {
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.include("*").serialize(countryList);
        File f = new File(fileLoc);
        try
        {
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //get user input
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

        //read/parse file
        countryMap = loadCountriesIntoMap(parseCountries("countries.txt"));

        //get user input
        letterChoice = getUserLetter().toUpperCase();

        newFileName = String.format("%s_Countries.txt",letterChoice);
        chosenCountries = countryMap.get(letterChoice);

        //save file
        saveFile(newFileName, chosenCountries);
    }
}
