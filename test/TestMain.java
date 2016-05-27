
import com.theironyard.javawithclojure.jhporter.Country;
import com.theironyard.javawithclojure.jhporter.Main;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffryporter on 5/26/16.
 * referenced some of the code we used in class(Java with Clojure taught by Zach Oakes, May2016 cohort) regarding json and file writing.
 *
 */
public class TestMain {

    @Test
    public void parseFile() throws FileNotFoundException {

        String testFile1 = "testFile1.txt";

        ArrayList<Country> parse1 = Main.parseCountries(testFile1);
        ArrayList<Country> parse2 = Main.parseCountries(testFile1);

        if (parse1.size() == parse2.size())
        {
            for (int i = 0; i < parse1.size(); i++)
            {
                assertTrue(parse1.get(i).getInitials().equals(parse2.get(i).getInitials()));
                assertTrue(parse1.get(i).getCountryName().equals(parse2.get(i).getCountryName()));
            }
        }
    }

    @Test
    public void loadHashMap() throws FileNotFoundException {

        String testFile1 = "testFile1.txt";
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Country> parse = Main.parseCountries(testFile1);
        HashMap<String, ArrayList<Country>> map1 = Main.loadCountriesIntoMap(parse);
        HashMap<String, ArrayList<Country>> map2 = Main.loadCountriesIntoMap(parse);

        for (Country country : parse)
        {
            keys.add(String.valueOf(country.getInitials().charAt(0)));
        }

        for (String key : keys)
        {
            ArrayList<Country> map1List = map1.get(key);
            ArrayList<Country> map2List = map1.get(key);

            if (map1List.size() == map2List.size())
            {

                for (int i = 0; i < map1List.size(); i++)
                {
                    assertTrue(map1List.get(i).toString().equals(map2List.get(i).toString()));
                }
            }
        }








    }



    @Test
    public void pipeSave() throws Exception
    {
        String testFile2 = "testFile2.txt";
        String testFile3 = "testFile3.txt";

        ArrayList<Country> testList = new ArrayList<Country>();
        testList.add(new Country("C","cat"));
        testList.add(new Country("D","dog"));
        testList.add(new Country("k","kitten"));
        testList.add(new Country("s","smithsonian"));
        testList.add(new Country("(","(*&%$FVB"));
        testList.add(new Country("K","KJHGBV*^&R "));
        testList.add(new Country("p","poiuehrLIJG&^$%#"));
        testList.add(new Country("I","IU UBPT)%GB  ^^UGI GF("));

        Main.manualSaveFile(testFile2, testList);
        Main.manualSaveFile(testFile3, testList);

        ArrayList<Country> parse1 = Main.parseCountries(testFile2);
        ArrayList<Country> parse2 = Main.parseCountries(testFile3);

        if (parse1.size() == parse2.size())
        {
            for (int i = 0; i < parse1.size(); i++)
            {
                assertTrue(parse1.get(i).getInitials().equals(parse2.get(i).getInitials()));
                assertTrue(parse1.get(i).getCountryName().equals(parse2.get(i).getCountryName()));
            }
        }

        File f = new File(testFile2);
        File g = new File(testFile3);
        f.delete();
        g.delete();
    }

    @Test
    public void jsonSave()
    {
        String testFile4 = "testFile4.txt";
        String testFile5 = "testFile5.txt";

        ArrayList<Country> testList = new ArrayList<Country>();
        testList.add(new Country("C","cat"));
        testList.add(new Country("D","dog"));
        testList.add(new Country("k","kitten"));
        testList.add(new Country("s","smithsonian"));
        testList.add(new Country("(","(*&%$FVB"));
        testList.add(new Country("K","KJHGBV*^&R "));
        testList.add(new Country("p","poiuehrLIJG&^$%#"));
        testList.add(new Country("I","IU UBPT)%GB  ^^UGI GF("));

        Main.jsonSaveFile(testFile4, testList);
        Main.jsonSaveFile(testFile5, testList);

        File f = new File(testFile4);
        File g = new File(testFile5);

        try
        {
            Scanner scannerf = new Scanner(f);
            Scanner scannerg = new Scanner(g);

            scannerf.useDelimiter("\\Z");
            scannerg.useDelimiter("\\Z");
            String contentsf = scannerf.next();
            String contentsg = scannerg.next();
            assertTrue(contentsf.equals(contentsg));
        }
        catch (FileNotFoundException e) {}

        f.delete();
        g.delete();
    }
}
