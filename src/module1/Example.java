package module1;

import java.util.ArrayList;
import edu.duke.DirectoryResource;

public class Example
{
    public static void
    main(String argv[])
    {
        BatchGrayScale ex = new BatchGrayScale(new DirectoryResource(),
                                               new ArrayList<>(),
                                               new ArrayList<>());

        ex.ReadDirectory();
        ex.Convert();
        ex.Save();
    }
}
