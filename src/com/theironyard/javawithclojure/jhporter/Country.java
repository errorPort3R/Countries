package com.theironyard.javawithclojure.jhporter;

/**
 * Created by jeffryporter on 5/26/16.
 */
public class Country
{
    private String initials;
    private String countryName;



    public Country(String initials, String countryName)
    {
        this.initials = initials;
        this.countryName = countryName;
    }

    public String getInitials()
    {
        return initials;
    }

    public void setInitials(String startingInitial)
    {
        this.initials = startingInitial;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "initials='" + initials + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
