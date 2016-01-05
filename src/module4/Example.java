package module4;


public class Example
{
    public static void
    main(String argv[])
    {
        try
        {
            String path = "C:\\DEV\\Workspace\\Duke Course 2\\data\\us_babynames_by_year\\";
            String file_name = "yob1905.csv";
            BabyBirths ex = new BabyBirths(path, file_name);
            //ex.TestGetTotalBirthsRankedHigher();
            //ex.TestGetAverageRank();
            //ex.TestYearOfHighestRank();
            //ex.TestWhatIsNameInYear();
            //ex.TestGetName();
            //ex.TestGetRank();
            ex.TestTotalBirths();
        }
        catch(Exception ex) { System.err.println("Exception occured: " + ex.getMessage()); }
    }
}
