/*
* Second assignment of week 3 from the coursera "Java Programming: Solving Problems with Software" course.
* */


package module3;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class WeatherCSV
{
    public static final double NO_TEMPERATURE = -2.0;

    public double AverageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double temperature = 0;
        double curent_temp = 0;
        int count          = 0;
        int humidity       = 0;
        String temp;

        for(CSVRecord record : parser)
        {
            temp = record.get("Humidity");
            if(temp.contains("N/A")) continue;
            else
            {
                humidity = Integer.parseInt(temp);
                if(humidity > value)
                {
                    ++count;
                    curent_temp = Double.parseDouble(record.get("TemperatureF"));
                    temperature += curent_temp;
                }
            }
        }
        if(count != 0) return temperature / (double) count;
        else           return NO_TEMPERATURE;
    }

    public double AverageTemperatureInFile(CSVParser parser)
    {
        double temperature = 0;
        double curent_temp = 0;
        int count          = 0;

        for(CSVRecord record : parser)
        {
            ++count;
            curent_temp = Double.parseDouble(record.get("TemperatureF"));
            temperature += curent_temp;
        }

        return temperature / (double) count;
    }
    public CSVRecord ColdestHourInFile(CSVParser parser)
    {
        CSVRecord coldest = null;
        for(CSVRecord current : parser) coldest = Min(current, coldest);

        return coldest;
    }

    public CSVRecord LowestHumidityInManyFiles()
    {
        CSVRecord lowest = null;
        DirectoryResource dir = new DirectoryResource();
        for(File file : dir.selectedFiles())
        {
            FileResource res = new FileResource(file);
            lowest = LowestHumidityInFile(res.getCSVParser());
        }

        return lowest;
    }

    public CSVRecord LowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowest = null;
        for(CSVRecord current : parser)
        {
            String temp_curr;
            String temp_max;

            double max  = 0.0;
            double curr = 0.0;

            if(lowest == null)
            {
                temp_curr = current.get("Humidity");
                if(!temp_curr.contains("N/A")) lowest = current;
            }
            else
            {
                temp_curr = current.get("Humidity");
                temp_max  = lowest.get("Humidity");
                if(!temp_curr.contains("N/A") && !temp_max.contains("N/A"))
                {
                    curr = Double.parseDouble(temp_curr);
                    max  = Double.parseDouble(temp_max);

                    if (curr < max) lowest = current;
                }
            }
        }

        return lowest;
    }

    public String FileWithColdestTemperature()
    {
        String file_name  = null;
        CSVRecord largest = null;

        double max  = 0.0;
        double curr = 0.0;

        DirectoryResource dir = new DirectoryResource();

        for(File file : dir.selectedFiles())
        {
            FileResource res  = new FileResource(file);
            CSVRecord current = ColdestHourInFile(res.getCSVParser());

            if(largest == null)
            {
                largest   = current;
                file_name = file.getName();
            }
            else
            {
                curr = Double.parseDouble(current.get("TemperatureF"));
                if(curr != -9999)
                {
                    max = Double.parseDouble(largest.get("TemperatureF"));
                    if (curr < max)
                    {
                        largest   = current;
                        file_name = file.getName();
                    }
                }
            }

        }

        return file_name;
    }

    public CSVRecord Min(CSVRecord first, CSVRecord second)
    {
        double max  = 0.0;
        double curr = 0.0;

        if(second == null) second = first;
        else
        {
            curr = Double.parseDouble(first.get("TemperatureF"));
            if(curr != -9999)
            {
                max = Double.parseDouble(second.get("TemperatureF"));
                if (curr < max) second = first;
            }
        }
        return second;
    }

    public CSVRecord HottestOur(CSVParser parser)
    {
        CSVRecord largest = null;

        for(CSVRecord current : parser) largest = Max(current, largest);

        return largest;
    }

    public CSVRecord HottestOurInManyDays()
    {
        CSVRecord largest = null;
        DirectoryResource dir = new DirectoryResource();

        for(File file : dir.selectedFiles())
        {
            FileResource res = new FileResource(file);
            CSVRecord current = HottestOur(res.getCSVParser());

            largest = Max(current, largest);
        }

        return largest;
    }

    public CSVRecord Max(CSVRecord first, CSVRecord second)
    {
        double max = 0.0;
        double curr = 0.0;

        if(second == null) second = first;
        else
        {
            curr = Double.parseDouble(first.get("TemperatureF"));
            max  = Double.parseDouble(second.get("TemperatureF"));

            if(curr > max) second = first;
        }
        return second;
    }

    public void TestColdestHourInFile()
    {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();

        CSVRecord record = ColdestHourInFile(parser);
        System.out.println("Coldest temperature was: " +  record.get("TemperatureF") + " at " + record.get("DateUTC"));
    }

    void TestMany()
    {
        CSVRecord record = HottestOurInManyDays();
        System.out.println("Hottest temperature was: " +  record.get("TemperatureF") + " at " + record.get("DateUTC"));
    }

    public void TestAverageTemperatureInFile()
    {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();

        double average = AverageTemperatureInFile(parser);
        System.out.println("Average temperature in file is: " + average);
    }

    public void TestAverageTemperatureWithHighHumidityInFile()
    {
        FileResource file = new FileResource();
        CSVParser parser  = file.getCSVParser();
        int humidity_value = 79;

        double average = AverageTemperatureWithHighHumidityInFile(parser, humidity_value);
        if(average != NO_TEMPERATURE) System.out.println("Average temperature with humidity over " + humidity_value +
                " is: "                                   + average);
        else                          System.out.println("No temperature with humidity " + humidity_value);
    }

    public void TestLowestHumidityInManyFiles()
    {
        CSVRecord record = LowestHumidityInManyFiles();
        System.out.println("Lowest humidity was: " +  record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    public void TestLowestHumidityInFile()
    {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();

        CSVRecord record = LowestHumidityInFile(parser);
        System.out.println("Lowest humidity was: " +  record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    public void TestFileWithColdestTemperature()
    {
        String file_name = FileWithColdestTemperature();
        System.out.println("Coldest day was in file: " + file_name);

        String path = "\\data\\nc_weather\\2013\\" + file_name;

        FileResource file = new FileResource(path);
        CSVRecord record = ColdestHourInFile(file.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + record.get("TemperatureF"));

    }
}
