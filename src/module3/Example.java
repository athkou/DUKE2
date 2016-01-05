package module3;

import edu.duke.FileResource;

public class Example
{
    public static void
    main(String argv[])
    {
        try
        {
            FileResource file = new FileResource();
            SimpleCSV csv = new SimpleCSV(file);

            csv.Test();
        }
        catch(Exception ex){ System.err.println("Exception: " + ex.getMessage()); }
    }

}
