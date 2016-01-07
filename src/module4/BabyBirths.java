/*
* First and final assignment of week 4 from the coursera "Java Programming: Solving Problems with Software" course.
* */

package module4;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths
{
    public static final int NOT_FOUND       = -1;
    public static final int NAME            = 0;
    public static final int GENDER          = 1;
    public static final int TOTAL_OF_BIRTHS = 2;
    public static final int LENGTH_OF_YEAR  = 4;

    public BabyBirths(String path, String file_name)
    {
        path_      = path;
        file_name_ = file_name;

        ResetMaleRank();
    }

    public BabyBirths(String path)
    {
        path_      = path;
        file_name_ = "";

        ResetMaleRank();
    }

    // Seventh method to implement
    public long GetTotalBirthsRankedHigher(int year,
                                           String name,
                                           String gender)
    {
        ResetMaleRank();

        file_name_ = path_ + "yob" + year + ".csv";

        FileResource file = new FileResource(file_name_);
        CSVParser parser = file.getCSVParser(false);

        long rank = GetRank(year, name, gender);
        if(rank != NOT_FOUND)
        {
            long total_births = 0;
            ResetMaleRank();
            for(CSVRecord record : parser)
            {
                if(record.get(GENDER).equalsIgnoreCase("F"))
                {
                    if(record.getRecordNumber() < rank &&
                       record.get(GENDER).equalsIgnoreCase(gender)) total_births += Long.parseLong(record.get(TOTAL_OF_BIRTHS));
                }
                else if(record.get(GENDER).equalsIgnoreCase("M"))
                {
                    ++male_rank_count_;
                    if(male_rank_count_ < rank &&
                       record.get(GENDER).equalsIgnoreCase(gender)) total_births += Long.parseLong(record.get(TOTAL_OF_BIRTHS));
                }
            }

            return total_births;
        }

        return NOT_FOUND;
    }

    // Sixth method to implement
    public double GetAverageRank(String name, String gender)
    {
        int year;
        long rank;
        long total_rank = 0;
        int rank_count  = 0;

        DirectoryResource dir = new DirectoryResource();
        for(File file : dir.selectedFiles())
        {
            year = GetYearFromString(file.getName());
            rank = GetRank(year, name, gender);
            if(rank != NOT_FOUND)
            {
                total_rank += rank;
                ++rank_count;
            }
        }

        if(rank_count != 0) return (total_rank / (double) rank_count);

        return (double) NOT_FOUND;
    }

    public int GetYearFromString(String str_obj)
    {
        int begin = IndexOfFirstFindNumber(str_obj);
        int end   = begin + LENGTH_OF_YEAR;

        if(begin != NOT_FOUND && end < str_obj.length()) return Integer.parseInt(str_obj.substring(begin, end));
        else                                             return NOT_FOUND;
    }

    // Fifth method to implement
    public int YearOfHighestRank(String name, String gender)
    {
        int max_year  = NOT_FOUND;
        long max_rank = Long.MAX_VALUE;

        DirectoryResource dir = new DirectoryResource();
        for(File file : dir.selectedFiles())
        {
            int year;
            long rank;

            year = GetYearFromString(file.getName());
            rank = GetRank(year, name, gender);
            if(rank != NOT_FOUND)
            {
                if(max_rank > rank)
                {
                    max_rank = rank;
                    max_year = year;
                }
            }
        }

        return max_year;
    }

    // Fourth method to implement
    public String WhatIsNameInYear(String name,
                                   int birth_year,
                                   int new_year,
                                   String gender)
    {
        long rank = GetRank(birth_year, name, gender);
        if(rank == NOT_FOUND) return "NO NAME";
        else                  return GetName(new_year, rank, gender);
    }

    //Third method to implement
    public String GetName(int year, long rank, String gender)
    {
        ResetMaleRank();

        file_name_ = path_ + "yob" + year + ".csv";

        FileResource file = new FileResource(file_name_);
        CSVParser parser = file.getCSVParser(false);

        for(CSVRecord record : parser)
        {
            if(record.get(GENDER).equalsIgnoreCase("F"))
            {
                if(record.getRecordNumber() == rank &&
                   record.get(GENDER).equalsIgnoreCase(gender)) return record.get(NAME);
            }
            else if(record.get(GENDER).equalsIgnoreCase("M"))
            {
                ++male_rank_count_;
                if(male_rank_count_ == rank &&
                   record.get(GENDER).equalsIgnoreCase(gender)) return record.get(NAME);
            }
        }

        return "NO NAME";
    }

    // Second method to implement
    public long GetRank(int year, String name, String gender)
    {
        ResetMaleRank();

        file_name_ = path_ + "yob" + year + ".csv";

        FileResource file = new FileResource(file_name_);
        CSVParser parser = file.getCSVParser(false);

        for(CSVRecord record : parser)
        {
            if(record.get(GENDER).equalsIgnoreCase("M")) ++male_rank_count_;

            if(record.get(NAME).equalsIgnoreCase(name) &&
               record.get(GENDER).equalsIgnoreCase(gender))
            {
                if(gender.equalsIgnoreCase("F"))    return record.getRecordNumber();
                else                                return male_rank_count_;
            }
        }

        return NOT_FOUND;
    }

    public void TotalBirths(FileResource file)
    {
        int total_names        = 0;
        int unique_boy_names   = 0;
        int unique_girl_names  = 0;

        for(CSVRecord record : file.getCSVParser(false))
        {
            if(record.get(GENDER).equals("M")) ++unique_boy_names;
            else                               ++unique_girl_names;
        }

        total_names = unique_boy_names + unique_girl_names;

        System.out.println("total names = "        + total_names);
        System.out.println("Unique girls names = " + unique_girl_names);
        System.out.println("Unique boys names = "  + unique_boy_names);
    }

    public void TestTotalBirths()
    {
        file_name_ = path_ + file_name_;

        FileResource file = new FileResource(file_name_);
        TotalBirths(file);
    }

    // Seventh method to test
    public void TestGetTotalBirthsRankedHigher()
    {
        String name   = "Emily";
        String gender = "f";
        int year      = 1990;

        long total = GetTotalBirthsRankedHigher(year, name, gender);

        System.out.printf("The total number of births whose name is higher ranked than \"%s\" is: %s%n",
                          name,
                          total);

        name   = "Drew";
        gender = "m";
        year      = 1990;

        total = GetTotalBirthsRankedHigher(year, name, gender);

        System.out.printf("The total number of births whose name is higher ranked than \"%s\" is: %s%n",
                          name,
                          total);
    }

    // Sixth method to Test
    public void TestGetAverageRank()
    {
        String name   = "Susan";
        String gender = "f";

        double average = GetAverageRank(name, gender);

        System.out.printf("The average rank for the name \"%s\" and gender \"%s\" is: %s%n",
                          name,
                          gender,
                          average);

        name   = "Robert";
        gender = "m";

        average = GetAverageRank(name, gender);

        System.out.printf("The average rank for the name \"%s\" and gender \"%s\" is: %s%n",
                name,
                gender,
                average);
    }

    // Fifth method to Test
    public void TestYearOfHighestRank()
    {
        String name = "Genevieve";
        String gender = "f";

        int year = YearOfHighestRank(name, gender);
        System.out.printf("The %s is the year with the highest rank for the name \"%s\" and gender \"%s\"%n",
                         year,
                         name,
                         gender);

        name = "Mich";
        gender = "m";

        year = YearOfHighestRank(name, gender);
        System.out.printf("The %s is the year with the highest rank for the name \"%s\" and gender \"%s\"%n",
                year,
                name,
                gender);
    }

    // Fourth method to test
    public void TestWhatIsNameInYear()
    {
        String name    = "Susan";
        int birth_year = 1972;
        int new_year   = 2014;
        String gender  = "f";

        String new_name = WhatIsNameInYear(name,
                                           birth_year,
                                           new_year,
                                           gender);

        System.out.printf("%s born in %s would be \"%s\" if she was born in %s%n",
                          name,
                          birth_year,
                          new_name,
                          new_year);

        name    = "Owen";
        birth_year = 1974;
        new_year   = 2014;
        gender  = "m";

        new_name = WhatIsNameInYear(name,
                                    birth_year,
                                    new_year,
                                    gender);

        System.out.printf("%s born in %s would be \"%s\" if she was born in %s%n",
                          name,
                          birth_year,
                          new_name,
                          new_year);
    }

    //Third method to test
    public void TestGetName()
    {
        int rank      = 350;
        String gender = "f";
        int year      = 1980;

        String name = GetName(year, rank, gender);
        System.out.printf("The Name for the Rank \"%s\" with Gender \"%s\" is: %s%n",
                         rank,
                         gender.toUpperCase(),
                         name);

        rank   = 450;
        gender = "m";
        year   = 1982;

        name = GetName(year, rank, gender);
        System.out.printf("The Name for the Rank \"%s\" with Gender \"%s\" is: %s%n",
                         rank,
                         gender.toUpperCase(),
                         name);
    }

    // Second method to test
    public void TestGetRank()
    {
        String name = "Emily";
        String gender = "f";
        int year      = 1960;

        long rank = GetRank(year, name, gender);
        System.out.printf("The rank for the Name \"%s\" with Gender \"%s\" is: %s%n", name,
                                                                                     gender.toUpperCase(),
                                                                                     rank);

        name = "Frank";
        gender = "m";
        year      = 1971;

        rank = GetRank(year, name, gender);
        System.out.printf("The rank for the Name \"%s\" with Gender \"%s\" is: %s%n", name,
                gender.toUpperCase(),
                rank);
    }

    private void ResetMaleRank() { male_rank_count_ = 0; }

    private int IndexOfFirstFindNumber(String str_obj)
    {
        if(str_obj.isEmpty() || str_obj == null) return NOT_FOUND;

        char[] temp = str_obj.toCharArray();

        for(int it = 0; it != temp.length; ++it)
        {
            if(Character.isDigit(temp[it])) return it;
        }

        return NOT_FOUND;
    }

    private String file_name_;
    private String path_;
    private long male_rank_count_;
}
