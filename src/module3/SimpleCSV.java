/*
* First assignment of week 3 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module3;

import edu.duke.*;
import org.apache.commons.csv.*;

public class SimpleCSV
{
    public SimpleCSV(FileResource file) { file_   = file; }

    public void Test()
    {
        parser_ = file_.getCSVParser();
        System.out.println(CountryInfo("Nauru"));

        parser_ = file_.getCSVParser();
        ListExportersTwoItems("cotton", "flowers");

        parser_ = file_.getCSVParser();
        String item = "cocoa";
        System.out.println("\nNumber of countries that exports \"" + item + "\": " + NumberOfExporters(item));

        parser_ = file_.getCSVParser();
        item = "$999,999,999,999";
        System.out.println("\nNumber of countries with bigger exports than \"" + item + "\": ");
        BigExporters(item);
    }

    public String CountryInfo(String country)
    {
        String item;
        System.out.println("Information about \"" + country + "\"");
        for(CSVRecord record : parser_)
        {
            item = record.get("Country");
            if(item.equalsIgnoreCase(country))
            {
                String exports = "Exports: " + record.get("Exports");
                String values  = "Value: " + record.get("Value (dollars)");

                return exports + "\n" + values;
            }
        }

        return "Not found";
    }

    public void ListExportersTwoItems(String item1, String item2)
    {
        String temp;
        System.out.println("\nCountries that export \"" + item1 + "\" and \"" + item2 + "\":");

        for(CSVRecord record : parser_)
        {
            temp = record.get("Exports");
            if(temp.contains(item1) && temp.contains(item2)) System.out.println(record.get("Country"));
        }
    }

    public int NumberOfExporters(String item)
    {
        int count = 0;
        String temp;
        for(CSVRecord record : parser_)
        {
            temp = record.get("Exports");
            if(temp.contains(item)) ++count;
        }
        return count;
    }

    public void BigExporters(String amount)
    {
        String temp;
        int amount_length = amount.length();

        for(CSVRecord record : parser_)
        {
            temp = record.get("Value (dollars)");
            if(temp.length() > amount_length) System.out.println(record.get("Country") + "\t" +
                                                                 record.get("Value (dollars)"));
        }
    }

    private FileResource file_;
    private CSVParser    parser_;
}
