package com.theironyard.javawithclojure.jhporter;

/**
 * Created by jeffryporter on 5/26/16.
 */
public class Country
{
    private String startingInitial;
    private String countryName;



    public Country(String startingInitial, String countryName)
    {
        this.startingInitial = startingInitial;
        this.countryName = countryName;
    }

    public String getStartingInitial()
    {
        return startingInitial;
    }

    public void setStartingInitial(String startingInitial)
    {
        this.startingInitial = startingInitial;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }
}
